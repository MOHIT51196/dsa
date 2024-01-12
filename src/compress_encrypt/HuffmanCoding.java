package compress_encrypt;

import java.util.*;
import java.util.function.BinaryOperator;

class Node{
    int freq;
    char val;
    Node left;
    Node right;

    public Node(char val, int freq) {
        this.freq = freq;
        this.val = val;
    }
}

public class HuffmanCoding {

    PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.freq));

    private Node create(char ch, int freq){
        return new Node(ch, freq);
    }

    private Node createTree(){
        while(que.size() != 1){
            Node left = que.poll();
            Node right = que.poll();

            if(left != null && right != null){
                Node newNode = new Node('$', left.freq + right.freq);
                newNode.left = left;
                newNode.right = right;
                que.add(newNode);
            }
        }
        return que.peek();
    }

    private void encode(Node root, int[] encoding, int pos){
        if(root == null) return;

        if(root.left != null){
            encoding[pos] = 0;
            encode(root.left, encoding, pos + 1);
        }

        if(root.right != null){
            encoding[pos] = 1;
            encode(root.right, encoding, pos + 1);
        }

        if(root.left == null && root.right == null){
            // looping first 'pos' values then converting int to string then joining all of them
            String code = Arrays.stream(encoding).limit(pos).mapToObj(String::valueOf).reduce((s, s2) -> s + s2).get();
            System.out.println("Char = " + root.val + " | Code = " + code );
        }
    }

    public void huffmanEncoding(List<Character> chars, List<Integer> freqs){
        for (int i =0; i<chars.size(); i++) que.add(new Node(chars.get(i), freqs.get(i)));

        Node root = createTree();
        encode(root, new int[chars.size()], 0);

    }

    public static void main(String[] args) {
        new HuffmanCoding().huffmanEncoding(
                List.of('a', 'b', 'c', 'd', 'e', 'f'),
                List.of(5, 9, 12, 13, 16, 45)
        );
    }

}
