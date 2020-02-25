/**
 * Design Patterns to make code reusable and extensible, each pattern solves repeatable problems.
 *  ·Reference: Design Patterns Elements of Reusable Object-Oriented Software by Gang of Four. (23 patterns).
 * DP Categories;
 *  ·Creational: different ways to create objects.
 *  ·Structural: relationship between objects.
 *  ·Behavioral: interaction/communication between objects.
 * OOP principles:
 *  ·Interfaces: define a contract (what to do), and let classes implement it (how to do it).
 *  ·Encapsulation: to put code into a unit (class) with data (private fields) and methods on data (public modifiers).
 *  ·Abstraction: to hide details of implementations (make private implementation methods).
 *  ·Inheritance: to reuse code of a base class.
 *  ·Polymorphism: many forms, work with an abstract class with abstract methods to extends it and implement different behaviors.
 *  ·Open Close Principle: classes should be open for extension but closed for modifications, meaning we should extend code not modify it.
 *  ·Favor Composition over Inheritance Principle. So for adding new behavior to an object, favor adding a field (maybe passed via constructor) of other object instead of extending that class.
 * UML relationships types:
 *  ·Inheritance: Rectangle  ⇾  Shape    (Rectangle extends Shape)
 *  ·Composition: Shape     ◆➜ Size     (Shape has a field of type Size). ◆Composition(child can't exist independent on parent) vs ♢Aggregation(child can exists independent of parent) vs -Association(bidirectional is a generic term of composition and aggregation)
 *  ·Dependency:  Shape      ⤏ Document (Shape has a method using a Document as parameter/local variable)
 * Behavioral DP:
 *  ·Memento: Allows restoring an object to a previous state.
 *  ·State: Allows an object to behave differently depending on the state it is in.
 *  ·Iterator: Allows iterating over an object without having to expose the object’s internal structure (which may change in the future).
 *  ·Strategy: Allows passing different algorithms (behaviours) to an object.
 *  ·Template Method: Allows defining a template (skeleton) for an operation. Specific steps will then be implemented in subclasses.
 *  ·Command: Allows decouple a sender from a receiver. The sender will talk to the receive through a command. Commands can be undone and persisted.
 *  ·Observer: Allows an object notify other objects when its state changes.
 *  ·Mediator: Allows an object to encapsulate the communication between other objects.
 *  ·Chain of Responsibility: Allows building a chain of objects to process a request.
 *  ·Visitor: Allows adding new operations to an object structure without modifying it.
 * Structural DP:
 *  ·Composite: Represents object hierarchies where individual objects and compositions of objects are treated the same way.
 *  ·Adapter: Allows converting the interface of a class into another interface that clients expect.
 *  ·Decorator: Adds additional behavior to an object dynamically.
 *  ·Facade: Provides a simplified, higher-level interface to a subsystem. Clients can talk to the facade rather than individual classes in the subsystem.
 *  ·Flyweight: Allows sharing common state between multiple objects.
 *  ·Bridge: Allows representing hierarchies that grow in two different dimensions independently.
 *  ·Proxy: Allows providing a substitute for another object. The proxy object delegates all the work to the target object and contains some additional behavior.
**/
public class DesignPattern {}