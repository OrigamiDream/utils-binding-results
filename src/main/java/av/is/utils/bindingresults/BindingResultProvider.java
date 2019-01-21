package av.is.utils.bindingresults;

import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface BindingResultProvider extends IBindingResult {

    boolean isProvidable(BindingResult bindingResult);

    default List<ObjectError> reflectErrors(BindingResult bindingResult) {
        try {
            Field field = AbstractBindingResult.class.getDeclaredField("errors");
            field.setAccessible(true);
            return (List<ObjectError>) field.get(bindingResult);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}