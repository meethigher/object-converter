package top.meethigher.converter.annotation;

import java.lang.annotation.*;

/**
 * 复用{ConverterResults}
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
 * @author chenchuancheng
 * @since 2022/10/20 15:06
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConverterResultsMap {
    /**
     * Returns result map names to use.
     *
     * @return result map names
     */
    String[] value();
}
