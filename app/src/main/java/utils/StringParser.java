package utils;

/**
 * Created by rubertu on 21.05.2016.
 */

public class StringParser {

    public static String makeArrayToString(String[] arrayString) {
        StringBuilder builder = new StringBuilder();

        for (String string : arrayString) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(string);
        }

        return builder.toString();
    }
}
