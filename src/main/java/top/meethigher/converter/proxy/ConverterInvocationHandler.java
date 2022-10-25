package top.meethigher.converter.proxy;


import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.annotation.ConverterResult;
import top.meethigher.converter.annotation.ConverterResults;
import top.meethigher.converter.annotation.ConverterResultsMap;
import top.meethigher.converter.utils.Assert;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Proxy代理对象生成器
 * 目前提供两种方式
 * {@link DefaultConverterInvocationHandler} 匹配注解里配置的映射关系，支持同类型同字段自动转换、支持多继承
 * {@link DotSupportConverterInvocationHandler} 仅匹配注解里配置的映射关系，支持多继承、支持层级转换(比如person.name&lt;=&gt;mingCheng)
 *
 * @author chenchuancheng
 * @since 2022/10/25 10:31
 */
public abstract class ConverterInvocationHandler<T extends ObjectConverter<?, ?>> implements InvocationHandler {

    /**
     * 需要代理实现的接口
     */
    private final Class<T> clazz;

    private final Class<?> fromClass;

    private final Class<?> toClass;

    private final Map<String, Map<String, String>> mapMap;

    /**
     * 通过{methods}建立全局映射关系
     *
     * @param methods 方法数组
     * @return 全局映射关系
     */
    private static Map<String, Map<String, String>> getMap(Method[] methods) {
        Assert.notNull(methods, "没有定义方法");
        Map<String, Map<String, String>> fromToMap = new LinkedHashMap<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(ConverterResults.class)) {
                ConverterResults converterResults = m.getAnnotation(ConverterResults.class);
                String id = converterResults.id();
                if (fromToMap.containsKey(id)) {
                    continue;
                }
                ConverterResult[] results = converterResults.value();
                Map<String, String> paramMap = Arrays.stream(results).collect(Collectors.toMap(ConverterResult::from, ConverterResult::to));
                Assert.isTrue(!fromToMap.containsKey(id), String.format("@ConverterResults id '%s' 重复", id));
                fromToMap.put(id, paramMap);
            }
        }
        return fromToMap;
    }

    protected ConverterInvocationHandler(Class<T> clazz) {
        Assert.notNull(clazz, "参数 'clazz' 不可为空");
        this.clazz = clazz;
        //获取FROM与TO类型
        Type[] arguments = ((ParameterizedType) clazz.getGenericInterfaces()[0]).getActualTypeArguments();
        this.fromClass = (Class<?>) arguments[0];
        this.toClass = (Class<?>) arguments[1];
        this.mapMap = getMap(clazz.getMethods());
    }

    protected Class<?> getFromClass() {
        return fromClass;
    }

    protected Class<?> getToClass() {
        return toClass;
    }

    protected Map<String, Map<String, String>> getMapMap() {
        return mapMap;
    }

    public T instance() {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case "convert":
                return process(proxy, method, args[0], false, getToClass());
            case "reverse":
                return process(proxy, method, args[0], true, getFromClass());
            default:
                return method.invoke(proxy, args);
        }
    }

    /**
     * 数据转换处理过程
     * 实例化目标对象，将源对象内容根据映射关系，传递数据
     *
     * @param proxy       代理对象
     * @param method      方法
     * @param origin      对应的要转换的源对象
     * @param reverse     是否逆向转换
     * @param targetClass 目标Class
     * @return 处理后的对象
     * @throws Exception 异常
     */
    protected abstract Object process(Object proxy, Method method, Object origin, boolean reverse, Class<?> targetClass) throws Exception;

    /**
     * 获取要执行的方法注解里配置的映射关系
     *
     * @param method 方法
     * @return 映射关系
     */
    protected Map<String, String> getMethodMap(Method method) {
        Map<String, String> map = new LinkedHashMap<>();
        //获取映射编号
        String[] mapIdArray = new String[0];
        ConverterResults converterResults = method.getAnnotation(ConverterResults.class);
        ConverterResultsMap converterResultsMap = method.getAnnotation(ConverterResultsMap.class);
        if (converterResultsMap != null) {
            mapIdArray = converterResultsMap.value();
        }
        if (converterResults != null) {
            int length = mapIdArray.length;
            mapIdArray = Arrays.copyOf(mapIdArray, length + 1);
            mapIdArray[length] = converterResults.id();
        }
        for (String id : mapIdArray) {
            Map<String, String> stringMap = (Map<String, String>) getMapMap().get(id);
            Assert.isTrue(stringMap != null, String.format("检查@ConverterResults是否有编号 '%s' 对应的映射关系", id));
            map.putAll(stringMap);
        }
        return map;
    }

    /**
     * 忽略访问权限，递归获取父类及本身所有的字段
     *
     * @param targetClass 目标类
     * @return 字段数组
     */
    protected Field[] getFields(Class<?> targetClass) {
        if (targetClass == Object.class) {
            return targetClass.getDeclaredFields();
        }
        Field[] declaredFields = targetClass.getDeclaredFields();
        Field[] superDeclaredFields = getFields(targetClass.getSuperclass());

        //合并数组
        Field[] fields = Arrays.copyOf(declaredFields, declaredFields.length + superDeclaredFields.length);
        System.arraycopy(superDeclaredFields, 0, fields, declaredFields.length, superDeclaredFields.length);
        return fields;
    }

    /**
     * 忽略访问权限，通过名称查找字段
     *
     * @param fields 父类及本身的所有字段
     * @param name   字段名称
     * @return 字段
     * @throws NoSuchFieldException 无字段异常
     */
    protected Field getField(Field[] fields, String name) throws NoSuchFieldException {
        for (Field x : fields) {
            if (name.equals(x.getName())) {
                return x;
            }
        }
        throw new NoSuchFieldException(String.format("字段 '%s' 不存在", name));
    }
}
