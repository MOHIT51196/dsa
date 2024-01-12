package google.letter_combination_of_phone_number;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private List<String> chars = new ArrayList<>();
    private List<String> res = new ArrayList<>();
    Map<Character, Boolean> seen = new LinkedHashMap<>();

    private void init(){
        chars.add("");
        chars.add("");
        chars.add("abc");
        chars.add("def");
        chars.add("ghi");
        chars.add("jkl");
        chars.add("mno");
        chars.add("pqrs");
        chars.add("tuv");
        chars.add("wxyz");
    }
    
    
    void dfs(String digits, int index, StringBuilder sb){
        if(sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        int n = Character.getNumericValue(digits.charAt(index));
        
        for(char c : chars.get(n).toCharArray()){
            
                sb.append(String.valueOf(c));
                dfs(digits, index + 1, sb);
                sb.deleteCharAt(sb.length()-1);
            
        }
    }
    
    public List<String> letterCombinations(String digits) {
        init();
        if(digits.trim().length() == 0) return List.of();
        
        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb);
        
        return res;
    }
}