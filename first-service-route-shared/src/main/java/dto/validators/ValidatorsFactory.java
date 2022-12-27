package dto.validators;

public interface ValidatorsFactory {

    ValidationMember createParamBeanValidator();
    ValidationMember createRequestRouteValidator();

}
