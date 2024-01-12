package google.word_ladder;

import java.util.*;

class Node{
    String word;
    int level;

    Node(String word, int level){
        this.word = word;
        this.level = level;
    }
}

public class Solution {

    private  Map<String, List<String>> getTemplateMap(List<String> wordList){
         Map<String, List<String>> map = new LinkedHashMap<>();
        
        for(String word: wordList){
            for(int i =0; i<word.length(); i++){
                String template = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> transforms = map.getOrDefault(template, new ArrayList<>());
                transforms.add(word);
                map.put(template, transforms);
            }
        }
        return map;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // BFS to access every possible combination
        Map<String, List<String>> templates = getTemplateMap(wordList);
        
        Map<String, Boolean> visited = new LinkedHashMap<>();
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(beginWord, 1));
        visited.put(beginWord, true);
        
        while(!que.isEmpty()){
            Node node = que.poll();
            String word = node.word;
            int level = node.level;
            
            for(int i =0; i<word.length(); i++){
                String temp = word.substring(0, i) + "*" + word.substring(i+1);
                
                for(String str: templates.getOrDefault(temp, List.of())){
                    if(endWord.equals(str)) return level + 1;
                    
                    if(!visited.getOrDefault(str, false)){
                        que.add(new Node(str, level + 1));
                        visited.put(str, true);
                    }
                }
            }
        }
        
        return 0;
        
    }
}