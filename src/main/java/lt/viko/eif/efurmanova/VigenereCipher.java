package lt.viko.eif.efurmanova;

public abstract class VigenereCipher {

    public abstract String encrypt (String text, String key);
    public abstract String decrypt (String text, String key);

    protected abstract boolean isValidKey (String key);
    public abstract String getAlphabetDescription();

}
