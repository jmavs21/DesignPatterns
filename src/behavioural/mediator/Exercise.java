package behavioural.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: using Observer pattern to solve mediator problem.
 */
public class Exercise {
    public static void main(String[] args) {
        var singUpDialogBox = new SignUpDialogBox();
        singUpDialogBox.simulateUserInteraction();
    }
}

interface EventHandler {
    void handle();
}

class UiControl {
    private List<EventHandler> eventHandlers = new ArrayList<>();
    public void addEventHandler(EventHandler handler) {
        eventHandlers.add(handler);
    }
    protected void notifyEventHandlers() {
        for (var handler : eventHandlers)
            handler.handle();
    }
}

class Textbox extends UiControl {
    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
        notifyEventHandlers();
    }
    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
}

class Checkbox extends UiControl {
    private boolean isChecked;
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyEventHandlers();
    }
}

class Buttonx extends UiControl {
    private boolean isEnabled;
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
        notifyEventHandlers();
    }
}

class SignUpDialogBox {
    private Textbox usernameTextBox = new Textbox();
    private Textbox passwordTextBox = new Textbox();
    private Checkbox agreeToTermsCheckBox = new Checkbox();
    private Buttonx signUpButton = new Buttonx();
    public SignUpDialogBox() {
        usernameTextBox.addEventHandler(this::controlChanged);
        passwordTextBox.addEventHandler(this::controlChanged);
        agreeToTermsCheckBox.addEventHandler(this::controlChanged);
    }
    private void controlChanged() {
        signUpButton.setEnabled(isFormValid());
    }
    private boolean isFormValid() {
        return !usernameTextBox.isEmpty() && !passwordTextBox.isEmpty() && agreeToTermsCheckBox.isChecked();
    }
    public void simulateUserInteraction() {
        // Initially the button should be disabled
        System.out.println("Initially: " + signUpButton.isEnabled());
        // The user enters their username, the button is still disabled
        usernameTextBox.setContent("username");
        System.out.println("After setting the username: " + signUpButton.isEnabled());
        // The user enters their password, the button is still disabled
        passwordTextBox.setContent("password");
        System.out.println("After setting the password: " + signUpButton.isEnabled());
        // The agrees to the terms, the button becomes enabled
        agreeToTermsCheckBox.setChecked(true);
        System.out.println("After agreeing to terms: " + signUpButton.isEnabled());
        // The user removes the password, the button becomes disabled
        passwordTextBox.setContent("");
        System.out.println("After removing the password: " + signUpButton.isEnabled());
        // The user enters the password again, the button becomes enabled
        passwordTextBox.setContent("password");
        System.out.println("After re-setting the password: " + signUpButton.isEnabled());
    }
}

