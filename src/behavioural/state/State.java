package behavioural.state;

/**
 * State Pattern: allows an object to behave differently when its state changes (i.e. polymorphism).
 *  This pattern solves the problem of abusing using lots if/else/switch/enums statements for specific behavior (state) in multiple places.
 *
 * Classes:
 *  Canvas / Context
 *      currentTool:Tool
 *      mouseDown()
 *      mouseUp()
 *
 *  Tool / State
 *      mouseDown()
 *      mouseUp()
 *
 *  Selection / StateA
 *
 *  Brush / StateB
 *
 * Relationships:
 *  Canvas is compose with a Tool
 *  Selection and Brush extends Tool
 */
public class State {
    public static void main(String[] args) {
        Canvas canvas = new Canvas(new Selection());
        canvas.mouseDown();
        canvas.mouseUp();
        canvas.setCurrentTool(new Brush());
        canvas.mouseDown();
        canvas.mouseUp();
    }
}

interface Tool {
    void mouseDown();
    void mouseUp();
}

class Canvas {
    private Tool currentTool;
    public Canvas(Tool tool) { currentTool = tool; }
    public void mouseDown() {
        currentTool.mouseDown();
    }
    public void mouseUp() {
        currentTool.mouseUp();
    }
    public Tool getCurrentTool() {
        return currentTool;
    }
    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
}

class Selection implements Tool {
    @Override
    public void mouseDown() {
        System.out.println("Selection icon");
    }
    @Override
    public void mouseUp() {
        System.out.println("Draw a dashed rectangle");
    }
}

class Brush implements Tool {
    @Override
    public void mouseDown() {
        System.out.println("Bush icon");
    }
    @Override
    public void mouseUp() {
        System.out.println("Draw a line");
    }
}