package dp.zingzag;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public String convert(String s, int numRows) {
        if(s.length() <= 1 || numRows == 1) return s;

        int $ = numRows -1;

        ArrayList<StringBuilder> arr = new ArrayList<>();
        for (int i = 0; i < numRows; i++) arr.add(new StringBuilder(""));

//        int peakIndex = 0;
        boolean isUpper = false;
        for (int i = 0; i < s.length(); i++) {
            if(i % $ == 0){
                isUpper = !isUpper;
//                peakIndex = i;
                if(isUpper) arr.get(0).append(s.charAt(i));
                else arr.get(numRows - 1).append(s.charAt(i));
            } else {
                int z = i % $;
                if(!isUpper) z = $ - z;
                arr.get(z).append(s.charAt(i));
            }

        }

        return arr.stream().map(StringBuilder::toString).reduce((total, str) -> total + str).get();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string : ");
        String inp = in.nextLine();

        System.out.print("Enter the row count : ");
        int row = in.nextInt();

        System.out.println("\n\nRESULT : " + s.convert(inp, row));



        in.close();
    }
}

// inp -> PAYPALISHIRING
// 3 -> PAHNAPLSIIGYIR
// 4 -> PINALSIGYAHRPI