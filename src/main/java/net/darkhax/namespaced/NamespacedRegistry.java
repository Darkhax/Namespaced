/**
 * This class was created by <Darkhax>. It is distributed as part of the Namespaced library
 * under the LGPL 2.1. You can find the original source on GitHub.
 * https://github.com/Darkhax/Namespaced
 */
package net.darkhax.namespaced;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * This class represents a registry which uses namespaced identifiers for keys. Several helper
 * methods similar to those from Map and Optional are also included to make certain operations
 * cleaner or more efficient than traditional approaches.
 * 
 * @author Tyler Hancock (Darkhax)
 *
 * @param <T> The type of value held by the registry.
 */
public class NamespacedRegistry<T> implements Iterable<Entry<Identifier, T>> {
    
    /**
     * An internal registry mapping namespaced identifiers to their values.
     */
    private final Map<Identifier, T> internalRegistry = new TreeMap<>();
    
    /**
     * An internal 2D map that cahces entries based on their namespace. This is used as an
     * optimization for methods like {@link #getValues(String)}.
     */
    private final Map<String, Map<String, T>> namespaceCachedMap = new TreeMap<>();
    
    /**
     * Registers a new value with the registry.
     * 
     * @param key The namespaced identifier key.
     * @param value The value being registered.
     * @return The old value for the given key. This will be null if there was no existing
     *         value.
     */
    @Nullable
    public T register (Identifier key, T value) {
        
        // Register the new value, replacing the old one if it existed.
        final T oldValue = this.internalRegistry.put(key, value);
        
        // Gets or creates a new internal map to cache namespace specific values.
        final Map<String, T> namespaceInternalMap = this.namespaceCachedMap.computeIfAbsent(key.getNamespace(), k -> new HashMap<>());
        namespaceInternalMap.put(key.getName(), value);
        
        // Return the old value so systems can react to it.
        return oldValue;
    }
    
    /**
     * Gets the value mapped to a given identifier key.
     * 
     * @param key The key to search for.
     * @return The value mapped to the provided key. This will be null if no value could be
     *         found.
     */
    @Nullable
    public T get (Identifier key) {
        
        return this.internalRegistry.get(key);
    }
    
    /**
     * Gets the value mapped to a given identifier. If no value is found a default value can be
     * returned.
     * 
     * @param key The key to search for.
     * @param defaultGetter A function that will create the default value. The given input to
     *        this function is the same identifier used to look up the value. This must not
     *        return a null value.
     * @return The value that is mapped to the key, or the default value.
     */
    public T getOrDefault (Identifier key, Function<Identifier, T> defaultGetter) {
        
        // Get the existing value
        T value = this.get(key);
        
        // Generate a default if it doesn't exist.
        if (value == null) {
            
            value = defaultGetter.apply(key);
        }
        
        return value;
    }
    
    /**
     * Gets a value that is mapped to the passed key. If no value has been mapped an exception
     * will be thrown.
     * 
     * @param <X> The type of exception to be thrown.
     * @param key The key to search for.
     * @param exceptionSupplier A supplier for generating the exception thrown if no value is
     *        found.
     * @return Gets the value mapped to the key. If no value has been mapped an exception will
     *         be thrown.
     * @throws X An exception thrown if the key is not mapped to a value.
     */
    public <X extends Throwable> T getOrThrow (Identifier key, Supplier<? extends X> exceptionSupplier) throws X {
        
        // get the value
        final T value = this.get(key);
        
        // Throw the exception if value is null.
        if (value == null) {
            
            throw exceptionSupplier.get();
        }
        
        return value;
    }
    
    /**
     * Clears all values from the {@link #internalRegistry} and {@link #namespaceCachedMap}.
     * This is a potentially dangerous method to call and should be used cautiously.
     */
    public void clear () {
        
        this.internalRegistry.clear();
        this.namespaceCachedMap.clear();
    }
    
    /**
     * Gets a set of all identifiers which have values mapped to them.
     * 
     * @return A set of all identifiers that are currently mapped to a value.
     */
    public Set<Identifier> getIdentifiers () {
        
        return this.internalRegistry.keySet();
    }
    
    /**
     * Gets all values in the registry.
     * 
     * @return All values in the registry.
     */
    public Collection<T> getValues () {
        
        return this.internalRegistry.values();
    }
    
    /**
     * Gets all values registered with a given namespace. If this namespace does not exist an
     * empty collection will be returned.
     * 
     * @param namespace The namespace to specifically look for.
     * @return A collection containing all entries for a given namespace.
     */
    public Collection<T> getValues (String namespace) {
        
        return this.namespaceCachedMap.getOrDefault(namespace, Collections.emptyMap()).values();
    }
    
    @Override
    public Iterator<Entry<Identifier, T>> iterator () {
        
        return this.internalRegistry.entrySet().iterator();
    }
}
