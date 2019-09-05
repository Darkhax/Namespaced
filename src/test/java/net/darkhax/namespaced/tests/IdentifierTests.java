package net.darkhax.namespaced.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.darkhax.namespaced.Identifier;

public class IdentifierTests {
    
    @Test
    @DisplayName("Test Same Identifier")
    void testSameIdentifier () {
        
        final Identifier first = new Identifier("example", "test");
        final Identifier second = new Identifier("example", "test");
        
        Assertions.assertEquals(first, second);
    }
    
    @Test
    @DisplayName("Test Different Identifier")
    void testDifferentIdentifier () {
        
        final Identifier first = new Identifier("example", "first");
        final Identifier second = new Identifier("example", "second");
        
        Assertions.assertNotEquals(first, second);
    }
    
    @Test
    @DisplayName("Test CompareTo Identifier")
    void compareIdentifiers () {
        
        final Identifier first = new Identifier("example", "first");
        final Identifier second = new Identifier("example", "first");
        
        Assertions.assertEquals(0, first.compareTo(second));
    }
}
