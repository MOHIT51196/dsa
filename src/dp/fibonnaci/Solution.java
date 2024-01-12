package dp.fibonnaci;

public class Solution {
    long fibonnaci(int n){
        return n > 2 ? fibonnaci(n-1) + fibonnaci(n-2) : 1;
    }


    public static void main(String[] args) {
        int n = 5;
        System.out.println("Fibonnaci(" + n + ") = " + new Solution().fibonnaci(n));
    }
}
