package util;

import java.util.Random;

public class RandomEmailGenerator {
    private static final String[] DOMAINS = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com" };
    private static final String[] PREFIXES = { "john", "jane", "smith", "doe", "test" };

    public static String generateRandomEmail() {
        Random random = new Random();
        String prefix = PREFIXES[random.nextInt(PREFIXES.length)];
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];

        int randomNumber = random.nextInt(9999) + 1; // Generate a random number between 1 and 9999

        return prefix + randomNumber + "@" + domain;
    }

    public static void main(String[] args) {
        String randomEmail = generateRandomEmail();
        System.out.println("Random Email: " + randomEmail);
    }
}
