package av.is.utils.bindingresults;

import org.springframework.validation.BindingResult;

import java.util.Arrays;

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

}
