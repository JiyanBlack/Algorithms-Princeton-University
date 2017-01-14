import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Histo {

    public static void main(String[] args) {
        Scanner data = null;
        TreeMap<String,Integer> count;
        Integer idx;
        String word;
        Integer wordCount;

        try {
                data = new Scanner(new File("C:\\Codes\\Algorithms-Princiton-Univeristy\\Algorithms-Princeton-University\\Week1\\src\\test.dat"));
        }
        catch ( IOException e) {
            System.out.println("Sorry but I was unable to open your data file");
            e.printStackTrace();
            System.exit(0);
        }
        
        count = new TreeMap<String,Integer>();

        while(data.hasNext()){
        	word = data.next().toLowerCase();
        	wordCount = count.get(word);
        	if(wordCount == null){
        		wordCount = 0;
        	}
        	count.put(word, ++wordCount);
        }
        
        for(String key : count.keySet()){
        	System.out.println(key + " appear " + count.get(key));
        }
        String c = "absdasdas";
        String b= "c";
        int m =0;
        while (true){
        	m++;
        	System.out.println(m);
        	if (m==10){
        		continue;
        	}
        }
        
    }
}	
        