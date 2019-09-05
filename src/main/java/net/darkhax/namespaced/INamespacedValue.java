/**
 * This class was created by <Darkhax>. It is distributed as part of the Namespaced library
 * under the LGPL 2.1. You can find the original source on GitHub.
 * https://github.com/Darkhax/Namespaced
 */
package net.darkhax.namespaced;

/**
 * In certain situations it may be beneficial for values to keep track of their own identifier
 * values. This interface provides a simple and common way to handle this behavior.
 * 
 * @author Tyler Hancock (Darkhax)
 */
public interface INamespacedValue {
    
    /**
     * Gets the identifier associated with this object.
     * 
     * @return The identifier associated with this object.
     */
    Identifier getIdentifier ();
    
    /**
     * Sets the identifier that is associated with this object.
     * 
     * @param identifier The identifier associated with this object.
     */
    void setIdentifier (Identifier identifier);
}