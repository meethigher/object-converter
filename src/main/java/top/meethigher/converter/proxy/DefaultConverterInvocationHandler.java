package top.meethigher.converter.proxy;



import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.utils.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Proxy代理对象生成器
 * <pre>
 * 目前提供两种方式
 * {@link DefaultConverterInvocationHandler} 匹配注解里配置的映射关系，支持同类型同字段自动转换、支持多继承
 * {@link DotSupportConverterInvocationHandler} 仅匹配注解里配置的映射关系，支持多继承、支持层级转换(比如person.name&lt;=&gt;mingCheng)
 * </pre>
 * @author chenchuancheng
 * @since 2022/10/25 10:54
 */
public class DefaultConverterInvocationHandler<T extends ObjectConverter<?, ?>> extends ConverterInvocationHandler<T> {

    public DefaultConverterInvocationHandler(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected Object process(Object proxy, Method method, Object origin, boolean reverse, Class<?> targetClass) throws Exception {
        Assert.isTrue(origin != null, "参数不可为 null");
        //获取映射编号，映射编号不论逆转翻转，都不会改变顺序。
        Map<String, String> map = getMethodMap(method);
        //通过无参构造函数，实例化目标对象。
        Constructor<?> constructor = targetClass.getConstructor();
        constructor.setAccessible(true);
        Object target = constructor.newInstance();
        //忽略访问权限，获取本身及父级所有字段
        Field[] targetFields = getFields(targetClass);
        Field[] originFields = getFields(origin.getClass());
        Assert.isTrue(targetFields != null && originFields != null, "对象不存在字段");
        //正转与逆转的逻辑，在对于映射关系额处理时，是不同的。因为映射关系顺序不会变。
        if (reverse) {
            //逆转
            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                String name = map.get(targetField.getName()) == null ? targetField.getName() : map.get(targetField.getName());
                try {
                    Field originField = getField(originFields, name);
                    if (!targetField.getType().equals(originField.getType())) {
                        continue;
                    }
                    originField.setAccessible(true);
                    Object o = originField.get(origin);
                    targetField.set(target, o);
                } catch (NoSuchFieldException ignore) {
                }
            }
        } else {
            //正转
            for (Field originField : originFields) {
                originField.setAccessible(true);
                String name = map.get(originField.getName()) == null ? originField.getName() : map.get(originField.getName());
                try {
                    //考虑继承问题
                    Field targetField = getField(targetFields, name);
                    if (!originField.getType().equals(targetField.getType())) {
                        continue;
                    }
                    targetField.setAccessible(true);
                    Object o = originField.get(origin);
                    targetField.set(target, o);
                } catch (NoSuchFieldException ignore) {
                    //字段不存在，就忽略
                }
            }
        }
        return target;
    }


}
