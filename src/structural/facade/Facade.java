package structural.facade;

/**
 * Facade Pattern: solves the need of providing a simple interface to a complex system. Complex meaning lots of steps/calls to do a task.
 *  This examples is for an app that can send push notifications messages to users, complexity is in class NotificationService/Facade.
 *
 * Classes:
 *  NotificationService / Facade
 *      send(message, target)
 *
 * Relationships:
 *  NotificationService has dependency to NotificationServer, Connection, AuthToken and Message.
 */
public class Facade {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.send("hello", "target");
    }
}

class NotificationService {
    public void send(String message, String target) {
        NotificationServer server = new NotificationServer();
        Connection connection = server.connect("ip");
        AuthToken authToken = server.authenticate("appID", "key");
        server.send(authToken, new Message(message), target);
        connection.disconnect();
    }
}

class Message {
    private String content;
    public Message(String content) {
        this.content = content;
    }
}

class NotificationServer {
    public Connection connect(String ipAddress) {
        return new Connection();
    }
    public AuthToken authenticate(String appID, String key) {
        return new AuthToken();
    }
    public void send(AuthToken authToken, Message message, String target) {
        System.out.println("Sending a message");
    }
}

class Connection {
    public void disconnect() {

    }
}

class AuthToken {

}
