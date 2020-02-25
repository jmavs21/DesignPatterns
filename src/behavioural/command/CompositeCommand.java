package behavioural.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Because commands are abstract, we can for example store different commands on a queue and run them later in order.
 */
public class CompositeCommand implements ICommand{
    private List<ICommand> commands = new ArrayList<>();

    public void add(ICommand command) { commands.add(command); }

    @Override
    public void execute() {
        for(ICommand command : commands) command.execute();
    }

    public static void main(String[] args) {
        var composite = new CompositeCommand();
        composite.add(new Resize());
        composite.add(new BlackAndWhiteCommand());
        composite.execute();
    }
}

class Resize implements ICommand {
    @Override
    public void execute() {
        System.out.println("Resize");
    }
}

class BlackAndWhiteCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("Black and white");
    }
}