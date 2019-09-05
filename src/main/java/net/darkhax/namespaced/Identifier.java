/**
 * This class was created by <Darkhax>. It is distributed as part of the Namespaced library
 * under the LGPL 2.1. You can find the original source on GitHub.
 * https://github.com/Darkhax/Namespaced
 */
package net.darkhax.namespaced;

import java.util.Objects;

/**
 * This class provides an implementation of a namespaced identifier. The goal of a namespaced
 * identifier is to reduce the probability of conflicts by including a globally unique value
 * with an internally unique value. Some common examples of namespaced identifiers include java
 * package names and domain names. Namespaced identifiers are useful in systems where there are
 * many authors and organizations with entries in an environment.
 * 
 * This implementation specifically combines a globally unique namespace string with an
 * internally unique identifier name. These values are joined using a semicolon.
 * 
 * @author Tyler Hancock (Darkhax)
 */
public class Identifier implements Comparable<Identifier> {
    
    /**
     * A globally unique namespace for the identifier. The rules for what constitutes a valid
     * namespace are up to the discretion of the system which makes use of it.
     */
    private final String namespace;
    
    /**
     * An internally unique namespace for the identifier. The rules for what constitues a valid
     * name are up to the discretion of the system which uses it.
     */
    private final String name;
    
    /**
     * General constructor for the identifier.
     * 
     * @param namespace The namespace for the identifier. This should be globally unique to
     *        you.
     * @param name The name for the identifier. This should be internally unique to your
     *        project.
     */
    public Identifier(String namespace, String name) {
        
        this.namespace = namespace;
        this.name = name;
    }
    
    /**
     * Gets the namespace for the identifier.
     * 
     * @return The identifier's namespace.
     */
    public String getNamespace () {
        
        return this.namespace;
    }
    
    /**
     * Gets the name for the identifier.
     * 
     * @return The identifier's internal name value.
     */
    public String getName () {
        
        return this.name;
    }
    
    @Override
    public String toString () {
        
        return this.namespace + ':' + this.name;
    }
    
    @Override
    public int hashCode () {
        
        // Hashes the namespace and path together using a prime number.
        return Objects.hash(this.getNamespace(), this.getName());
    }
    
    @Override
    public boolean equals (Object obj) {
        
        // Check if there is object equivalence.
        if (this == obj) {
            
            return true;
        }
        
        // Object can't be equal if it's not the same type.
        if (!(obj instanceof Identifier)) {
            
            return false;
        }
        
        // Compare the equivalence of namespace and name for both identifiers.
        final Identifier other = (Identifier) obj;
        return Objects.equals(this.getNamespace(), other.getNamespace()) && Objects.equals(this.getName(), other.getName());
    }
    
    @Override
    public int compareTo (Identifier o) {
        
        // Compare the namespace of both identifiers.
        int relation = this.getNamespace().compareTo(o.getNamespace());
        
        // If they have the same identifier compare the values of the name.
        if (relation == 0) {
            
            relation = this.getName().compareTo(o.getName());
        }
        
        return relation;
    }
}