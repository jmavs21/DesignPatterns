package behavioural.mediator;

/**
 * Mediator Pattern: solves the problem of multiple objects need to communicate with each other by use of a mediator (decouple).
 *  Note: if lots of logic in Mediator (ArticlesDialogBox) then could apply Observer (Publisher/Subscriber) pattern.
 *
 * Classes:
 *  abstract DialogBox / Mediator
 *      changed(control)
 *
 *  ArticlesDialogBox / MediatorA
 *      changed(control)
 *
 *  UIControl / Colleague
 *      owner: DialogBox
 *
 *  ListBox, TextBox, Button / ColleagueA, ColleagueB, ColleagueC
 *
 * Relationships:
 *  ArticlesDialogBox extends DialogBox and implements changed(control) mediator logic between UIControls
 *  ListBox, TextBox and Button is compose with a DialogBox field (owner) and calls changed(control) when his state changes.
 */
public class Mediator {
    public static void main(String[] args) {
        var dialogBox = new ArticlesDialogBox();
        dialogBox.simulateUserInteraction();
    }
}

abstract class DialogBox {
    public abstract void changed(UIControl_ control);
}

class ArticlesDialogBox extends DialogBox {
    private ListBox listBox = new ListBox(this);
    private TextBox_ textBox = new TextBox_(this);
    private Button button = new Button(this);
    public void simulateUserInteraction() {
        listBox.setSelection("Article 1");
        // textBox.setContent("");
        textBox.setContent("Article 2");
        System.out.println("TextBox: " + textBox.getContent());
        System.out.println("Button: " + button.isEnabled());
    }
    @Override
    public void changed(UIControl_ control) {
        if(control == listBox) listBoxSelected();
        else if(control == textBox) textBoxChanged();
    }
    private void listBoxSelected() {
        textBox.setContent(listBox.getSelection());
        button.setEnabled(true);
    }
    private void textBoxChanged() {
        String content = textBox.getContent();
        if(content != null && !content.isEmpty()) button.setEnabled(true);
        else button.setEnabled(false);
    }
}

class UIControl_ {
    protected DialogBox owner;
    public UIControl_(DialogBox owner) {
        this.owner = owner;
    }
}

class ListBox extends UIControl_ {
    private String selection;
    public ListBox(DialogBox owner) {
        super(owner);
    }
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
        owner.changed(this);
    }
}

class TextBox_ extends UIControl_ {
    private String content;
    public TextBox_(DialogBox owner) {
        super(owner);
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
        owner.changed(this);
    }
}

class Button extends UIControl_ {
    private boolean isEnabled;
    public Button(DialogBox owner) {
        super(owner);
    }
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
        owner.changed(this);
    }
}