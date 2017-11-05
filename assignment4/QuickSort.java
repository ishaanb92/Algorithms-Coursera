import java.util.Comparator;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;


// TODO : Improve the 3-way sort
public class QuickSort {
    
    public void qsort(Comparable[] a) {
        StdRandom.shuffle(a);
        int hi = a.length - 1;
        qsort(a,0,hi);
        
    }
    
    private void qsort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a,lo,hi);
        qsort(a,lo,j-1);
        qsort(a,j+1,hi);
        
    }
    
    private void exch(Comparable[] a, int i,int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    // Find the correct position for the partitioning element
    private int partition(Comparable[] a,int lo, int hi) {
        int i = lo;
        int j = hi+1;
        Comparable element = a[lo];
        while(true) {
            while (less(a[++i],element)) {
                if (i == hi)
                    break;
            }
            while(less(element,a[--j])) {
                if (j == lo)
                    break;
            }
            if (i >= j) break;
            exch(a,i,j); // Put the elements in the correct sub-array
        }
        exch(a,lo,j); // Put the partitioning element in it's correct position
        return j;
        
    }
    
    private static boolean less(Comparable v,Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    // Test client
    public static void main(String[] args) {
        
        String[] myArray = new String[] {"Mary","Had","A","Little","Lamb"};
        QuickSort sorter = new QuickSort();
        sorter.qsort(myArray);
        System.out.println(Arrays.toString(myArray));
    }
}