package top.meethigher.converter.annotation;

import java.lang.annotation.*;

/**
 * 单字段的映射关系
 * <p>
 * <b>使用示例</b><br>
 * <pre>
 *
 * interface DotConverter extends ObjectConverter&lt;Dot, DotDto&gt; {
 *
 *     &#64;ConverterResults(value = {
 *             &#64;ConverterResult(from = "person.name", to = "mingCheng"),
 *             &#64;ConverterResult(from = "superPerson.skill", to = "superSkill"),
 *             &#64;ConverterResult(from = "superPerson.name", to = "superMingCheng")
 *     })
 *     &#64;Override
 *     DotDto convert(Dot dot);
 *
 *     &#64;ConverterResultsMap("default")
 *     &#64;Override
 *     Dot reverse(DotDto dotDto);
 * }
 *
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
     */
    String from() default "";

    /**
     * Returns the to name for applying this mapping.
     *
     * @return the to name
     */
    String to() default "";

}
