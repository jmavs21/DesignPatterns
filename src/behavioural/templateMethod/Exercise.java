package behavioural.templateMethod;

/**
 * The Window class has a method for closing a window. Certain windows may need to execute some code before or after a window is closed.
 */
public class Exercise {
    public static void main(String[] args) {
        var window = new ChatWindow();
        window.close();
    }
}

class Window {
    public void close() {
        onClosing();
        System.out.println("Removing the window from the screen");
        onClosed();
    }
    protected void onClosing() {}
    protected void onClosed() {}
}

class ChatWindow extends Window {
    @Override
    protected void onClosing() {
        System.out.println("Closing streams...");
    }
    @Override
    protected void onClosed() {
        System.out.println("Disconnecting from the server...");
    }
}
