import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    private int size;
    
    public RandomizedQueue() {
        size = 0;
    } // construct an empty randomized queue
    
    public boolean isEmpty() {
        return (size==0);
    } // is the randomized queue empty?
    
    public int size() {
        return size;
    } // return the number of items on the randomized queue
    
    public void enqueue(Item item) {
        int id = StdRandom.uniform(this.size); // get a number between 0 and (size-1)
    } // add the item
    
    public Item dequeue() {
        return null;
    } // remove and return a random item
    
    public Item sample() {
        return null;
    } // return a random item (but do not remove it)
    
    public Iterator<Item> iterator() {
        return null;
    } // return an independent iterator over items in random order
    
    public static void main(String[] args) {
    } // unit testing (optional)
    
}
