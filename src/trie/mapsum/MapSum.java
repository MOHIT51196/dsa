package trie.mapsum;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int value;
}

public class MapSum {
    private Map<String, Integer> inputs;
    TrieNode root;
    public MapSum() {
        this.root = new TrieNode();
        this.inputs = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val + inputs.getOrDefault(key, 0);
        TrieNode node = root;
        for(char c : key.toCharArray()){
            if(!node.children.containsKey(c)) node.children.put(c, new TrieNode());
            node.value += delta;
            node = node.children.get(c);
        }
        node.value += delta;
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            if(!node.children.containsKey(c)) return 0;
            node = node.children.get(c);
        }

        return node.value;

    }


}
