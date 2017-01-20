/**
 * Created by anshi on 1/20/2017.
 */
public class testLoop {

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            System.out.println(i);
            if(i % 10 == 0) {
                i += 5;
            }
        }
    }
}
