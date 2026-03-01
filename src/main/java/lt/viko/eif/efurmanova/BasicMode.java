package lt.viko.eif.efurmanova;

public class BasicMode extends VigenereCipher{

    private static final char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int size = alphabet.length;

    private String prepareKey( String key){
        return key.toUpperCase().replaceAll("[^A-Z]", "");
    }

    @Override
    public String encrypt (String text, String key){
    System.out.println("Basics.encript() - implementuoti reikia");
        return "";
    };
    @Override
    public String decrypt (String text, String key){
        System.out.println("Basics.decript() - implementuoti reikia");
        return "";
    }

    @Override
    protected boolean isValidKey(String key) {
        String cleanKey = key.toUpperCase().replaceAll("[^A-Z]", "");
        return !cleanKey.isEmpty();
    }

    @Override
    public String getModeName() {
        return "MAIN MODE (Latin alphabet only";
    }

    @Override
    public String getAlphabetDescription() {
        return "Alphabet: " + String.valueOf (alphabet) + " (length: " + size + ")";
    }


}
