package behavioural.templateMethod;

/**
 * Template Method Pattern: defines a template/behavior for all methods.
 * This solves the problem of repeating same behavior in multiple methods (i.e. calling auditTrail.record() on each different methods)
 *
 * Classes:
 *  abstract Task
 *      execute() / templateMethod()
 *      abstract protected doExecute()
 *
 *  TransferMoney
 *      doExecute()
 *
 *  GenerateReport
 *      doExecute()
 *
 * Relationships:
 *  Task has a execute() that does the auditTrail.record() and then calls doExecute() which are implemented by child classes.
 *  TransferMoney and GenerateReport extends Task and overrides the protected doExecute()
 */
public class TemplateMethod {
    public static void main(String[] args) {
        Task task = new TransferMoney();
        task.execute();
        task = new GenerateReport();
        task.execute();
    }
}

abstract class Task {
    private AuditTrail auditTrail;
    public Task() {
        this.auditTrail = new AuditTrail();
    }
    public void execute() {
        auditTrail.record();
        doExecute();
    }
    protected abstract void doExecute();
}

class TransferMoney extends Task {
    @Override
    protected void doExecute() {
        System.out.println("Transfer money");
    }
}

class GenerateReport extends Task {
    @Override
    protected void doExecute() {
        System.out.println("Generate report");
    }
}

class AuditTrail {
    public void record() {
        System.out.println("Audited");
    }
}
