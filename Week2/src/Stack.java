import java.util.Iterator;

/**
 * Created by anshi on 1/12/2017.
 */

public class Stack<Type>  implements Iterable<Type>{

    public Iterator<Type> iterator() {return new ListIterator();}


    private Node head = null;

    public static void main(String[] args){
        Stack<String> st=new Stack();
        st.push("12");
        st.push("22");
        st.push("3");
        st.push("4");
        st.push("5");
        for(String i:st){
            System.out.println(i);
        }
    }

    public class ListIterator implements Iterator<Type>{
        private Node current = head;
        public boolean hasNext(){return current != null;}
        public void remove(){}
        public Type next(){
            Type item = current.item;
            current = current.next;
            return item;
        }
    }


    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void push(Type item) {
        Node newNode = new Node(item);
        Node oldHead = this.head;
        this.head = newNode;
        this.head.next = oldHead;
    }

    public Type pop() {
        Type result = this.head.item;
        this.head = this.head.next;
        return result;
    }

}
