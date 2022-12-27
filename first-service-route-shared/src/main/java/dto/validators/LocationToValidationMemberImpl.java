package dto.validators;

import dto.LocationToDTOImpl;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class LocationToValidationMemberImpl extends AbstractValidationMember<LocationToDTOImpl> implements Serializable {


    @Override
    @ValidationMemberRequiredClass(LocationToDTOImpl.class)
    public void validate(LocationToDTOImpl validationObject) throws ValidationException {
        if(validationObject == null) return;
        if(validationObject.getX() == null) throw new ValidationException("Value \"X\" of \"Location-To\" must be specified!");
        if(validationObject.getY() == null) throw new ValidationException("Value \"Y\" of \"Location-To\" must be specified!");
        if(validationObject.getZ() == null) throw new ValidationException("Value \"Z\" of \"Location-To\" must be specified!");
    }
}
