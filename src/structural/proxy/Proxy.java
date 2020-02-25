package structural.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Proxy Pattern: solves the need of creating an agent (proxy) for a real object.
 *      Client -> Proxy -> Target
 *  This examples is for an ebook app. The proxy can do additional steps like lazy initialization, logging, cache, etc.
 *      Library -> ProxyEbook -> RealEbook
 *
 *
 * Classes:
 *  interface Ebook / Subject
 *      show() / request()
 *
 *  ProxyEbook / Proxy
 *      ebook: RealEbook
 *      show() / request()
 *
 *  RealEbook / RealSubject
 *      show() / request()
 *
 *  Library / Client
 *      add(ebook)
 *
 * Relationships:
 *  ProxyEbook and RealEbook implements Ebook
 *  ProxyEbook is compose with an RealEbook and do additional work and/ call methods in RealEbook when necessary
 *  Library has a dependency with Ebook
 *
 *  Note: RealEbook constructor is costly, because of load(), so that's why we use a ProxyEbook, and just init when show() is called.
 */
public class Proxy {
    public static void main(String[] args) {
        Library library = new Library();
        String[] fileNames = {"a", "b", "c"};
        for(String fileName : fileNames)
            library.add(new ProxyEbook(fileName)); // ProxyEbook
        library.openEbook("a");
    }
}

interface Ebook {
    void show();
    String getFileName();
}

class RealEbook implements Ebook {
    private String fileName;
    public RealEbook(String fileName) {
        this.fileName = fileName;
        load();
    }
    private void load() {
        System.out.println("Loading the ebook: " + fileName);
    }
    @Override
    public void show(){
        System.out.println("Showing the ebook: " + fileName);
    }
    @Override
    public String getFileName() {
        return fileName;
    }
}

class ProxyEbook implements Ebook {
    private String fileName;
    private RealEbook ebook;
    public ProxyEbook(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void show() {
        if(ebook == null) ebook = new RealEbook(fileName);
        ebook.show();
    }
    @Override
    public String getFileName() {
        return fileName;
    }
}

class ProxyLogEbook implements Ebook {
    private String fileName;
    private RealEbook ebook;
    public ProxyLogEbook(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void show() {
        if(ebook == null) ebook = new RealEbook(fileName);
        System.out.println("Logging...");
        ebook.show();
    }
    @Override
    public String getFileName() {
        return fileName;
    }
}

class Library {
    private Map<String, Ebook> ebooks = new HashMap<>();
    public void add(Ebook ebook) {
        ebooks.put(ebook.getFileName(), ebook);
    }
    public void openEbook(String fileName) {
        ebooks.get(fileName).show();
    }
}