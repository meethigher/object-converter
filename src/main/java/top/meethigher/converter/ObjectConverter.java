package top.meethigher.converter;

/**
 * 转换器
 *
 * @author chenchuancheng
 * @since 2022/10/20 15:03
 */
public interface ObjectConverter<FROM, TO> {

    /**
     * 正向转换，将{from}转换成目标对象TO
     *
     * @param from 源
     * @return TO对象
     */
    TO convert(FROM from);

    /**
     * 逆向转换，将{to}转换成目标对象FROM
     *
     * @param to 源
     * @return FROM对象
     */
    FROM reverse(TO to);

}
