package dp.reverse_integer;

import java.util.Scanner;

public class Solution {
    public int reverse(int x) {
        boolean isNegative = x < 0;
        String snum = new StringBuilder(isNegative ? String.valueOf(x).substring(1) : String.valueOf(x)).reverse().toString();
        try {
            int revnum = Integer.parseInt(snum);
            return isNegative ? revnum * -1 : revnum;
        } catch (Exception e){
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Enter a 32 bit int number : ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println("Reverse value : " + s.reverse(n));

        in.close();

    }
}
