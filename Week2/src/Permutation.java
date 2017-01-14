/**
 * Created by anshi on 1/13/2017.
 */
import edu.princeton.cs.algs4.StdIn;
public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int outNumber = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        for(int i = 0; i < outNumber; i++) {
            System.out.println(rq.dequeue());
        }
    }

}
