package top.meethigher.converter.annotation;

import java.lang.annotation.*;

/**
 * 单字段的映射关系
 * <p>
 * 使用示例
 * <pre>
 * {@code
 * interface DotConverter extends ObjectConverter&lt;Dot, DotDto%gt; {
 *
 *     {@literal @}ConverterResults(value = {
 *             {@literal @}ConverterResult(from = "person.name", to = "mingCheng"),
 *             {@literal @}ConverterResult(from = "superPerson.skill", to = "superSkill"),
 *             {@literal @}ConverterResult(from = "superPerson.name", to = "superMingCheng")
 *     })
 *     {@literal @}Override
 *     DotDto convert(Dot dot);
 *
 *     {@literal @}ConverterResultsMap("default")
 *     {@literal @}Override
 *     Dot reverse(DotDto dotDto);
 * }
 * }
 * </pre>
 *
 * @author chenchuancheng
 * @since 2022/10/20 15:09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConverterResult {

    /**
     * Return the from name(or from label) to map to this argument.
     *
     * @return the from name(or from label)
     * @see ConverterResultsMap
     */
    String from() default "";

    /**
     * Returns the to name for applying this mapping.
     *
     * @return the to name
     */
    String to() default "";

}
