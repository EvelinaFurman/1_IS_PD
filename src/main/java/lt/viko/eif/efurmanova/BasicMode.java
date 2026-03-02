package lt.viko.eif.efurmanova;

public class BasicMode extends VigenereCipher {

    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int size = alphabet.length;

    private String prepareKey(String key) {
        return key.toUpperCase().replaceAll("[^A-Z]", "");
    }

    @Override
    public String encrypt(String text, String key) {
        String cleanKey = prepareKey(key);
        if (!isValidKey(cleanKey)) {
            throw new IllegalArgumentException("Invalid key");
        }
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);
            if (Character.isLetter(currChar)) {
                char encryptedChar = processChar(currChar, getKeyChar(cleanKey, keyIndex), true);
                result.append(encryptedChar);
                keyIndex++;
            } else {
                result.append(currChar);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        String cleanKey = prepareKey(key);
        if (!isValidKey(cleanKey)) {
            throw new IllegalArgumentException("Invalid key");
        }
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                char decryptedChar = processChar(currentChar,
                        getKeyChar(cleanKey, keyIndex),
                        false);
                result.append(decryptedChar);
                keyIndex++;
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    private char processChar(char textChar, char keyChar, boolean isEncrypted) {
        boolean isLowerCase = Character.isLowerCase(textChar);
        char upperTextChar = Character.toUpperCase(textChar);

        int textPosition = findCharIndex(upperTextChar);
        int keyPosition = findCharIndex(keyChar);

        int resultPos;
        if (isEncrypted) {
            resultPos = (textPosition + keyPosition) % size;
        } else {
            resultPos = (textPosition - keyPosition + size) % size;
        }
        char charResult = alphabet[resultPos];
        return isLowerCase ? Character.toLowerCase(charResult) : charResult;
    }

    private int findCharIndex(char charText) {
        for (int i = 0; i < size; i++) {
            if (alphabet[i] == charText) {
                return i;
            }
        }
        return -1;
    }

        private char getKeyChar( String key, int index) {
            return key.charAt(index % key.length());
        }
        @Override
        protected boolean isValidKey (String key){
            return key != null && !key.isEmpty();
        }

        @Override
        public String getModeName () {
            return "MAIN MODE (Latin alphabet only";
        }

        @Override
        public String getAlphabetDescription () {
            return "Alphabet: " + String.valueOf(alphabet) + " (length: " + size + ")";
        }


    }
