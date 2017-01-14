/**
 * Created by anshi on 1/12/2017.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head,tail;
    private int size;

    private class Node{
        public Item item;
        public Node next;
    }

    private class NodeIterator implements Iterator<Item>{
        private Node cur = head;

        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

    public Deque() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        testNull(item);
        if (isEmpty()){
            this.head = new Node();
            this.head.item = item;
            this.tail = this.head;
        }else{
            Node oldHead = this.head;
            this.head = new Node();
            this.head.item = item;
            this.head.next = oldHead;
        }
        size++;
    }

    public void addLast(Item item) {
        testNull(item);
        if (isEmpty()) {
            this.tail = new Node();
            this.tail.item = item;
            this.head = this.tail;
        } else {
            Node oldTail = this.tail;
            this.tail = new Node();
            this.tail.item = item;
            oldTail.next = this.tail;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty.");
        Item firstItem;
        if (size == 1) {
            firstItem = this.head.item;
            this.tail = null;
            this.head = null;
        } else {
            firstItem = this.head.item;
            head = head.next;
        }
        size--;
        return firstItem;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty.");
        Item lastItem;
        if (size == 1) {
            lastItem = head.item;
            head = null;
            tail = null;
        } else {
            lastItem = tail.item;
            Node cur = head;
            while (cur.next != this.tail) cur = cur.next;
            tail = cur;
            tail.next = null;
        }
        size--;
        return lastItem;
    }

    public Iterator<Item> iterator() {
        return new NodeIterator();
    }

    public static void main(String[] args) {
        Deque<String> strDq = new Deque<String>();
        System.out.println(strDq.isEmpty());
        strDq.addFirst("addFirst1");
        strDq.addFirst("addFirst2");
        strDq.addFirst("addFirst3");
        strDq.addLast("addLast1");
        strDq.addLast("addLast2");
        strDq.addLast("addLast3");

        for(String item : strDq) {
            System.out.println(item);
        }
        System.out.println(strDq.isEmpty());
        System.out.println(strDq.size());
        strDq.removeFirst();
        System.out.println(strDq.size());
        strDq.removeLast();
        strDq.removeLast();
        strDq.removeLast();
        strDq.removeLast();
        System.out.println(strDq.size());
        for(String item : strDq) {
            System.out.println(item);
        }
    }

    private void testNull(Item item){
        if (item == null) throw new java.lang.NullPointerException("Cannot add null.");
    }
}
