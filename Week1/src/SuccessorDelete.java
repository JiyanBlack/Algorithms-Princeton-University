/**
 * Created by anshi on 1/10/2017.
 */
public class SuccessorDelete {
    int[] idx;
    int N;

    public SuccessorDelete(int N) {
        idx = new int[N];
        this.N = N;
        for (int i = 0; i < N; i++) {
            idx[i] = i + 1;
        }
        idx[N - 1] = -1;
    }

    public static void main(String[] args) {
        SuccessorDelete example = new SuccessorDelete(100);
        example.remove(1);
        example.remove(10);
        example.remove(15);
        example.remove(20);
        System.out.println(example.find(14) +" "+example.find(15)+" "+example.find(19));

    }

    public void remove(int x) {
        if (idx[x] == -1) return;
        int successor = idx[x];
        idx[x] = -1;
        int p = x - 1;
        while (p >= 0 && idx[p] == x) {
            idx[p] = successor;
            p--;
        }
    }

    public int find(int x) {
        return idx[x];
    }

}
