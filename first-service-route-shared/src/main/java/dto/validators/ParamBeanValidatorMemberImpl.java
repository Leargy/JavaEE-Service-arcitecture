package dto.validators;


import dto.ParamBeanDTOImpl;
import model.Route;

import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.lang.reflect.Field;

public class ParamBeanValidatorMemberImpl extends AbstractValidationMember<ParamBeanDTOImpl> implements Serializable {
    @Override
    @ValidationMemberRequiredClass(ParamBeanDTOImpl.class)
    public void validate(ParamBeanDTOImpl validationObject) throws ValidationException {

        if(validationObject.getOffSet() != null && validationObject.getOffSet() < 0) {
            throw new ValidationException("Offset must be greater or equals 0!");
        }else if ((validationObject.getOffSet() == null && validationObject.getPageSize() != null) || (validationObject.getOffSet() != null && validationObject.getPageSize() == null)){
            throw new ValidationException("Offset must be specified with page size!");
        }
        if(validationObject.getPageSize() != null && validationObject.getPageSize() <= 0) {
            throw new ValidationException("Page size must be greater than 0!");
        }

        String[] orderByValues = validationObject.getOrderBy();
        if(orderByValues == null) return;
        for(String str : orderByValues) {

            if (!str.matches("([A-Za-z]+_)*([A-Za-z]+)"))
                throw new ValidationException("OrderBy doesn't match patter!");

            String[] path = str.split("_");

            Class classObj = Route.class;
            Field[] fields = classObj.getDeclaredFields();

            int fitsPointer;
            if (path.length > 0) {
                for (int i = 0; i < path.length; ++i) {
                    fitsPointer = -1;
                    for (int j = 0; j < fields.length; ++j) {
                        if (fields[j].getName().equals(path[i])) {
                            fitsPointer = j;
                            break;
                        }
                    }

                    if (fitsPointer == -1) {
                        throw new ValidationException("No such orderBy name \"" + path[i] + "\" in \"" + classObj.getName() + "\" for \"" + str + "\"");
                    }

                    if (i != path.length - 1) {
                        fields = fields[fitsPointer].getType().getDeclaredFields();
                    }
                }
            }
        }
    }
}
