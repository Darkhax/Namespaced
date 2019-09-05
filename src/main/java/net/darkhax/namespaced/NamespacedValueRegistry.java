/**
 * This class was created by <Darkhax>. It is distributed as part of the Namespaced library
 * under the LGPL 2.1. You can find the original source on GitHub.
 * https://github.com/Darkhax/Namespaced
 */
package net.darkhax.namespaced;

import javax.annotation.Nullable;

/**
 * This implementation of NamespacedRegistry provides some additional methods that can lead to
 * cleaner code.
 * 
 * @author Tyler Hancock (Darkhax)
 *
 * @param <T> The type of value held by the registry.
 */
public class NamespacedValueRegistry<T extends INamespacedValue> extends NamespacedRegistry<T> {
    
    /**
     * Registers a value directly with the registry using the identifier from
     * {@link INamespacedValue#getIdentifier()}.
     * 
     * @param value The value to register.
     * @return The value that was previously mapped to the identifier. This will be null if
     *         there is no previous value.
     */
    @Nullable
    public T register (T value) {
        
        return this.register(value.getIdentifier(), value);
    }
}