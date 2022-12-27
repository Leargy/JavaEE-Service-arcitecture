package dto.validators;


import dto.CoordinatesDTOImpl;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class CoordinatesValidatorMemberImpl extends AbstractValidationMember<CoordinatesDTOImpl> implements Serializable {
    @Override
    @ValidationMemberRequiredClass(CoordinatesDTOImpl.class)
    public void validate(CoordinatesDTOImpl validationObject) throws ValidationException {
        if(validationObject == null) return;

        if(validationObject.getX() == null) throw new ValidationException("Value \"X\" of \"Coordinates\" must be specified!");
        if(validationObject.getY() == null) throw new ValidationException("Value \"Y\" of \"Coordinates\" must be specified!");

        if(validationObject.getX() > 736) throw new ValidationException("Value \"X\" of \"Coordinates\" must be lower or equals 736!");
        if(validationObject.getX() <= -119) throw new ValidationException("Value \"X\" of \"Coordinates\" must be grater than -119!");
    }
}
