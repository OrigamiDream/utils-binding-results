## BindingResult Utils
 Provide methods for querying, removing, cleaning BindingResult.
 
#### Requires
- Spring Framework Environment
- JDK 8


#### Import / Injection
```java
import av.is.utils.bindingresults.BindingResultUtils;

@Component // Any streotype annotations of Spring Framework.
public class FooComponent {
    
    private final BindingResultUtils bindingResultUtils;
    
    @Autowired
    public FooService(BindingResultUtils bindingResultUtils) {
        this.bindingResultUtils = bindingResultUtils;
    }
}
```

#### Documents
```java
/**
 * Removes a specific error which defined in BindingResult.
 */
void removeError(BindingResult bindingResult, String field);

/**
 * Remove a chunk of errors which defined in BindingResult.
 */
void removeErrors(BindingResult bindingResult, String... fields);

/**
 * Clear all errors defined in BindingResult.
 */
void clearError(BindingResult bindingResult);

/**
 * Checks which error has defined in BindingResult by error field.
 */
boolean hasError(BindingResult bindingResult, String field);
```