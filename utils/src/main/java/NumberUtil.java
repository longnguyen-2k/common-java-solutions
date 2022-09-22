import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
        public NumberUtil() {
        }

        public static boolean hasValue(Integer input) {
            return input != null;
        }

        public static boolean hasValue(Long input) {
            return input != null;
        }

        public static boolean hasValue(Double input) {
            return input != null;
        }

        public static boolean hasValue(Number input) {
            return input != null;
        }



        public static BigDecimal convertToDecimalAmount(String input, String var1, int var2) {
            BigDecimal var3 = new BigDecimal(input);
            BigDecimal var4 = new BigDecimal(var1);
            var3 = var3.divide(var4).setScale(var2, RoundingMode.HALF_UP);
            return var3;
        }

        public static boolean isNumeric(String input) {
            if (!StringUtil.hasValue(input)) {
                return false;
            } else {
                char[] var1 = input.toCharArray();
                int var2 = var1.length;

                for(int var3 = 0; var3 < var2; ++var3) {
                    char var4 = var1[var3];
                    if (!Character.isDigit(var4)) {
                        return false;
                    }
                }

                return true;
            }
        }

        public static boolean isDecimal(String input) {
            try {
                new BigDecimal(input);
                return true;
            } catch (Exception var2) {
                return false;
            }
        }

        public static boolean isBetween(BigDecimal input, BigDecimal var1, BigDecimal var2) {
            return input.compareTo(var1) > 0 && input.compareTo(var2) < 0;
        }

        public static String toPlainString(BigDecimal input) {
            return null != input ? input.toPlainString() : null;
        }

        public static BigDecimal toBigDecimal(String input) {
            return null != input && "" != input ? new BigDecimal(input) : null;
        }

        public static BigDecimal convertToBigDecimal(Object input) {
            if (input != null && StringUtil.hasValue(input.toString())) {
                try {
                    return new BigDecimal(input.toString());
                } catch (Exception var2) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }






