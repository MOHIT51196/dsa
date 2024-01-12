package string_matching.kmp;

import string_matching.rabin_karp.RabinKarp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kmp {

    private List<Integer> lps(String pattern){
        Integer[] lps = new Integer[pattern.length()];
        Arrays.fill(lps, 0);

        int j = 0;
        int i = 1;
        while (i < pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = i - j;
                i++;
                j++;
            }
            else {
                if(j > 0) j = lps[j - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return Arrays.asList(lps);
    }
    public boolean match(String text, String pattern){
        List<Integer> lps = lps(pattern);

        int i = 0;
        int j = 0;
        while (i < text.length()){
            if(text.charAt(i) == pattern.charAt(j)){
                i++; j++;
                if(j == pattern.length()) return true;
            } else {
                if(j == 0) i++;
                else j = lps.get(j - 1);
            }
        }

        return false;

    }

    public static void main(String[] args) {
        String text = "Mohit Malhotra";
        String pattern = "lhotr";

        Kmp kmp = new Kmp();

        System.out.println("String matching status = " + kmp.match(text, pattern));
    }
}
