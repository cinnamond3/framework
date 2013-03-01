package framework.core.utilities;

/**
 * Utility for cryptography services.
 * 
 * @author Frederick Yap
 */
public interface Cryptography {

    /**
     * Decrypts the input message.
     * 
     * @param encryptedMessage
     *            the message to be decrypted.
     * @return the decrypted message.
     */
    String decrypt(String encryptedMessage);

    /**
     * Encrypts the input message.
     * 
     * @param message
     *            the message to be encrypted.
     * @return the encrypted message.
     */
    String encrypt(String message);
}
