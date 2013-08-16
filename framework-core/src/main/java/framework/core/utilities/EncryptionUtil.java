package framework.core.utilities;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EncryptionUtil {

    private static Cryptography cryptography;

    public static String decrypt(String encryptedMessage) {
        return cryptography.decrypt(encryptedMessage);
    }

    public static String encrypt(String message) {
        return cryptography.encrypt(message);
    }

    @Inject
    protected EncryptionUtil(Cryptography cryptography) {
        EncryptionUtil.cryptography = cryptography;
    }
}
