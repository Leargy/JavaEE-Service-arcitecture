package dto.validators;

import javax.annotation.ManagedBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
public class ValidatorsFactoryImpl implements ValidatorsFactory, Serializable {
    private Map<String, AbstractValidationMember> chainBox;

    public ValidatorsFactoryImpl() {
        chainBox = new HashMap();
        chainBox.put("paramBeanValidator", new ParamBeanValidatorMemberImpl());
        chainBox.put("coordinateValidator", new CoordinatesValidatorMemberImpl());
        chainBox.put("locationFromValidator", new LocationFromValidationMemberImpl());
        chainBox.put("locationToValidator", new LocationToValidationMemberImpl());
        chainBox.put("routePartsValidator", new CoordinatesValidatorMemberImpl());
    }

    @Override
    public ValidationMember createParamBeanValidator() {
        AbstractValidationMember paramBeanValidationMember = chainBox.get("paramBeanValidator");
        AbstractValidationMember coordinateValidationMember = chainBox.get("coordinateValidator");
        AbstractValidationMember locationFromValidationMember = chainBox.get("locationFromValidator");
        AbstractValidationMember locationToValidationMember = chainBox.get("locationToValidator");
        AbstractValidationMember routePartsValidationMember = chainBox.get("routePartsValidator");

        paramBeanValidationMember.setNextMember(coordinateValidationMember);
        coordinateValidationMember.setNextMember(locationFromValidationMember);
        locationFromValidationMember.setNextMember(locationToValidationMember);
        locationToValidationMember.setNextMember(routePartsValidationMember);
        routePartsValidationMember.setNextMember(null);
        return paramBeanValidationMember;
    }

    @Override
    public ValidationMember createRequestRouteValidator() {
        AbstractValidationMember coordinateValidationMember = chainBox.get("coordinateValidator");
        AbstractValidationMember locationFromValidationMember = chainBox.get("locationFromValidator");
        AbstractValidationMember locationToValidationMember = chainBox.get("locationToValidator");
        AbstractValidationMember routePartsValidationMember = chainBox.get("routePartsValidator");

        routePartsValidationMember.setNextMember(locationFromValidationMember);
        locationFromValidationMember.setNextMember(locationToValidationMember);
        locationToValidationMember.setNextMember(coordinateValidationMember);
        coordinateValidationMember.setNextMember(null);
        return routePartsValidationMember;
    }
}
