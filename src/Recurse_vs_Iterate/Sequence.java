package Recurse_vs_Iterate;

//import packages
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Date: 7/24/16
 * Class:
 * Author: ATG8
 * Purpose: This is the Sequence class that will run computations
 */
public class Sequence{
    
    // set variables
    public static int trackEfficiency;
    
    // constructor
    public Sequence(){
        trackEfficiency = 0;
    }
    
    // computeIterative method
    public static int computeIterative(int n){
        trackEfficiency = 0;
        int [] result = new int[n+1];
        
        for (int i = 0; i <= n; i++){
            if (i <= 1){
                result[i] = i;
            }else{
                result[i] = (result[i-1]*2) + (result[i-2]);
            } //end for loop
            trackEfficiency++;
        } //end for loop
        return result[n];
    } //end computeIterative method
    
    // computeRecursive helper method
    public static int computeRecursive(int n){
        trackEfficiency = 0;
        return recursive(n);
    } //end computeRecursive helper method
    
    // recursive method
    private static int recursive(int n){
        trackEfficiency++;
        if (n <= 1){
            return n;
        }else{
            return recursive(n-1)*2 + recursive(n-2);
        } // end if/else
    } //end Recursive method
    
    //
    
    // getEfficiency method
    public int getEfficiency(){
        return Sequence.trackEfficiency;
    }
    
} //end Sequence class
