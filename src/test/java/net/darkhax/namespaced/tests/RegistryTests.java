package net.darkhax.namespaced.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.darkhax.namespaced.Identifier;
import net.darkhax.namespaced.NamespacedRegistry;

public class RegistryTests {
    
    @Test
    @DisplayName("Test Registry Insertion")
    void testRegistryInsert () {
        
        final NamespacedRegistry<String> registry = new NamespacedRegistry<>();
        registry.register(new Identifier("test", "one"), "one");
        Assertions.assertEquals("one", registry.get(new Identifier("test", "one")));
    }
    
    @Test
    @DisplayName("Test Registry Cache")
    void testRegistryCache () {
        
        final NamespacedRegistry<String> registry = new NamespacedRegistry<>();
        registry.register(new Identifier("test", "one"), "one");
        registry.register(new Identifier("test", "two"), "two");
        registry.register(new Identifier("test", "three"), "three");
        Assertions.assertEquals(3, registry.getValues("test").size());
    }
}