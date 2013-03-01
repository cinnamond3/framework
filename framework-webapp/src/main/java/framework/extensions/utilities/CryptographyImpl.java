package framework.extensions.utilities;

import javax.inject.Inject;
import javax.inject.Named;

import org.jasypt.encryption.StringEncryptor;

import framework.core.utilities.Cryptography;

@Named
public class CryptographyImpl implements Cryptography {

    private final StringEncryptor encryptor;

    @Inject
    public CryptographyImpl(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String decrypt(String encryptedMessage) {
        return this.encryptor.decrypt(encryptedMessage);
    }

    @Override
    public String encrypt(String message) {
        return this.encryptor.encrypt(message);
    }

}
