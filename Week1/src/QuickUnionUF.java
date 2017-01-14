
public class QuickUnionUF {
    private int[] id;
    private int[] sz;

    public QuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for(int i=0;i<id.length;i++){
            id[i]=i;
            sz[i] = 1;
        }

    }

    private int root(int i){
        while(i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }


    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p , int q){
        id[root(p)] = root(q);
        int p_root = root(p);
        int q_root = root(q);
        int size_of_p = sz[p_root];
        int size_of_q = sz[q_root];

        if (q_root == p_root) return;

        if (size_of_p<size_of_q){
//          add p's tree to q' root
            id[p_root] = q_root;
            sz[q_root] += size_of_p;
        }else{
//          add q's tree to p's root
            id[q_root] = p_root;
            sz[p_root] += size_of_q;
        }
    }
}
