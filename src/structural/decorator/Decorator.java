package structural.decorator;

/**
 * Decorator Pattern: solves the need of adding new behavior to an object, by wrapping (decorating) it to another object.
 *  This example is for a cloud storage service, we want to add different behaviors (encrypt or compress or encrypt and compress, etc)
 *
 * Classes:
 *  interface Stream / Component
 *      write(data) / operation()
 *
 *  CloudStore / ComponentA
 *      write(data) / operation()
 *
 *  Encrypted / Decorator
 *      stream: Stream / component: Component
 *      write(data) / operation()
 *
 *  Compressed / Decorator
 *      stream: Stream / component: Component
 *      write(data) / operation()
 *
 * Relationships:
 *  CloudStream and Encrypted implements Stream
 *  Encrypted is compose with a Stream
 *
 *  Encrypted will encrypt data and then call his compose stream.write(encrypted)
 */
public class Decorator {
    public static void main(String[] args) {
        String data = "1234-1234-1234-1234";

        Stream store = new CloudStore();
        storeCreditCard(store, data);

        Stream encryptThenStore = new Encrypted(new CloudStore());
        storeCreditCard(encryptThenStore, data);

        Stream compressThenStore = new Compressed(new CloudStore());
        storeCreditCard(compressThenStore, data);

        Stream encryptThenCompressThenStore = new Encrypted(new Compressed(new CloudStore()));
        storeCreditCard(encryptThenCompressThenStore, data);
    }
    static void storeCreditCard(Stream stream, String data) {
        stream.write(data);
    }
}

interface Stream {
    void write(String data);
}

class CloudStore implements Stream {
    public void write(String data) {
        System.out.println("Storing: " + data);
    }
}

class Encrypted implements Stream {
    private Stream stream;
    public Encrypted(Stream stream) {
        this.stream = stream;
    }
    @Override
    public void write(String data) {
        stream.write(encrypt(data));
    }
    private String encrypt (String data) {
        return "%/)&$·=!($/]ç";
    }
}

class Compressed implements Stream {
    private Stream stream;
    public Compressed(Stream stream) {
        this.stream = stream;
    }
    @Override
    public void write(String data) {
        stream.write(compress(data));
    }
    private String compress (String data) {
        return data.substring(0, 4);
    }
}

