package behavioural.command;

/**
 * Command Pattern: solves the problem of different commands/actions for same object.
 * Invoker and Receiver can communicate while been also decouple. Commands can be put into a queue, undone and persisted.
 *
 * Classes:
 *  Button / Invoker
 *      click()
 *
 *  Command
 *      execute()
 *
 *  AddCustomer / ConcreteCommand
 *      execute()
 *
 *  CustomerService / Receiver
 *      addCustomer()
 *
 * Relationships:
 *  Button is compose of a Command
 *  AddCustomer implements Command
 *  AddCustomer has dependency call to CustomerService
 *
 *  When user clicks a button it calls the click() in Button, then calls the execute() in Command which is implemented
 *  by AddCustomer which then calls addCustomer from CustomerService.
 */
public class Command {
    public static void main(String[] args) {
        ICommand command = new AddCustomer(new CustomerService());
        Button button = new Button(command);
        button.click();
    }
}

interface ICommand {
    void execute();
}

class Button {
    private String label;
    private ICommand command;
    public Button(ICommand command) {
        this.command = command;
    }
    public void click() { command.execute(); }
    public String getLabel() {
        return label;
    }
}

class CustomerService {
    public void addCustomer() {
        System.out.println("Add customer");
    }
}

class AddCustomer implements ICommand {
    private CustomerService service;
    public AddCustomer(CustomerService service) {
        this.service = service;
    }
    @Override
    public void execute() {
        service.addCustomer();
    }
}
