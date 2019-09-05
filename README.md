# Namespaced
This library provides an implementation of a namespaced identifier, along with a registry which can map values to a namespaced identifier. The goal of a namespaced identifier is to reduce the probability of conflicts by including a globally unique component to the the internally unique identifier. Some examples of systems which use this concept are java package names and domain names. Namespaced identifiers are especially useful in environments where there are many authors and organizations with entries in the same environment. 

## How to use?
The only necessary class is the Identifier class. The Identifier class overrides the various compares, equals, and hashcode methods, allowing it to be seamlessly used with all standard maps and collections. The NamespacedRegistry class is an optional class which provides some beneficial functionality such as the ability to get all values mapped with the same global namespace but different internal identifiers. Additionally the INamedspacedValue interface and NamespacedValueRegistry class are provided to allow objects to easily track their own identifiers.

## Maven / Gradle
Coming soon!

## Example Code

```java
        final NamespacedRegistry<String> registry = new NamespacedRegistry<>();
        registry.register(new Identifier("example", "hello_world"), "Hello World");
        registry.register(new Identifier("example", "foo_bar"), "Foo Bar");
        registry.register(new Identifier("elpmaxe", "dlorw_olleh"), "dlroW olleH");
        registry.register(new Identifier("elpmaxe", "rab_oof"), "raB ooF");
        
        final String entry = registry.get(new Identifier("example", "hello_world"));
        System.out.println(entry);
```