package utils;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> makeDiff(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<String>();

        for (int i = 0; i < list1.size(); i++) {
            if (!list2.contains(list1.get(i))) {
                diff.add(list1.get(i));
            }
        }

        return diff;
    }
}
