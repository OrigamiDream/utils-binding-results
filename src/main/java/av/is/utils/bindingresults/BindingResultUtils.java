package av.is.utils.bindingresults;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BindingResultUtils implements IBindingResult {

    private final List<BindingResultProvider> providers;

    public BindingResultUtils() {
        providers = new ArrayList<>();
        providers.add(new AbstractBindingResultProvider());
        providers.add(new BindingResultWrapperProvider());
    }

    @Override
    public void removeError(BindingResult bindingResult, String field) {
        providers.stream()
                .filter(provider -> provider.isProvidable(bindingResult))
                .forEach(provider -> provider.removeError(bindingResult, field));
    }

    @Override
    public void clearError(BindingResult bindingResult) {
        providers.stream()
                .filter(provider -> provider.isProvidable(bindingResult))
                .forEach(provider -> provider.clearError(bindingResult));
    }

    @Override
    public boolean hasError(BindingResult bindingResult, String field) {
        return providers.stream()
                .filter(provider -> provider.isProvidable(bindingResult))
                .anyMatch(provider -> provider.hasError(bindingResult, field));
    }

    @Override
    public List<ObjectError> getErrorsByField(BindingResult bindingResult, String field) {
        return providers.stream()
                .filter(provider -> provider.isProvidable(bindingResult))
                .flatMap(provider -> provider.getErrorsByField(bindingResult, field).stream())
                .collect(Collectors.toList());
    }
}
