
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vigneshkrishnaraja
 */
public class BinarySearchDeluxe {
    /* Returns the index of the first key in a[] that equals the search key, 
or -1 if no such key. */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if(a == null || key == null || comparator == null)
            throw new NullPointerException();
        int low = 0;
        int high = a.length-1;
        
        while(low<=high){
            int mid = low + ((high-low)/2);
            if(comparator.compare(key, a[mid])>0) 
                low = mid + 1;
            else if(comparator.compare(key,a[mid]) < 0 ) 
                high = mid - 1;
            else if(comparator.compare(key,a[mid])==0){
                if(mid == 0)
                    return mid;
                else if(comparator.compare(key,a[mid-1]) > 0 )
                    return mid;
                else
                    high = mid - 1;
            }
        }
        
        return -1;
    }
/* Returns the index of the last key in a[] that equals the search key, 
or -1 if no such key. */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if(a == null || key == null || comparator == null)
            throw new NullPointerException();
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = (high-low)/2 + low;
            if(comparator.compare(key, a[mid])>0)
                low = mid + 1;
            else if(comparator.compare(key, a[mid])<0)
                high = mid -1;
            else if(comparator.compare(key,a[mid])==0){
                if(mid == a.length-1)
                    return mid;
                else if(comparator.compare(key,a[mid+1])<0)
                    return mid;
                else
                    low = mid+1;
            }
                
        }
        return -1;
    }
}
