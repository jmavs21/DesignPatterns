package structural.adapter;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static void main(String[] args) {
        EmailClient client = new EmailClient();
        client.addProvider(new GmailAdapter());
        client.downloadEmails();
    }
}

interface EmailProvider {
    void downloadEmails();
}

class EmailClient {
    private List<EmailProvider> providers = new ArrayList<>();
    public void addProvider(EmailProvider provider) {
        providers.add(provider);
    }
    public void downloadEmails() {
        for (EmailProvider provider : providers)
            provider.downloadEmails();
    }
}

class GmailAdapter implements EmailProvider {
    private GmailClient client = new GmailClient();
    @Override
    public void downloadEmails() {
        client.connect();
        client.getEmails();
        client.disconnect();
    }
}

/** Third party package */
class GmailClient  {
    public void connect() {
        System.out.println("Connecting to Gmail");
    }
    public void getEmails() {
        System.out.println("Downloading emails from Gmail");
    }
    public void disconnect() {
        System.out.println("Disconnecting from Gmail");
    }
}
