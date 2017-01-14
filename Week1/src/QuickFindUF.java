
public class QuickFindUF {
    private int[] id;

    public static void main(String[] args){
        QuickFindUF qf = new QuickFindUF(10);

    }

    public QuickFindUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int p_value = id[p];
        int q_value = id[q];
        for(int i =0; i< id.length; i++){
            if(id[i] == p_value) id[i] = q_value;
        }
    }

}
