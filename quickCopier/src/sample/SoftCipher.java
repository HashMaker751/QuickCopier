package sample;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class SoftCipher implements CiphComm {
    public static final boolean DEBUG_MODE = true;
    public static final boolean CLEAR_MODE = false;
    private final Cipher encoder;
    private final Cipher decoder;
    private final SecretKeySpec key;
    private final boolean mode;

    public SoftCipher(String uniKey, boolean mode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        this.key = new SecretKeySpec(uniKey.getBytes(StandardCharsets.UTF_8), "AES");
        encoder = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encoder.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        decoder = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decoder.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        this.mode = mode;
    }

    @Override
    public String encodeString(String value) throws BadPaddingException, IllegalBlockSizeException {
        String out;
        if (mode) System.out.println("[1] Input string : " + value);
        byte[] encryptingText = value.getBytes();
        if (mode) System.out.println("[2] Encrypting bytes : " + Arrays.toString(encryptingText));
        byte[] encryptedText = encoder.doFinal(encryptingText);
        if (mode) System.out.println("[3] Encrypted bytes : " + Arrays.toString(encryptedText));
        out = new String(Base64.getEncoder().encode(encryptedText));
        return out;
    }

    @Override
    public String decodeString(String value) throws BadPaddingException, IllegalBlockSizeException {
        String out;
        if (mode) System.out.println("[1] Input string : " + value);
        byte[] decryptingText = Base64.getDecoder().decode(value.getBytes());
        if (mode) System.out.println("[2] Decrypting bytes :" + Arrays.toString(decryptingText));
        byte[] decryptedText = decoder.doFinal(decryptingText);
        if (mode) System.out.println("[3] Decrypted bytes : " + Arrays.toString(decryptedText));
        out = new String(decryptedText);
        return out;
    }

    @Override
    public void encodeFile(String fileName) {

    }

    @Override
    public void decodeFile(String fileName) {

    }
}
