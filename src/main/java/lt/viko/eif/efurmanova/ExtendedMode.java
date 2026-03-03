package lt.viko.eif.efurmanova;

public class ExtendedMode extends VigenereCipher {
    private static final int MIN_ASCII = 32;
    private static final int MAX_ASCII = 126;
    private static final int RANGE = MAX_ASCII - MIN_ASCII + 1;

    @Override
    public String encrypt(String text, String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key cannot be empty");
        }

        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (isInRange(currentChar)) {
                char encryptedChar = processChar(currentChar, getKeyChar(key, keyIndex), true);
                result.append(encryptedChar);
                keyIndex++;
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key cannot be empty");
        }
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);


            if (isInRange(currentChar)) {
                char decryptedChar = processChar(currentChar, getKeyChar(key, keyIndex), false);
                result.append(decryptedChar);
                keyIndex++;
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    private char processChar(char textChar, char keyChar, boolean isEncrypted) {
        int textValue = textChar - MIN_ASCII;
        int keyValue = keyChar - MIN_ASCII;

        int resultValue;
        if (isEncrypted) {
            resultValue = (textValue + keyValue) % RANGE;
        } else {
            resultValue = (textValue - keyValue + RANGE) % RANGE;
        }
        return (char) (resultValue + MIN_ASCII);
    }

    private boolean isInRange(char textChar) {
        return textChar >= MIN_ASCII && textChar <= MAX_ASCII;
    }

    private char getKeyChar(String key, int index) {
        return key.charAt(index % key.length());
    }

    @Override
    protected boolean isValidKey(String key) {
        return key != null && !key.isEmpty();
    }

    @Override
    public String getModeName() {
        return "EXTENDED MODE (ASCII 32-126)";
    }

    @Override
    public String getAlphabetDescription() {
        return "ASCII length: " + MIN_ASCII + "-" + MAX_ASCII +
                " (Number of symbols: " + RANGE + ")";
    }
}