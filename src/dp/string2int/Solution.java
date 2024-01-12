package dp.string2int;

import java.util.Scanner;

public class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        int value = 0;


        
        boolean isNegative = s.startsWith("-");
        if(isNegative){
            s = s.substring(1);
        } else {
            if(s.startsWith("+")) s = s.substring(1);
        }




        int index = 0;
        for (; index < s.length(); index++) {
            int c = Character.getNumericValue(s.charAt(index));
            if(!(c <= 9 && c >=0)) break;
        }

        char[] chars = s.substring(0, index).toCharArray();

        for(int i =0; i< chars.length; i++){

            int c = Character.getNumericValue(chars[chars.length - 1 - i]);

            int num = c * ((int) Math.pow(10, i));
            boolean isBelowLimit = (i < 10 || c == 0) ;
            isBelowLimit = isBelowLimit && Integer.MAX_VALUE - value >= num ;
            if (isBelowLimit) {
                value += num;
                System.out.println("Value = " + value);
            }
            else return (isNegative) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        }


        return isNegative ? value * -1 : value;


    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the value : ");
        String inp = in.nextLine();

        int i = new Solution().myAtoi(inp);
        System.out.println("Convert value = " + i);

        in.close();
    }
}