package behavioural.strategy;

public class Exercise {
    public static void main(String[] args) {
        var client = new ChatClient();
        client.send("message", new DES());
    }
}

class ChatClient {
    public void send(String message, EncryptionAlgorithm encryptionAlgorithm) {
        var encryptedMessage = encryptionAlgorithm.encrypt(message);
        System.out.printf("Sending the encrypted message %s ...", encryptedMessage);
    }
}

interface EncryptionAlgorithm {
    String encrypt(String text);
}

class DES implements EncryptionAlgorithm {
    @Override
    public String encrypt(String text) {
        System.out.println("Encrypting message using DES");
        return "encryptedText";
    }
}

class AES implements EncryptionAlgorithm {
    @Override
    public String encrypt(String text) {
        System.out.println("Encrypting message using AES");
        return "encryptedText";
    }
}
