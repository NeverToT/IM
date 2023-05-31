package util;

import java.util.Random;

public class MakePhone {
    private static final String[] PREFIXES = {
            "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
            "150", "151", "152", "153", "155", "156", "157", "158", "159",
            "176", "177", "178",
            "180", "181", "182", "183", "184", "185", "186", "187", "188", "189"
    };

    public static String generatePhoneNumber() {
        Random random = new Random();
        String prefix = PREFIXES[random.nextInt(PREFIXES.length)];
        String suffix = String.format("%08d", random.nextInt(100000000));
        return prefix + suffix;
    }
}
