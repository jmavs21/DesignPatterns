package behavioural.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Memento Pattern: used to solve undo mechanisms problem (use of stack). Allows restoring an object to a previous state.
 *
 * Classes (participants/players):
 *  Editor / Originator
 *      content:String
 *      createState()
 *      restore(state)
 *
 *  EditorState / Memento
 *      content:String
 *
 *  History / Caretaker
 *      states:list
 *      push(state)
 *      pop()
 *
 * Relationships:
 *  Editor has dependency to EditorState
 *  History is compose with EditorState
 */
public class Memento {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();
        editor.setContent("a");
        history.push(editor.createState());
        editor.setContent("b");
        history.push(editor.createState());
        editor.setContent("c");
        editor.restore(history.pop());
        System.out.println(editor.getContent());
    }
}

class Editor {
    private String content;
    public EditorState createState() {
        return new EditorState(content);
    }
    public void restore(EditorState state) {
        content = state.getContent();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

class EditorState {
    private final String content;
    public EditorState(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}

class History {
    private Deque<EditorState> states = new ArrayDeque<>();
    public void push(EditorState state) {
        states.push(state);
    }
    public EditorState pop() {
        return states.pop();
    }
}
