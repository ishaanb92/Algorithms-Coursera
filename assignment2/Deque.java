import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;


// API for a data-structure that supports addition
// and deletion from the "front" and "back"
public class Deque<Item> implements Iterable<Item> {
   
    private class Node {
        Item item;
        Node next;
        Node prev; // With this, we can remove implement removeLast() in constant time
    }
   
    private Node first,last; // book-keeping
    private int size;
    
    public Deque() {
        first = null;
        last = null;
        size = 0;
    } // construct an empty deque
   
    
    public boolean isEmpty() {
        if (first == null && last == null)
            return true;
        return false;
    } // is the deque empty?
    
    public int size() {
        return size;
        
    } // return the number of items on the deque
    
    public void addFirst(Item item) {
        if (item == null) 
            throw new java.lang.IllegalArgumentException("Cannot add a NULL item");
        if (!isEmpty()) {
            Node newNode = new Node();
            Node oldFirst = first;
            newNode.item = item;
            // Update the links
            newNode.next = oldFirst; 
            oldFirst.prev = newNode;
            newNode.prev = null;
            first = newNode;
        }
        else { // Adding to an empty deque
            first = new Node();
            first.item = item;
            first.next = null;
            first.prev = null;
            last = first;
        }
        size++;
        System.out.println("Adding " + first.item + " size = " + size);
    } // add the item to the front
    
    public void addLast(Item item) {
        if (item == null) 
            throw new java.lang.IllegalArgumentException("Cannot add a NULL item");
        
        if (!isEmpty()) {
            Node newNode = new Node();
            Node oldLast = last;
            newNode.item = item;
            // Update links in the list
            newNode.next = null;
            oldLast.next = newNode;
            newNode.prev = oldLast;
            last = newNode;
        }
        else {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        }
        size++;
        System.out.println("Adding " + last.item + " size = " + size); 
    } // add the item to the end
    
    public Item removeFirst() {
        if (!isEmpty()) {
            Node oldFirst = first;
            Item item = oldFirst.item;
            if (oldFirst.next == null) { // Only one left
                first = null;
                last = null;
            }
            else {
                first = oldFirst.next;
                first.prev = null;
            }
            size--;
            return item;
            
        }
        else {
            throw new java.util.NoSuchElementException("The deque is empty");
        }
    } // remove and return the item from the front
    
    public Item removeLast() {
        if (!isEmpty()) {
            Item item = last.item;
            // Perform book-keeping
            Node oldLast = last;
            if (oldLast.prev == null) { // Looks like I'm the only one left :(
                last = null;
                first = null;
            }
            else {
                last = oldLast.prev;
                last.next = null;
            }
            size--;
            return item;
        }
        else {
            throw new java.util.NoSuchElementException("The deque is empty");
        }
    } // remove and return the item from the end
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    } // return an iterator over items in order from front to end
    
    // Implement the Iterable interface
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() { 
            return current != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove() operation is not supported");
        }
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) { // Unit-test
        Deque<Integer> deq = new Deque<Integer>();
        deq.addFirst(1);
        deq.addFirst(2);
        deq.addFirst(10);
        deq.addLast(100);
        deq.removeLast();
        deq.removeLast();
        int last = deq.removeFirst();
        System.out.println("Last element " + last);
        last = deq.removeLast();
        System.out.println("First element " + last);
        for (int elem : deq) {
            System.out.println(elem);
        }
    }
}
