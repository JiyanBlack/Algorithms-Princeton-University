/**
 * Created by anshi on 1/12/2017.
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int space, usedSize,index;
    private Item[] items;

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.enqueue("4");
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.sample());
        q.enqueue("11");
        q.enqueue("22");
        q.enqueue("33");
        q.enqueue("44");
        for(Integer i = 0; i < 1000 ; i++){
            q.enqueue(i.toString());
        }
        for(String i : q) {
            System.out.println(i);
        }
    }

    public RandomizedQueue() {
        space = 1;
        usedSize = 0;
        index = 0;
        items = (Item[]) new Object[space];
    }

    public boolean isEmpty() {
        return usedSize == 0;
    }

    public void enqueue(Item item) {
        testNull(item);
        usedSize++;
        items[index] = item;
        index++;
        autoResize();
    }

    public Item dequeue() {
        testEmpty();
        usedSize--;
        int index = getRanIndex();
        while (items[index] == null) {
            index = getRanIndex();
        }
        Item target = items[index];
        items[index] = null;
        autoResize();
        return target;
    }

    public Item sample() {
        testEmpty();
        Item target = items[getRanIndex()];
        while (target == null) {
            target = items[getRanIndex()];
        }
        return target;
    }

    private void autoResize() {
        if (index == space) {
            space = 2 * space;
            Item[] oldItems = items;
            items = (Item[]) new Object[space];
            for(int i = 0; i < oldItems.length; i++) {
                items[i] = oldItems[i];
            }
        }else if(usedSize <= (space/4)) {
            space = space/2;
            Item[] oldItems = items;
            items = (Item[]) new Object[space];
            int j = 0;
            for(int i = 0; i < oldItems.length; i++) {
                if (oldItems[i] != null) {
                    items[j] = oldItems[i];
                    j++;
                }
            }
            index = usedSize;
        }
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] iterItems;
        private int index;
        public ArrayIterator() {
            iterItems = (Item[]) new Object[usedSize];
            index = 0;
            int j = 0;
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    iterItems[j] = items[i];
                    j++;
                }
            }
            StdRandom.shuffle(iterItems);
        }

        public boolean hasNext() {
            return index < iterItems.length;
        }

        public Item next() {
            index++;
            if (index > iterItems.length) throw new java.util.NoSuchElementException();
            return iterItems[index - 1];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    public int size() { return usedSize; }

    private int getRanIndex() {
        return StdRandom.uniform(index);
    }

    private void testNull(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
    }

    private void testEmpty() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }
}
