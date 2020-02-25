package behavioural.chainOfResponsibility;

/**
 * Chain Of Responsibility Pattern: solves the problem of need of create a chain (linked list) of objects to process a request.
 *
 * Classes:
 *  WebServer / Sender
 *      handle(request)
 *
 *  abstract Handler
 *      next: Handler
 *      handle(request)
 *      doHandle(request)
 *
 *  Authenticator / HandlerA
 *      doHandle(request)
 *
 *  Logger / HandlerB
 *      doHandle(request)
 *
 * Relationships:
 *  WebServer is compose with the head of the Handler
 *  Authenticator and Logger implements Handler doHandle().
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        // authenticator -> logger -> compressor
        var authenticator = new Authenticator(new Logger(new Compressor(null)));
        var server = new WebServer(authenticator);
        server.handle(new HttpRequest("admin", "1234"));
    }
}

class WebServer {
    private Handler handler;
    public WebServer(Handler handler) {
        this.handler = handler;
    }
    public void handle(HttpRequest request) {
        handler.handle(request);
    }
}

abstract class Handler {
    private Handler next;
    public Handler(Handler next) {
        this.next = next;
    }
    public void handle(HttpRequest request) {
        if(doHandle(request)) return;
        if(next != null) next.handle(request);
    }
    public abstract boolean doHandle(HttpRequest request);
}

class Authenticator extends Handler {
    public Authenticator(Handler next) {
        super(next);
    }
    @Override
    public boolean doHandle(HttpRequest request) {
        System.out.println("Authentication");
        return !(request.getUsername().equals("admin") && request.getPassword().equals("1234"));
    }
}

class Compressor extends Handler {
    public Compressor(Handler next) {
        super(next);
    }
    @Override
    public boolean doHandle(HttpRequest request) {
        System.out.println("Log");
        return false;
    }
}

class Logger extends Handler {
    public Logger(Handler next) {
        super(next);
    }
    @Override
    public boolean doHandle(HttpRequest request) {
        System.out.println("Compress");
        return false;
    }
}

class HttpRequest {
    private String username;
    private String password;
    public HttpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
