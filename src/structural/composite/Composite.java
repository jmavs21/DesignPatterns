package structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern: solves the need of represent hierarchy of objects and use them as hierarchy objects (leaf or composite) (i.e. file system).
 *  This example uses the group operation in power point like app.
 *
 * Classes:
 *  interface Component
 *      render() / operation()
 *
 *  Shape / Leaf
 *      render() / operation()
 *
 *  Group / Composite
 *      render() / operation
 *
 * Relationships:
 *  Shape and Group implements Component render()
 *  Group has a list of Components
 */
public class Composite {
    public static void main(String[] args) {
        Group group1 = new Group();
        group1.add(new Shape()); // square
        group1.add(new Shape()); // square

        Group group2 = new Group();
        group2.add(new Shape()); // circle
        group2.add(new Shape()); // circle

        Group group = new Group();
        group.add(group1);
        group.add(group2);
        group.render();
        group.move();
    }
}

interface Component {
    void render();
    void move();
}

class Shape implements Component {
    @Override
    public void render() {
        System.out.println("Render Shape");
    }
    @Override
    public void move() {
        System.out.println("Move Shape");
    }
}

class Group implements Component {
    private List<Component> components = new ArrayList<>();
    public void add(Component component) {
        components.add(component);
    }
    @Override
    public void render() {
        for(Component component : components)
            component.render();
    }
    @Override
    public void move() {
        for(Component component : components)
            component.move();
    }
}
