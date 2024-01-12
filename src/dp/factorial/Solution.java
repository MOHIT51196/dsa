package dp.factorial;

public class Solution {
    long factorial(int n) { return n > 1 ? n * factorial(n-1) : 1; }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Factorial of " + n + " = " + new Solution().factorial(n));
    }
}
