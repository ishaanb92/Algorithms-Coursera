import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size;
    private Item[] array; 
    
    public RandomizedQueue() {
        array = (Item []) new Object[2];
        size = 0;
    } // construct an empty randomized queue
    
    public boolean isEmpty() {
        return (size==0);
    } // is the randomized queue empty?
    
    public int size() {
        return size;
    } // return the number of items on the randomized queue
    
    public void enqueue(Item item) {
        if (size == array.length) resize(array.length*2); // double the size
        array[size++] = item;
    } // add the item
    
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Trying to remove from an empty queue");
        int r = StdRandom.uniform(size); // Pick a random index
        Item item = array[r];
        array[r] = array[size-1]; // copy last element into "empty" spot
        array[--size] = null; 
        if (size == array.length/4) resize(array.length/2);
        return item;
    } // remove and return a random item
    
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Trying to sample from an empty queue");
        int r = StdRandom.uniform(size);
        Item item = array[r];
        return item;
    } // return a random item (but do not remove it)
    
    public void resize(int capacity) {
        
        Item[] temp = (Item []) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp; // Over-write the array reference with temp
        
    } // Resize the array
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    } // return an independent iterator over items in random order
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private int itemsLeft;
        private Item[] tempArray;
        
        public RandomizedQueueIterator() { // ctor
            itemsLeft = size;
            tempArray = (Item []) new Object[size];
            for (int i = 0; i < size; i++) {
                tempArray[i] = array[i]; // Get rid of the "null" elements
            }
            StdRandom.shuffle(tempArray); 
        }
        
        public boolean hasNext() {return (itemsLeft>0);}
        
        public void remove(){ throw new java.lang.UnsupportedOperationException("remove() operation is not supported");}
        
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("All elements have been iterated over");
            Item item  = tempArray[itemsLeft - 1];
            itemsLeft--;
            return item;
        }
        
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQ = new RandomizedQueue<Integer>();
        randQ.enqueue(1);
        randQ.enqueue(2);
        randQ.enqueue(3);
        for (int elem : randQ)
            System.out.println(elem);
        randQ.dequeue();
        randQ.dequeue();
        randQ.dequeue();
        randQ.enqueue(1);
        randQ.enqueue(2);
        randQ.enqueue(3);
        for (int elem : randQ)
            System.out.println(elem);
    } // unit testing (optional)
    
}
