package av.is.utils.bindingresults;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface IBindingResult {

    /**
     * Removes a specific error which defined in BindingResult.
     */
    void removeError(BindingResult bindingResult, String field);

    /**
     * Remove a chunk of errors which defined in BindingResult.
     */
    default void removeErrors(BindingResult bindingResult, String... fields) {
        Arrays.stream(fields).forEach(field -> removeError(bindingResult, field));
    }

    /**
     * Clear all errors defined in BindingResult.
     */
    void clearError(BindingResult bindingResult);

    /**
     * Checks which error has defined in BindingResult by error field.
     */
    boolean hasError(BindingResult bindingResult, String field);

    /**
     * Get all errors which its field name equals to 'field'.
     */
    List<ObjectError> getErrorsByField(BindingResult bindingResult, String field);

    /**
     * Provides stream wrapper of method: getErrorsByField()
     */
    default Stream<ObjectError> errorsByField(BindingResult bindingResult, String field) {
        return getErrorsByField(bindingResult, field).stream();
    }

}
