package behavioural.iterator;

/**
 * Iterator Pattern: solves the problem of iterating a data structure, abstracting away implementation detail.
 *
 * Classes:
 *  Iterator
 *      hasNext()
 *      current()
 *      next()
 *
 *  BrowseHistory / Aggregate
 *      createIterator()
 *      push(url)
 *      pop()
 *      StackIterator / ConcreteIterator
 *          hasNext()
 *          current()
 *          next()
 *
 * Relationships:
 *  BrowseHistory has a dependency of Iterator
 *  StackIterator is a subclass of BrowseHistory
 *  StackIterator implements Iterator
 */
public class Iterator {
    public static void main(String[] args) {
        BrowseHistory history = new BrowseHistory();
        history.push("a");
        history.push("b");
        history.push("c");
        IIterator iter = history.createIterator();
        while(iter.hasNext()) {
            System.out.print(iter.current()+",");
            iter.next();
        }
        System.out.println();
        history.pop();
        iter = history.createIterator();
        while(iter.hasNext()) {
            System.out.print(iter.current()+",");
            iter.next();
        }
    }
}

class BrowseHistory {
    private static class Node {
        private String url;
        private Node next;
        public Node(String url) {
            this.url = url;
        }
    }
    private Node head;
    public void push(String url) {
        Node node = new Node(url);
        if(head != null) node.next = head;
        head = node;
    }
    public String pop() {
        String url = head.url;
        head = head.next;
        return url;
    }
    public IIterator createIterator() { return new StackIterator(head); }
    private static class StackIterator implements IIterator {
        private Node head;
        public StackIterator(Node head) {
            this.head = head;
        }
        @Override
        public boolean hasNext() {
            return head != null;
        }
        @Override
        public String current() {
            return head.url;
        }
        @Override
        public void next() {
            head = head.next;
        }
    }
}

interface IIterator {
    boolean hasNext();
    String current();
    void next();
}
