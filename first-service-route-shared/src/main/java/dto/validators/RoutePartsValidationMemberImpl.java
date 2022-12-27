package dto.validators;

import dto.RequestRouteDTOImpl;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class RoutePartsValidationMemberImpl extends AbstractValidationMember<RequestRouteDTOImpl> implements Serializable {

    @Override
    @ValidationMemberRequiredClass(RequestRouteDTOImpl.class)
    public void validate(RequestRouteDTOImpl validationObject) throws ValidationException {
        String routeName = validationObject.getName();
        if(routeName == null) {
            throw new ValidationException("Route name must be specified!");
        } else if(routeName.equals("")) {
            throw new ValidationException("Route name must be specified!");
        }

        Float distance = validationObject.getDistance();

        if(distance == null) {
            throw new ValidationException("Route distance must be specified!");
        }else if(distance <= 1) {
            throw new ValidationException("Route distance must be grater that 1!");
        }
    }
}
