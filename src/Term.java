
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
public class Term implements Comparable<Term>{
    //variables for Term
    private String search_query;
    private long search_weight;
    //defining each instance
    public Term(String query, long weight){
        if(query == null)
            throw new NullPointerException();
        if(weight<0)
            throw new IllegalArgumentException();
        search_query = query;
        search_weight = weight;
    }
    //comparator to compare Terms with their weight
    public static Comparator<Term> byReverseWeightOrder(){
        return new ReverseWeightOrder();
    }
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b){
            if(a.search_weight>b.search_weight)
                return -1;
            else if(a.search_weight == b.search_weight)
                return 0;
            else 
                return 1;
        }
    }
    //comparator to order them according to lexographic order
    public static Comparator<Term> byPrefixOrder(int r){
        if(r<0)
            throw new IllegalArgumentException();
        return new PrefixOrder(r);
    }
    private static class PrefixOrder implements Comparator<Term> {
        private int n;
        public int compare(Term a, Term b){
            //int temp = Math.min(a1.search_query.length(),b1.search_query.length());
            //int min = Math.min(temp, n);
            a.search_query = a.search_query.toLowerCase();
            b.search_query = b.search_query.toLowerCase();
            for(int i = 0; i<n;i++){
                if(a.search_query.charAt(i)<b.search_query.charAt(i))
                    return -1;
                else if(a.search_query.charAt(i)>b.search_query.charAt(i))
                    return 1;
            }
            return 0;
        }
        public PrefixOrder(int r){
            n = r;
        }
    }
    
    public String toString(){
        return (search_weight+"\t"+search_query);
    }
    //compareTo method to order queries lexographically 
    public int compareTo(Term that) {
        this.search_query = this.search_query.toLowerCase();
        that.search_query = that.search_query.toLowerCase();
        int min = Math.min(this.search_query.length(),that.search_query.length());
        for(int i =0; i< min;i++){
            if(this.search_query.charAt(i)<that.search_query.charAt(i))
                return 1;
            else if(this.search_query.charAt(i)>that.search_query.charAt(i))
                return -1;
        }
        if(min == this.search_query.length())
            return -1;
        else if(min == that.search_query.length())
            return 1;
        else 
            return 0;
    }
    
}
