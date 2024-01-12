package trie.replacewords;

import java.util.*;


class Node{
    Map<Character, Node> children = new HashMap<>();
    boolean isWord = false;
}


class Trie{
    Node root;
    Trie(){
        root = new Node();
    }
    void insert(String str){
        Node node = root;
        for(char c : str.toCharArray()){
            if(!node.children.containsKey(c)) node.children.put(c, new Node());
            node = node.children.get(c);
        }
        node.isWord = true;
    }

    boolean search(String str){
        Node node = root;
        for(char c : str.toCharArray()){
            if(!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return true;
    }

    String matchedRoot(String str){
        Node node = root;
        StringBuilder match = new StringBuilder();
        for(char c : str.toCharArray()){
            if(!node.children.containsKey(c) || node.isWord) break;
            match.append(c);
            node = node.children.get(c);
        }
        return match.length() > 0 && node.isWord ? match.toString() : str ;
    }
}
public class Solution {

    Trie trie = new Trie();
    public String replaceWords(List<String> dictionary, String sentence) {

        for(String inp : dictionary) trie.insert(inp);

        StringBuilder output = new StringBuilder();

        for(String s : sentence.split(" ")){
            if(output.length() > 0) output.append(" ");
            output.append(trie.matchedRoot(s));
        }
        return output.toString();
    }

    public static void main(String[] args) {

        List<String> roots = Arrays.asList("a", "aa", "aaa", "aaaa");
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the input string : ");
        String inp = in.nextLine();

        System.out.println("OUTPUT : " + new Solution().replaceWords(roots, inp));

        in.close();
    }
}