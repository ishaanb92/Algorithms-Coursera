import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    
    public static void main(String[] args){
        
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
       
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randQ.enqueue(s);
        }
        for (int j = 0; j < k; j++) {
            System.out.println(randQ.dequeue());
        }
        
    }
}
