
import java.util.Arrays;
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
public class Autocomplete {
    /* Initializes the data structure from the given array of terms. */
    private Term[] fterms;
    
    public Autocomplete(Term[] terms){
        if(terms == null)
            throw new NullPointerException();
        for(int i =0;i<terms.length;i++){
            if(terms[i]==null)
                throw new NullPointerException();
        }
        fterms = terms;
        quicksort_prefix(fterms);
    }
/* Returns all terms that start with the given prefix, in descending 
order of weight. */
    //sorting according to the prefix
    private void quicksort_prefix(Term[] temp){
        sort_prefix(temp, 0, temp.length-1);
    }
    
    private static void sort_prefix(Term[] temp,int lo,int hi){
        if(hi<=lo) return;
        int j = partition_prefix(temp,lo,hi);
        sort_prefix(temp,lo,j-1);
        sort_prefix(temp,j+1,hi);
    }
    
    private static int partition_prefix(Term[] temp, int lo, int hi){
        int i =lo;
        int j = hi+1;
        while(true){
            while(temp[++i].compareTo(temp[lo])>-1){
                if(i==hi)
                    break;
            }
            while(temp[lo].compareTo(temp[--j])>-1){
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            exch(temp,i,j);
        }
        exch(temp,lo,j);
        return j;
    }
    
    private static void exch(Term[] temp, int i ,int j){
        Term temp1 = temp[i];
        temp[i] = temp[j];
        temp[j] = temp1;        
    }
    //finding allMatches of the given prefix
    public Term[] allMatches(String prefix){
        if(prefix == null || prefix.isEmpty())
            throw new NullPointerException();
        Term search_term = new Term(prefix,0);
        int first = BinarySearchDeluxe.firstIndexOf(fterms,search_term,Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(fterms,search_term,Term.byPrefixOrder(prefix.length()));
        int size = last - first + 1;
        Term[] temp = new Term[size];
        if(first> -1 && last> -1){
            for(int i = 0;i<size;i++)
                temp[i]= fterms[first+i];
        }
        else{
            temp[0] = new Term("NO MATCH",0);
        }
        quicksort_weight(temp);
        return temp;
    }
    //sorting in descending order of weight 
    private static void quicksort_weight(Term[] temp){
        sort_weight(temp,0,temp.length-1);
    }
    private static void sort_weight(Term[] temp,int lo,int hi){
        if(hi<=lo) return;
        int j = partition_weight(temp,lo,hi,Term.byReverseWeightOrder());
        sort_weight(temp,lo,j-1);
        sort_weight(temp,j+1,hi);
    }
    private static int partition_weight(Term[] temp, int lo, int hi,Comparator<Term> comparator){
        int i =lo;
        int j = hi+1;
        while(true){
            while(comparator.compare(temp[++i],temp[lo])<1){
                if(i==hi)
                    break;
            }
            while(comparator.compare(temp[lo],temp[--j])<1){
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            exch(temp,i,j);
        }
        exch(temp,lo,j);
        return j;
    }
    public static void main(String[] args) { 
    // read in the terms from a file
    String filename = args[0];  // first argument from command line 
    In in = new In(filename); 
    int N = in.readInt(); 
    Term[] terms = new Term[N]; 
    for (int i = 0; i < N; i++) { 
        long weight = in.readLong();           
// read the next weight
        in.readChar();                         
// scan past the tab
        String query = in.readLine();          
// read the next query
        terms[i] = new Term(query, weight);    
// construct the term
    } 
// read in queries from standard input and print the top k matching terms
    int k = Integer.parseInt(args[1]); // 2nd argument from command line 
    /*Term[] terms = new Term[3];
    terms[0] = new Term("apple",3);
    terms[1] = new Term("apple",4);
    terms[2] = new Term("apple",10);*/
    Autocomplete autocomplete = new Autocomplete(terms); 
    while (StdIn.hasNextLine()) { 
        String prefix = StdIn.readLine(); 
        Term[] results = autocomplete.allMatches(prefix); 
        for (int i = 0; i < Math.min(k, results.length); i++) 
            StdOut.println(results[i]); 
    } 
        
    } 
}
