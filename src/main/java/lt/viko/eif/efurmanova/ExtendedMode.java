package lt.viko.eif.efurmanova;

public class ExtendedMode extends VigenereCipher {
    private static final int MIN_ASCII = 32;
    private static final int MAX_ASCII = 126;
    private static final int RANGE = MAX_ASCII - MIN_ASCII + 1;

    @Override
    public String encrypt(String text, String key) {
        System.out.println("ExtendedMode.encrypt() - dar neimplementuota");
        return text;
    }

    @Override
    public String decrypt(String text, String key) {
        System.out.println("ExtendedMode.decrypt() - dar neimplementuota");
        return text;
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


