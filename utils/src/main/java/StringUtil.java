import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class StringUtil {
    public static final String EMPTY = "";
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);


    public StringUtil() {
    }

    public static boolean hasValue(String input) {
        return input != null && !"".equals(input.trim());
    }

    public static boolean hasValue(StringBuffer input) {
        return input != null && !"".equals(input.toString().trim());
    }

    public static String convertNullString(String input) {
        return input != null ? input.trim() : "";
    }

    public static int convertNullInt(String input) {
        return input != null ? Integer.parseInt(input.trim()) : 0;
    }

    public static short convertNullShort(String input) {
        return input != null ? Short.parseShort(input.trim()) : 0;
    }

    public static BigDecimal convertNullBigDecimal(String input) {
        return input != null ? new BigDecimal(input.trim()) : new BigDecimal("0");
    }

    public static long convertNullLong(String input) {
        return input != null ? new Long(input.trim()) : 0L;
    }

    public static String stripNonValidXMLCharacters(String input) {
        if (!hasValue(input)) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            for(int var3 = 0; var3 < input.length(); ++var3) {
                char var2 = input.charAt(var3);
                if (var2 == '\t' || var2 == '\n' || var2 == '\r' || var2 >= ' ' && var2 <= '\ud7ff' || var2 >= '\ue000' && var2 <= '�' || var2 >= 65536 && var2 <= 1114111) {
                    stringBuffer.append(var2);
                }
            }

            return stringBuffer.toString();
        }
    }

    public static boolean isAlphanumeric(String input) {
        if (!hasValue(input)) {
            return false;
        } else {
            char[] listCharacter = input.toCharArray();
            int var2 = listCharacter.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                char var4 = listCharacter[var3];
                if (!Character.isLetterOrDigit(var4)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String removeSpaceInString(String input) {
        return input.replace(" ", "");
    }

    public static String toLowerCamelCase(String input) {
        if (!hasValue(input)) {
            return "";
        } else {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = input.toCharArray();
            for(int i = 0; i < charArray.length; ++i) {
                char character = charArray[i];
                if (!Character.isUpperCase(character)) {
                    break;
                }

                buffer.append(character);
            }

            if (buffer != null && buffer.length() > 0) {
                int var6 = buffer.length();
                if (var6 == 1) {
                    input = input.substring(0, var6).toLowerCase() + input.substring(var6);
                } else {
                    input = input.substring(0, var6 - 1).toLowerCase() + input.substring(var6 - 1);
                }
            }

            return input;
        }
    }

    public static String concatenateString(String input, String input2, String input3) {
        if (!hasValue(input) && !hasValue(input2)) {
            return "";
        } else {
            return hasValue(input) ? input + input3 + convertNullString(input2) : convertNullString(input2);
        }
    }

    public static String removeRemainingStringFromACharOnwards(String input, char var1) {
        if (!hasValue(input)) {
            return "";
        } else {
            int var2;
            if ((var2 = input.indexOf(var1)) > 0) {
                input = input.substring(0, var2);
            }

            return input;
        }
    }
}