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
        Node oldFirst = first;
        first.item = item;
        // Update the links
        first.next = oldFirst; 
        oldFirst.prev = first;
        first.prev = null;
        size++;
        
    } // add the item to the front
    
    public void addLast(Item item) {
        Node oldLast = last;
        last.item = item;
        // Update links in the list
        last.next = null;
        oldLast.next = last;
        last.prev = oldLast;
        size++;
    } // add the item to the end
    
    public Item removeFirst() {
        if (!isEmpty()) {
            Node oldFirst = first;
            first = oldFirst.next;
            first.prev = null;
            size--;
            return oldFirst.item;
        }
        else
            return null;
    } // remove and return the item from the front
    
    public Item removeLast() {
        if (!isEmpty()) {
            Node oldLast = last;
            last = oldLast.prev;
            last.next = null;
            size--;
            return oldLast.item;
        }
        else
            return null;
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
            // do nothing
        }
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
