package lt.viko.eif.efurmanova;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(" ===================================");
        System.out.println("    VIGENERE CIPHER SYSTEM V1.0");
        System.out.println(" ====================================");

        try {
            VigenereCipher cipher = selectMode();
            String text = getInputText();
            String key = getKey(cipher);

            processCipher(cipher, text, key);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static VigenereCipher selectMode() {
        while (true) {
            System.out.println("Please select mode");
            System.out.println("1. Basic Vigenere Mode (Latin alphabet only)");
            System.out.println("2. Extended Vigenere Mode (ASCII 32-126");
            System.out.println(" Your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    VigenereCipher basicMode = new BasicMode();
                    System.out.println("\n You chose: " + basicMode);
                    System.out.println(" " + basicMode.getAlphabetDescription());
                    return new BasicMode();
                case "2":
                    System.out.println("Extended Vigenere Mode (ASCII 32-126)");
                    return new ExtendedMode();
                default:
                    System.out.println("Error: invalid choice, please try again");
            }
        }
    }

    private static String getInputText() {
        System.out.println("Please enter the text: ");
        return scanner.nextLine();
    }

    private static String getKey(VigenereCipher cipher) {
        while (true) {
            System.out.println("Please enter the key: ");
            String key = scanner.nextLine();

            if (cipher instanceof BasicMode) {
                String cleanCode = key.toUpperCase().replaceAll("[^A-Z]", "");
                if (cleanCode.isEmpty()) {
                    System.out.println("Error: The key has to have at least one letter");
                    continue;
                }
                return key;
            }
            return key;
        }
    }

    private static void processCipher(VigenereCipher cipher, String text, String key) {
        System.out.println("---------------------------------------------");
        System.out.println("Encryption in progress...");
        System.out.println("-----------------------------------------------");

        try {
            String encrypted = cipher.encrypt(text, key);
            String decrypted = cipher.decrypt(encrypted, key);

            System.out.println("Primary text: " + text);
            System.out.println("Key: " + key);
            System.out.println("Encrypted text: " + encrypted);
            System.out.println("Decrypted text: " + decrypted);

            if (text.equals(decrypted)) {
                System.out.println("Mode works successfully");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
