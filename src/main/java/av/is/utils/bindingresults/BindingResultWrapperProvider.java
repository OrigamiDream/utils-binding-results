package av.is.utils.bindingresults;

import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;

public class BindingResultWrapperProvider implements BindingResultProvider {

    private final BindingResultProvider bindingResultProvider;

    public BindingResultWrapperProvider() {
        this.bindingResultProvider = new AbstractBindingResultProvider();
    }

    private BindingResult wrappedBindingResult(BindingResult bindingResult) {
        try {
            Field field = bindingResult.getClass().getDeclaredField("bindingResult");
            field.setAccessible(true);
            return (BindingResult) field.get(bindingResult);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isProvidable(BindingResult bindingResult) {
        try {
            bindingResult.getClass().getDeclaredField("bindingResult");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    @Override
    public void removeError(BindingResult bindingResult, String field) {
        bindingResultProvider.removeError(wrappedBindingResult(bindingResult), field);
    }

    @Override
    public void clearError(BindingResult bindingResult) {
        bindingResultProvider.clearError(wrappedBindingResult(bindingResult));
    }

    @Override
    public boolean hasError(BindingResult bindingResult, String field) {
        return bindingResultProvider.hasError(bindingResult, field);
    }
}
