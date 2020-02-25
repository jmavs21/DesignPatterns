package behavioural.iterator;

// Note: now using java's Iterable and Iterator interfaces.
public class Exercise {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        for(String val : stack) System.out.print(val+",");
        System.out.println();
        stack.pop();
        for(String val : stack) System.out.print(val+",");
    }
}

class Stack<T> implements Iterable<T> {
    private Node<T> head;
    public void push(T val) {
        Node<T> node = new Node(val);
        if(head != null) node.next = head;
        head = node;
    }
    public T pop() {
        T val = head.val;
        head = head.next;
        return val;
    }
    @Override
    public java.util.Iterator<T> iterator() {
        return new StackIterator(head);
    }
    private class StackIterator<T> implements java.util.Iterator<T> {
        private Node<T> head;
        public StackIterator(Node head) { this.head = head; }
        @Override
        public boolean hasNext() {
            return head != null;
        }
        @Override
        public T next() {
            T val = head.val;
            head = head.next;
            return val;
        }
    }
    private static class Node<T> {
        private T val;
        private Node<T> next;
        public Node(T val) {
            this.val = val;
        }
    }
}


