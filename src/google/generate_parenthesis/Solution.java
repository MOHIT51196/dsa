package google.generate_parenthesis;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {

    private List<String> res = new ArrayList<>();
    private Stack<String> stack = new Stack<>();

    private void dfs(int open, int close, int size){
        if(stack.size() == size * 2){
            res.add(String.join("", stack));
        }

        if(open < size){
            stack.add("(");
            List<String> s = List.of();
            dfs(open + 1, close, size);
            stack.pop();
        }

        if(open > close){
            stack.add(")");
            dfs(open, close + 1, size);
            stack.pop();
        }
    }

    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n);
        return res;
    }
}