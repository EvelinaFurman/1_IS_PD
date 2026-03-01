package lt.viko.eif.efurmanova;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        System.out.println(" ===================================");
        System.out.println("    VIGENERE CIPHER SYSTEM V1.0");
        System.out.println(" ====================================");

        try {
            VigenereCipher cipher = selectMode(scanner);
            System.out.println("\nYou chose: " + cipher.getModeName());
            System.out.println("  " + cipher.getAlphabetDescription());

            System.out.print("\nEnter the the text you want to encrypt: ");
            String text = scanner.nextLine();

            System.out.print("Now please enter the key: ");
            String key = scanner.nextLine();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static VigenereCipher selectMode(Scanner scanner) {
        while (true) {
            System.out.println("Please select mode");
            System.out.println("1. Basic Vigenere Mode (Latin alphabet only)");
            System.out.println("2. Extended Vigenere Mode (ASCII 32-126");
            System.out.println(" Your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return new BasicMode();
                case "2":
                    return new ExtendedMode();
                default:
                    System.out.println("Error: invalid choice, please try again");
            }
        }
    }
}
