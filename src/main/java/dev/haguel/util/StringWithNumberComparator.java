package dev.haguel.util;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Custom comparator that can compare string with numbers right
public class StringWithNumberComparator implements Comparator<String> {
    private static final Pattern splitter = Pattern.compile("([-]?[\\d]+(?:\\.[\\d]+)?|[\\D]+)");
    private static final Pattern matchNumber = Pattern.compile("[-]?\\d+(?:\\.[\\d]+)?");

    @Override
    public int compare(String s1, String s2) {
        if(s1.equals(s2)) return 0;

        Matcher m1 = splitter.matcher(s1);
        Matcher m2 = splitter.matcher(s2);

        while(m1.find() && m2.find()) {
            if(matchNumber.matcher(m1.group()).matches() && matchNumber.matcher(m2.group()).matches()) {
                int numberCompareResult = Double.compare(Double.parseDouble(m1.group()), Double.parseDouble(m2.group()));
                if(numberCompareResult != 0) return numberCompareResult ;
            }
            else {
                if(m1.group().compareTo(m2.group()) != 0) return m1.group().compareTo(m2.group());
            }
        }
        return m1.matches() ? 1 : (m2.matches() ? -1 : 0);
    }
}
