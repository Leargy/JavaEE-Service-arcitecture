package dto.validators;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractValidationMember<K> implements ValidationMember<K>{
    private AbstractValidationMember nextMember = null;

    public final void startValidation(Object object) throws ValidationException, NoSuchMethodException{
        Method method = this.getClass().getMethod("validate", Object.class);
        method.setAccessible(true);

        String memberRequiredValueTypeName = method.getDeclaredAnnotation(ValidationMemberRequiredClass.class).value().getName();
        String typeName = object.getClass().getTypeName();
        if(typeName.equals(memberRequiredValueTypeName)) {
            this.validate((K)object);
        }else {
            List<Field> fieldList = Arrays.stream(object.getClass().getDeclaredFields()).filter(z -> z.getType().getTypeName().equals(memberRequiredValueTypeName)).collect(Collectors.toList());
            for (Field currentField : fieldList) {
                currentField.setAccessible(true);
                try {
                    this.validate((K) currentField.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(getNextMember() != null) {
            nextMember.startValidation(object);
        }
    }

    public final void setNextMember(AbstractValidationMember nextMember) {
        this.nextMember = nextMember;
    }

    public final AbstractValidationMember getNextMember() {
        return nextMember;
    }
}
