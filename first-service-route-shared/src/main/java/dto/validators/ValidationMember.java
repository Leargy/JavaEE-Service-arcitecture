package dto.validators;

import javax.xml.bind.ValidationException;


public interface ValidationMember<K> {
    void validate(K validationObject) throws ValidationException;
    void startValidation(Object object) throws ValidationException, NoSuchMethodException;
}
