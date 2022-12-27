package dto.validators;


import dto.LocationFromDTOImpl;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class LocationFromValidationMemberImpl extends AbstractValidationMember<LocationFromDTOImpl> implements Serializable {
    @Override
    @ValidationMemberRequiredClass(LocationFromDTOImpl.class)
    public void validate(LocationFromDTOImpl validationObject) throws ValidationException {
        if(validationObject == null) return;
        if(validationObject.getY() == null) throw new ValidationException("Value \"Y\" of \"Location-From\" must be specified!");
        if(validationObject.getZ() == null) throw new ValidationException("Value \"Z\" of \"Location-From\" must be specified!");
    }
}
