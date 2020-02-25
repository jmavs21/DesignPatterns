package behavioural.strategy;

/**
 * Strategy Pattern: similar to State pattern but now with multiple object behaviors (states).
 *  So this pattern also solves the problem of abusing using lots if/else/switch/enums statements for specific behaviors (states) in multiple places.
 *
 * Classes:
 *  ImageStorage / Context
 *      store(compressor, filter)
 *
 *  Filter / Strategy
 *      apply()
 *
 *  Compressor / Strategy
 *      compress()
 *
 * Relationships:
 *  ImageStorage has dependency to a Filter and a Compressor
 *  Jpeg and Png implements Compressor
 *  BlackAndWhite implements Filter
 */
public class Strategy {
    public static void main(String[] args) {
        ImageStorage imageStorage = new ImageStorage();
        imageStorage.store("a", new Jpeg(), new BlackAndWhite());
        imageStorage.store("b", new Png(), new BlackAndWhite());
    }
}

class ImageStorage {
    public void store(String filename, Compressor compressor, Filter filter) {
        compressor.compress(filename);
        filter.apply(filename);
    }
}

interface Compressor {
    void compress(String filename);
}

interface Filter {
    void apply(String filename);
}

class BlackAndWhite implements Filter {
    @Override
    public void apply(String filename) {
        System.out.println("Applying B&W filter");
    }
}

class Jpeg implements Compressor {
    @Override
    public void compress(String filename) {
        System.out.println("Compressing using JPEG");
    }
}

class Png implements Compressor {
    @Override
    public void compress(String filename) {
        System.out.println("Compressing using PNG");
    }
}
