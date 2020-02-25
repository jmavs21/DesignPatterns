package behavioural.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise {
    public static void main(String[] args) {
        Document document = new Document();
        HistoryDocument history = new HistoryDocument();
        document.setContent("Hello");
        history.push(document.createState());
        document.setFontName("Font 1");
        history.push(document.createState());
        document.setFontSize(10);
        System.out.println(document);
        document.restore(history.pop());
        System.out.println(document);
        document.restore(history.pop());
        System.out.println(document);
    }
}

class Document {
    private String content;
    private String fontName;
    private int fontSize;
    public DocumentState createState() {
        return new DocumentState(content, fontName, fontSize);
    }
    public void restore(DocumentState state) {
        content = state.getContent();
        fontName = state.getFontName();
        fontSize = state.getFontSize();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFontName() {
        return fontName;
    }
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    @Override
    public String toString() {
        return "Document{" +
                "content='" + content + '\'' +
                ", fontName='" + fontName + '\'' +
                ", fontSize=" + fontSize +
                '}';
    }
}

class DocumentState {
    private String content;
    private String fontName;
    private int fontSize;
    public DocumentState(String content, String fontName, int fontSize) {
        this.content = content;
        this.fontName = fontName;
        this.fontSize = fontSize;
    }
    public String getContent() {
        return content;
    }
    public String getFontName() {
        return fontName;
    }
    public int getFontSize() {
        return fontSize;
    }
}

class HistoryDocument {
    private Deque<DocumentState> states = new ArrayDeque<>();
    public void push(DocumentState state) {
        states.push(state);
    }
    public DocumentState pop() {
        return states.pop();
    }
}