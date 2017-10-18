import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Permutation {
    
    public static void main(String[] args){
        
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
       
// Read in the strings, push each string into a randomized queue
        for (int i = 1; i < args.length; i++) {
            randQ.enqueue(args[i]);
        }
        
        for (int j = 0; j < k; j++) {
            System.out.println(randQ.dequeue());
        }
        
    }
}
