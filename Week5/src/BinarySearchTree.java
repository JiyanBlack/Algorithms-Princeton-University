/**
 * Created by anshi on 3/14/2017.
 */
public class BinarySearchTree {

    Node root;

    private class Node{

        public String key;
        public int value;
        public Node left,right;

        public Node(String key, int value){
            this.left = null;
            this.right = null;
            this.key = key;
            this.value = value;
        }
    }

    public BinarySearchTree(){

    }

    public void put(String key, int val){
        root = put(root,key,val);
    }

    private Node put(Node x, String key, int val){
        if(x == null) return new Node(key,val);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        if(cmp > 0) x.right = put(x.right,key,val);
        else x.value = val;
        return x;
    }

    public static void main(String[] args){
        BinarySearchTree bt = new BinarySearchTree();
        bt.put("A",10);
        bt.put("B",5);
        bt.put("E",14);
    }
}
