package trie.basics;

import java.util.HashMap;
import java.util.Map;


class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}



class Trie {

    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String str) {
        TrieNode node = root;
        for(char c : str.toCharArray()){
            if(!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
    }

    public boolean search(String str) {
        TrieNode node = root;
        for(char c : str.toCharArray()){
            if(!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            if(!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("App running.....\n");
        Trie t = new Trie();
        t.insert("Mohit");
        t.insert("Mozak");

        System.out.println("Find Mo : " + t.search("Mo"));
        System.out.println("Find za : " + t.search("za"));
        System.out.println("Find at : " + t.search("at"));
        System.out.println("Find Moh : " + t.search("Moh"));
        System.out.println("Find Moz : " + t.search("Moz"));
    }

}

