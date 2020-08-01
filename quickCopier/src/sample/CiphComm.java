package sample;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface CiphComm {
    String encodeString(String value) throws BadPaddingException, IllegalBlockSizeException;

    String decodeString(String value) throws BadPaddingException, IllegalBlockSizeException;

    void encodeFile(String fileName);

    void decodeFile(String fileName);
}
