package framework.extensions.converters;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.DozerConverter;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import framework.core.utilities.Cryptography;

@Named
public class EncryptionConverter extends DozerConverter<String, String> {

    @Inject
    protected EncryptionConverter(Cryptography cryptography) {
        super(String.class, String.class);
        this.cryptography = cryptography;
    }

    private final Cryptography cryptography;

    @Override
    public String convertTo(String source, String destination) {
        return this.convertFrom(source, destination);
    }

    @Override
    public String convertFrom(String source, String destination) {
        try {
            return this.cryptography.decrypt(source);
        } catch (EncryptionOperationNotPossibleException e) {
            return this.cryptography.encrypt(source);
        }
    }

}