package top.meethigher.converter.utils;

/**
 * 判空
 *
 * @author chenchuancheng
 * @since 2022/10/20 17:11
 */
public class Assert {

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean flag, String message) {
        if (!flag) {
            throw new IllegalArgumentException(message);
        }
    }
}
