package top.meethigher.converter.proxy;


import top.meethigher.converter.ObjectConverter;

/**
 * 实现转换接口Handler
 *
 * @author chenchuancheng
 * @since 2022/10/20 23:29
 */
public class ConverterProvider {


    /**
     * {@link DefaultConverterInvocationHandler}
     *
     * @param clazz 接口class
     * @param <T>   ObjectConverter子对象
     * @return 根据注解实现后的对象实例
     */
    public static <T extends ObjectConverter<?, ?>> T defaultInstance(Class<T> clazz) {
        return new DefaultConverterInvocationHandler<>(clazz).instance();
    }

    /**
     * {@link DotSupportConverterInvocationHandler}
     * @param clazz 接口class
     * @param <T> 泛型
     * @return 根据注解实现后的对象实例
     */
    public static <T extends ObjectConverter<?, ?>> T dotSupportInstance(Class<T> clazz) {
        return new DotSupportConverterInvocationHandler<>(clazz).instance();
    }

}
