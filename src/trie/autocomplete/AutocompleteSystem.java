package trie.autocomplete;

import java.util.*;
import java.util.stream.Collectors;


class Node{
    Map<Character, Node> childrens = new HashMap<>();
    Map<String, Integer> traceMap = new TreeMap<>();
}


class Trie{
    private Node root = new Node();

    void insert(String str, int times){
        Node node = root;
        for(char c : str.toCharArray()){
            if(!node.childrens.containsKey(c)) node.childrens.put(c, new Node());
            node = node.childrens.get(c);
            node.traceMap.put(str, node.traceMap.getOrDefault(str, 0) + times);
        }
    }

    List<String> search(String s) {

        Node node = root;
        for (char c : s.toCharArray()) {
            if(!node.childrens.containsKey(c)) return List.of();
            node = node.childrens.get(c);
        }
        return node.traceMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
    }


}

public class AutocompleteSystem {

    Trie trie = new Trie();
    StringBuffer buffer = new StringBuffer();
    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) trie.insert(sentences[i], times[i]);
    }

    public List<String> input(char c) {
        if(c == '#') {
            trie.insert(buffer.toString(), 1);
            buffer.delete(0, buffer.length());
            return List.of();
        }
        return trie.search(buffer.append(c).toString());
    }


}