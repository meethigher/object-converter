package top.meethigher.converter.proxy;


import top.meethigher.converter.ObjectConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Proxy代理对象生成器
 * <pre>
 * 目前提供两种方式
 * {@link DefaultConverterInvocationHandler} 匹配注解里配置的映射关系，支持同类型同字段自动转换、支持多继承
 * {@link DotSupportConverterInvocationHandler} 仅匹配注解里配置的映射关系，支持多继承、支持层级转换(比如person.name&lt;=&gt;mingCheng)
 * 基于.语法的Proxy代理对象生成器
 * 思路借鉴了Aviator，本来整个这块一开始尝试用[Aviator](https://github.com/killme2008/aviatorscript)来实现，但是Aviator只支持读取，而写入支持类型只是数值型，所以还要手动实现
 * </pre>
 *
 * @author chenchuancheng
 * @since 2022/10/25 11:17
 */
public class DotSupportConverterInvocationHandler<T extends ObjectConverter<?, ?>> extends ConverterInvocationHandler<T> {
    protected DotSupportConverterInvocationHandler(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected Object process(Object proxy, Method method, Object origin, boolean reverse, Class<?> targetClass) throws Exception {
        //通过无参构造函数，实例化目标对象。
        Constructor<?> constructor = targetClass.getConstructor();
        constructor.setAccessible(true);
        Object target = constructor.newInstance();

        Map<String, String> methodMap = getMethodMap(method);
        methodMap.forEach((from, to) -> {
            try {
                fastSetProperty(reverse ? from : to, fastGetProperty(reverse ? to : from, origin), target);
            } catch (Exception ignore) {
            }
        });
        return target;
    }

    /**
     * 快速取值
     *
     * @param property 属性字段，支持.语法，表示层级字段，如person.name
     * @param obj      取值对象
     * @return 值
     */
    private Object fastGetProperty(String property, Object obj) throws NoSuchFieldException, IllegalAccessException {
        if (!property.contains(".")) {
            Field[] fields = getFields(obj.getClass());
            if (fields == null) {
                return null;
            }
            Field field = getField(fields, property);
            field.setAccessible(true);
            return field.get(obj);
        }
        String head = property.split("\\.")[0];
        String back = property.substring(property.indexOf(".") + 1);
        Field field = obj.getClass().getDeclaredField(head);
        field.setAccessible(true);
        Object o = field.get(obj);
        if (o == null) {
            return null;
        }
        return fastGetProperty(back, o);
    }

    /**
     * 快速赋值
     * 赋值时，要注意有层级时，需要将层级对象创建出来
     *
     * @param property 属性字段，支持.语法，表示层级字段，如person.name
     * @param value    值
     * @param obj      赋值对象
     */
    private void fastSetProperty(String property, Object value, Object obj) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        if (!property.contains(".")) {
            Field[] fields = getFields(obj.getClass());
            if (fields == null) {
                return;
            }
            Field field = getField(fields, property);
            field.setAccessible(true);
            field.set(obj, value);
            return;
        }
        String head = property.split("\\.")[0];
        String back = property.substring(property.indexOf(".") + 1);
        Field field = obj.getClass().getDeclaredField(head);
        field.setAccessible(true);
        Object o = field.get(obj);
        if (o == null) {
            //通过无参构造函数，实例化目标对象。
            Constructor<?> constructor = field.getType().getConstructor();
            constructor.setAccessible(true);
            o = constructor.newInstance();
            field.set(obj, o);
        }
        fastSetProperty(back, value, o);
    }
}
