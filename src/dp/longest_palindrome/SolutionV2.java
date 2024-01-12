package dp.longest_palindrome;

import java.util.Scanner;

// O(n^2)
public class SolutionV2 {

    public String longestPalindrome(String s) {
        int size = s.length();
        boolean[][] table = new boolean[size][size];
        int maxlen = 1;
        int startIndex = 0;


        for(int i=0; i<size; i++) {
            table[i][i] = true;  // for length = 1

            if(i < size-1 && s.charAt(i) == s.charAt(i+1)) {
                table[i][i+1] = true;  // for length = 2
                maxlen = 2;
                startIndex = i;
            }
        }

        for(int len = 3; len<= size; len++){
            for(int i =0; i <= size - len; i++){
                int end = i + len -1;
                if(s.charAt(i) == s.charAt(end) && table[ i + 1 ][end - 1]) {
                    table[i][end] = true;
                    if(maxlen < len){
                        maxlen = len;
                        startIndex = i;
                    }
                }
            }
        }

//       for(boolean[] arr : table){
//           for(boolean e : arr) System.out.print(e + " | ");
//           System.out.println();
//
//       }

        return s.substring(startIndex, startIndex + maxlen );

    }

    static String createPalindrome(String inp) {
        return inp + new StringBuilder(inp).reverse();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        SolutionV2 s2 = new SolutionV2();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string : ");
        String line = createPalindrome(in.nextLine());
        System.out.println("Checking for : " + line);

        long startTime = System.currentTimeMillis();
        String res1 = s.longestPalindrome(line);
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("PALINDROME v1 : " + res1 + " in " + finishTime + " ms");


        long startTime1 = System.currentTimeMillis();
        String res2 = s2.longestPalindrome(line);
        long finishTime1 = System.currentTimeMillis() - startTime1;


        System.out.println("PALINDROME v2 (with DP) : " + res2 + " in " + finishTime1 + " ms");
    }
}


