package lt.viko.eif.efurmanova;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        boolean running = true;

        while (running) {
            try {
                VigenereCipher cipher = selectMode();
                if (cipher == null) {
                    running = false;
                    break;
                }

                String text = getInputText();
                String key = getKey(cipher);
                processCipher(cipher, text, key);

                System.out.println("\nPlease press ENTER to return to the main menu...");
                scanner.nextLine();

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        printGoodbye();
    }

    private static void printWelcomeMessage() {
        System.out.println(" ===================================");
        System.out.println("    VIGENERE CIPHER SYSTEM V2.0");
        System.out.println(" ====================================");
    }

    private static VigenereCipher selectMode() {
        while (true) {
            System.out.println("\nPlease select mode:");
            System.out.println("1. Basic Vigenere Mode (Latin alphabet only)");
            System.out.println("2. Extended Vigenere Mode (ASCII 32-126)");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "0":
                    return null;

                case "1":
                    VigenereCipher basicMode = new BasicMode();
                    System.out.println("\nYou chose: " + basicMode.getModeName());
                    System.out.println(" " + basicMode.getAlphabetDescription());
                    return basicMode;

                case "2":
                    VigenereCipher extendedMode = new ExtendedMode();
                    System.out.println("\nYou chose: " + extendedMode.getModeName());
                    System.out.println(" " + extendedMode.getAlphabetDescription());
                    return extendedMode;

                default:
                    System.out.println("Error: invalid choice, please try again");
            }
        }
    }

    private static String getInputText() {
        System.out.print("Please enter the text: ");
        return scanner.nextLine();
    }

    private static String getKey(VigenereCipher cipher) {
        while (true) {
            System.out.print("Please enter the key: ");
            String key = scanner.nextLine();

            if (cipher instanceof BasicMode) {
                String cleanCode = key.toUpperCase().replaceAll("[^A-Z]", "");
                if (cleanCode.isEmpty()) {
                    System.out.println("Error: The key has to have at least one letter");
                    continue;
                }
                return key;
            } else {
                if (key.isEmpty()) {
                    System.out.println("Error: The key cannot be empty");
                    continue;
                }
                return key;
            }
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
                System.out.println("Decryption was successful!");
            } else {
                System.out.println("Ups, something is wrong, decryption failed!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printGoodbye() {
        System.out.println("=====================================");
        System.out.println("See you soon!");
        System.out.println("=====================================");
    }
}