import java.util.Comparator;
import java.util.Arrays;

public class MergeSort {
    
    private static Comparable[] aux;
    
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; // Declare aux 
        sort(a,0,a.length-1);
    }
    
    public static void sort(Comparable[] a,int lo,int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a,lo,mid); // left half
        sort(a,mid+1,hi); // right half
        merge(a,lo,mid,hi);
    }
    
    private static boolean less(Comparable v,Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void merge(Comparable[] a, int lo, int mid,int hi) {
        int i = lo;
        int j = mid+1;
        
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];  // Copy into aux array
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++]; // Exhausted left sub-array
            }
            else if (j > hi) {
                a[k] = aux[i++]; // Exhausted right sub-array
            }
            else if (less(aux[j],aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }
    
    // Test client
    public static void main(String[] args) {
        
        String[] myArray = new String[] {"Mary","Had","A","Little","Lamb"};
        MergeSort sorter = new MergeSort();
        sorter.sort(myArray);
        System.out.println(Arrays.toString(myArray));
    }
    
}
