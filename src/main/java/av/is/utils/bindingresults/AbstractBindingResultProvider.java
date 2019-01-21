package av.is.utils.bindingresults;

import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractBindingResultProvider implements BindingResultProvider {

    @Override
    public boolean isProvidable(BindingResult bindingResult) {
        return bindingResult instanceof AbstractBindingResult;
    }

    @Override
    public void removeError(BindingResult bindingResult, String field) {
        List<ObjectError> errors = reflectErrors(bindingResult);
        errors.removeIf(objectError -> objectError.getObjectName().equals(field));
    }

    @Override
    public void clearError(BindingResult bindingResult) {
        reflectErrors(bindingResult).clear();
    }

    @Override
    public boolean hasError(BindingResult bindingResult, String field) {
        return reflectErrors(bindingResult).stream().anyMatch(error -> error.getObjectName().equalsIgnoreCase(field));
    }

    @Override
    public List<ObjectError> getErrorsByField(BindingResult bindingResult, String field) {
        return reflectErrors(bindingResult).stream().filter(error -> error.getObjectName().equalsIgnoreCase(field)).collect(Collectors.toList());
    }
}

