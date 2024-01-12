package dp.palindrome_partion;

import java.util.*;


// abcdef
public class Solution {

    Boolean[][] store;

    private Boolean[][] getPalindromeStore(final String str){
        int len = str.length();
        char[] ch = str.toCharArray();
        Boolean[][] arr = new Boolean[len][len];

        // for string length 1 and 2
        for (int i = 0; i < len; i++) {
           arr[i][i] = true;
           if(i < len-1) arr[i][i+1] = (ch[i] == ch[i+1]);
        }

        // for string length > 2
        if(len == 3){
            arr[0][len - 1] = ch[0] == ch[len - 1];
        } else {
            for (int dist = 3; dist <= len; dist++) {
                for (int i = 0; i <= len - dist; i++) {
                    int end = i + dist - 1;
                    arr[i][end] = (ch[i] == ch[end] && arr[i + 1][end - 1]);
                }
            }
        }

        for (var i: arr) {
            for(var j: i) System.out.print(j + " | ");
            System.out.println();
        }

        return arr;
    }


    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        this.store = getPalindromeStore(s);
//        dfs(s, 0, new ArrayList<>(), result);
        return result;
    }

    public void dfs(String s, int start, List<String> container, List<List<String>> result){
        if(start >= s.length()){
            result.add(List.copyOf(container));
            return;
        }

        for (int i = start; i <s.length(); i++) {
            if(store[start][i]){
                container.add(s.substring(start, i + 1));
                dfs(s, i + 1, container, result);
                container.remove(container.size() -1);
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string : ");
        String str = in.nextLine();


        System.out.println("\nPartition possible : ");
        var res = new Solution().partition(str);
        res.forEach(System.out::println);
    }
}
