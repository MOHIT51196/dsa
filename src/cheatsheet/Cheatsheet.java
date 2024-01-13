package cheatsheet;

import java.util.*;

class ListNode {
    
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(o instanceof ListNode node) {
            return node.val == this.val 
            && ( 
                (this.next == null && node.next == null) // both null
                || this.next != null && node.next != null && this.next.equals(node.next) // not null comparison
            );
        } else return false;
    }
}

class Node {
    public int weight; // cost
}


public class Cheatsheet {

    // breadth first search
    void bfs(Map<Node, List<Node>> adjancency, Node root) {
        Map<Node, Boolean> visisted = new LinkedHashMap<>();
        Queue<Node> que = new ArrayDeque<>();
        
        que.add(root);
        visisted.put(root, true);

        while(!que.isEmpty()) {
            Node node = que.poll();
            System.out.println(node.weight);

            for(Node child : adjancency.get(node)) {
                if(!visisted.getOrDefault(child, false)) {
                    que.add(child);
                    visisted.put(child, true); // mark nodes visisted upfront (avoid redundant entry)
                }
            }
        }
    }

    // depth first search
    class DFS {
        // with recursion ie. using memory stack
        Map<Node, Boolean> visisted = new LinkedHashMap<>();
        void dfs(Map<Node, List<Node>> adjancency, Node root) {
//        if(!visited.getOrDefault(root, false)) return; // not required as there is a check in loop
            System.out.println(root.weight);
            visisted.put(root, true);

            for(Node child : adjancency.get(root)) {
                if(!visisted.getOrDefault(child, false)) {
                    dfs(adjancency, child);
                }
            }
        }

        // without recursion ie. using stack
        void dfsWithStack(Map<Node, List<Node>> adjancency, Node root) {
            Stack<Node> stack = new Stack<>();

            stack.push(root);

            while(!stack.empty()) {
                Node node = stack.pop();
                System.out.println(node.weight);
                visisted.put(node, true);

                for(Node child : adjancency.get(node)) {
                    if(!visisted.getOrDefault(child, false)) {
                        stack.push(child);
                    }
                }
            }
        }
    }

    // dijkstra - single source shortest path
    Map<Node, Integer> dijkstra(Map<Node, List<Node>> adjancency, Node root) {
        Map<Node, Integer> distances = new LinkedHashMap<>();
        Map<Node, Boolean> visisted = new LinkedHashMap<>();
        adjancency.keySet().forEach((e) -> {
            distances.put(e, Integer.MAX_VALUE);
            visisted.put(e, false);
        });
        Queue<Node> que = new PriorityQueue<>((e1, e2) -> distances.get(e1) - distances.get(e2));

        que.add(root);
        distances.put(root, 0);

        while(!que.isEmpty()) {
            Node node = que.poll();
            visisted.put(node, true); // only diff from BFS
            for(Node child : adjancency.get(node)) {
                if(!visisted.getOrDefault(child, false) 
                && distances.get(node) + child.weight < distances.get(child)) {
                    distances.put(node, distances.get(node) + child.weight);
                    que.add(child);
                }
            }
        }

        return distances;
    }
    
    // maximum sum subarray problem
    int maxSubArray(List<Integer> nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int num : nums){
            sum = sum < 0 ? num : sum + num;
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    // palindrome string
    boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int l = 0;
        int r = chars.length - 1;

        while(l <= r) {
            if(chars[l] != chars[r]) return false;
            l++;
            r--;
        }
        return true;
    }

    // jump game
    boolean canJump(List<Integer> nums) {
        int goal = nums.size() - 1;
        for (int i = nums.size() -2; i >= 0; i--) {
            if(i + nums.get(i) >= goal) goal = i;
            else return false;
        } 
        return true;
    }

    // floyd cycle detection
    boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while(
            slow != null 
            && fast != null
            && fast.next != null
        ) {
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    // sorting
    List<Integer> bubbleSort(final List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            // optimisation 2
            boolean isSorted = true;
            // optimisation 1 : - i
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    isSorted = false;
                }
            }
            if(isSorted) break;
        }
        return arr;
    }

    List<Integer> selectionSort(final List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if(arr.get(j) < arr.get(minIndex)){
                    minIndex = j;
                }
            }

            if(minIndex != i) {
                int temp = arr.get(i);
                arr.set(i, arr.get(minIndex));
                arr.set(minIndex, temp);
            }
        }
        return  arr;
    }

    List<Integer> insertionSort(final List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;
            for (; j >= 0 && key < arr.get(j); j--) {
                arr.set(j + 1, arr.get(j));
            }

            arr.set(j + 1, key);
        }
        return  arr;
    }

    // search
    boolean search(List<Integer> arr, final int val, final int start, final int end) {
        if(start <= end) {
            final int mid = (start + end) / 2;
            System.out.println("start, end = " + start + "," + end + " | mid = " + mid);
            final int midValue = arr.get(mid);
            if (val == midValue) return true;
            else if (val < midValue) return search(arr, val, start, mid - 1);
            else if (val > midValue) return search(arr, val, mid + 1, end);
        }
        return false;
    }
    boolean binarySearch(List<Integer> arr, final int val) {
        selectionSort(arr); // any sort will work
        return search(arr, val, 0, arr.size() - 1);
    }

    // kadane algorithm - max sum subarray
    int kadaneAlgorithm(final List<Integer> arr) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum = sum < 0 ? arr.get(i) : sum + arr.get(i); // or sum = Math.max(arr.get(i), sum + arr.get(i));
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // max product subarray
    int maxProductSubarray(final List<Integer> arr) {
        int maxProd = Integer.MIN_VALUE;
        int pmin = 1;
        int pmax = 1;

        for (int i = 0; i < arr.size(); i++) {
            int num = arr.get(i);
            int npmin = pmin * num;
            int npmax = pmax * num;
            pmax = Math.max(num, Math.max(npmax, npmin));
            pmin = Math.min(num, Math.min(npmax, npmin));
            maxProd = Math.max(maxProd, pmax);
        }
        return maxProd;
    }

    // Jump Game
    boolean isJumpPossible(final List<Integer> arr) {
        int goal = arr.size() - 1;
        for (int i = arr.size() - 2; i >= 0; i--) {
            if(i + arr.get(i) < goal) return false;
            else goal = i;
        }
        return true;
    }

    boolean isCycleDetected(final ListNode root) {
        var slowItr = root;
        var fastItr = root.next;
        while (slowItr != null && fastItr != null && fastItr.next != null) {
            if(slowItr.equals(fastItr)) return true; // cycle detected when iterators collide
            slowItr = slowItr.next;
            fastItr = fastItr.next.next;
        }
        return false;
    }

    boolean isStringPalindrome(final String str) {
        var chars = str.toLowerCase().toCharArray();
        int left = 0;
        int right = str.length() - 1;
        while( left <= right ) {
            if(chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    /* Shortest path algorithm
    * 1. Bellman Ford - shortest path from source to diff destination (handles -ve weights & cycles)
    * 2. Dijkistra -  shortest path from source to diff destination (does not handle -ve weights & cycles)
    * 3. Floyd Warshall - shortest path from anywhere to anywhere (high complexity - O(n^3))
     */

    //  1. Works with edges rather than vertices
    //  2. Relaxation of edges
    //  3. Detect cycles
    record Edge (
        int src,
        int dest,
        int weight
    ){}

    Map<Integer, Integer> bellmanFord(final int[][] adjacencyMatrix, int root) {
        // ========= pre processing =========
        List<Edge> edges = new LinkedList<>(); // faster than ArrayList<>()
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                edges.add(new Edge(i, j, adjacencyMatrix[i][j]));
            }
        }
        // ==================================

        // Bellman Ford Algorithm
        Map<Integer, Integer> minDistance = new LinkedHashMap<>();
        minDistance.put(root, 0);

        // Relaxation of edges till (V-1) times as V-1 are maximum number of edges that can exist from src to dest node
        int vertexCount = adjacencyMatrix.length;
        for (int i = 0; i < vertexCount - 1; i++) {
            for (var edge : edges) {
                int minDist = Math.min(minDistance.getOrDefault(edge.dest, Integer.MAX_VALUE), minDistance.getOrDefault(edge.src, Integer.MAX_VALUE) + edge.weight);
                minDistance.put(edge.dest, minDist);
            }
        }

        // detect negative cycles
        for (var edge : edges) {
            if(minDistance.get(edge.dest) > minDistance.get(edge.src) + edge.weight) {
                throw new RuntimeException("Negative cycle detected");
            }
        }
        return minDistance;
    }

    /*
    1. Use priority queue to serve closed node first
    2. Works like graph search BFS with visit condition like DFS
     */
    Map<Integer, Integer> dijkistra(final int[][] adjacencyMatrix, int root) {
        Map<Integer, Integer> minDistance = new LinkedHashMap<>();
        Queue<Integer> que = new PriorityQueue<>((n1, n2) -> minDistance.getOrDefault(n1, Integer.MIN_VALUE) - minDistance.getOrDefault(n2, Integer.MAX_VALUE));
        Map<Integer, Boolean> visited = new LinkedHashMap<>();

        que.add(root);
        minDistance.put(root, 0);

        while (!que.isEmpty()) {
            var node = que.poll();
            if(visited.getOrDefault(node, false)) continue;

            visited.put(node, true);

            for(var child : adjacencyMatrix[node]) {
                if( adjacencyMatrix[node][child] > 0
                    && minDistance.getOrDefault(child, Integer.MAX_VALUE) > minDistance.getOrDefault(node, Integer.MAX_VALUE) + adjacencyMatrix[node][child]) {
                    minDistance.put(child, minDistance.getOrDefault(node, Integer.MAX_VALUE) + adjacencyMatrix[node][child] );
                    que.add(child);
                }
            }
        }

        return minDistance;
    }

    // 3 loops algorithm
    int[][] floydWarshall(final int[][] adjacencyMatrix, int root) {
        // can use map also, using 2d array for readability
        int[][] distances = Arrays.copyOf(adjacencyMatrix, adjacencyMatrix.length);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                for (int k = 0; k < adjacencyMatrix.length; k++) {
                    if(distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
                        distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                    }
                }
            }
        }

        return distances;
    }


    /* MST - minimum spanning tree
    * Graph can be converted to a spanning tree where all vertices are connected with edge count = vertices - 1
    * E = V - 1
    * Max number of ST = V^(V-2)
    * Max number of edges that can be eliminated to get ST , Er = E - V  + 1
    *
    * Algorithms
    * - Kruskal : msp from a single source using DSU + PriorityQueue
    * - Prims : msp from every source using 'process closest unvisited node first' for V times
     */
    int kruskal(List<Edge> edges) { return 0; }
    List<Integer> prims(final int[][] adjacencyMatrix) { return null; }


    // Trie : self referencing structure using map<char, trie>
    class Trie {
        public Map<Character, Trie> children = new LinkedHashMap<>();
    }

    void insert(final String str) {
        Trie trie = new Trie();
        for(char c : str.toCharArray()) {
            if(!trie.children.containsKey(c)) {
                trie.children.put(c, new Trie());
            }
            trie = trie.children.get(c);
        }
    }

    boolean search(final String str) {
        Trie trie = new Trie();
        for(char c : str.toCharArray()) {
            if(!trie.children.containsKey(c)) return false;
            trie = trie.children.get(c);
        }
        return true;
    }

    // Leetcode Problems
    // coin change - return count of coins required to make up the amount
    int coinDenominations(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // MAX_VALUE can be replaced by (amount + 1)
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if(coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] +  1);
                }
            }
        }

        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

    String longestPalindrome(final String str) {
        var chars = str.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        int maxlen = 1;
        int startIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
            if(i + 1 < chars.length && chars[i] == chars[i+1]) {
                dp[i][i + 1] = true;
                if (maxlen < 2) {
                    maxlen = 2;
                    startIndex = i;
                }
            }
        }

        for (int len = 3; len <= chars.length; len++) {
            for (int i = 0; i + len - 1 < chars.length ; i++) {
                int end = i + len - 1;
                if(chars[i] == chars[end] && dp[i + 1][end - 1]) {
                    dp[i][end] = true;
                    if(len > maxlen){
                        maxlen = len;
                        startIndex = i;
                    }
                }
            }
        }

        return str.substring(startIndex, startIndex + maxlen);
    }

    // String matching
    // Rabin Karp is window based algorithm with hashing comparison
    // Best case - O(n-m)
    // Drawback - spurious hits as hash collision can happen which leads to unnecessary comparison

    final int MOD = 1001; // large prime value
    final int BASE = 256; // generally length of character set
    int hash(final String str) {

        int hash = 1;
        for(char c : str.toCharArray()) hash = ((BASE * hash) + (int)c) % MOD;
        return hash;
    }

    void rabinKarp(final String text, final String pattern) {
        int textHash = hash(text);
        int patternHash = hash(pattern);

        // calculate max base value which will help to move window by subtraction from left
        int maxBase = 1;
        for(int i =0; i < pattern.length() - 1; i++) {
            maxBase = (BASE * maxBase) % MOD;
        }

        // check till t - h character
        // i acts as base index during window movement
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if(textHash == patternHash) {
                // compare each character
                int j = 0;
                for (; j < pattern.length(); j++) {
                    if(text.charAt(i + j) != pattern.charAt(j)) break;
                }
                if(j != pattern.length()) { // no match but false hash collision
                    System.out.println("Spurious hit");
                } else {
                    System.out.println("Match found");
                }
            }

            // need to update text hash
            if(i + pattern.length() < text.length()) {
                int adjustedExistingHash = textHash - (maxBase * text.charAt(i));
                textHash = (BASE * (adjustedExistingHash + text.charAt(i + pattern.length()))) % MOD;

                // fix if hash is negative
                if(textHash < 0) textHash += MOD;
            }
        }
    }

    // MaxHeap - max node at top and left and right nodes are smaller than root
    // parent = (n-1)/2
    // left = 2n + 1
    // right = 2n + 2
    List<Integer> heap = new LinkedList<>();
    int parent(final int pos) { return (pos - 1)/2; };
    int leftChild(final int pos) { return 2 * pos + 1; };
    int rightChild(final int pos) { return 2 * pos + 2; };

    void swap(final int i, final int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // pos is root index
    void heapify(int pos) {
        if(pos >= heap.size()) return;

        int left = leftChild(pos);
        int right= rightChild(pos);

        int maxIndex = pos;
        if(left < heap.size() && heap.get(left) > heap.get(maxIndex)) maxIndex = left;
        if(right < heap.size() && heap.get(right) > heap.get(maxIndex)) maxIndex = right;

        // swap and start heapify from new root
        if(maxIndex != pos) {
            swap(maxIndex, pos);
            heapify(maxIndex);
        }

    }

    void insert(int node) {
        heap.add(node);
        int pos = heap.size() - 1;
        int parent = parent(pos);
        while(pos > 0 && heap.get(parent) > heap.get(pos)) {
            swap(pos, parent);
            pos = parent;
        }
    }
    int getMax() { return heap.get(0); }

    int pollMax() {
        int max = heap.get(0);

        // stabilize the heap after fetching max
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapify(0);

        return max;
    }

    /* longest common subsequence
    * DP solution with 2d array
     */

    int lcs(final String str1, final String str2, int i, int j, int[][] dp) {
        if(i >= str1.length() || j >= str2.length()) return 0;

        if(dp[i][j] == -1) {
            if(str1.charAt(i) == str2.charAt(j)) dp[i][j] = 1 + lcs(str1, str2, i + 1, j + 1, dp);
            else dp[i][j] = Math.max(
                    lcs(str1, str2, i + 1, j, dp),
                    lcs(str1, str2, i, j + 1, dp)
            );
        }
        return dp[i][j];
    }
     int longestCommonSubsequence(final String str1, final String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        for(var dup : dp) Arrays.fill(dup, -1);

        return lcs(str1, str2, 0, 0, dp);
     }

     /* Generate paranthesis
     * if n =2 then generate all possible combinations of enclosed parathesis
     * (()), ()()
     *
     * VERY TYPICAL PROBLEM
      */
    Stack<Character> stack = new Stack<>();
    List<String> result = new ArrayList<>();
    void dfs(int n, int openCount, int closeCount) {

        if(stack.size() == 2 * n) result.add(String.join("", stack.stream().map(Object::toString).toList()));

        if(openCount < n) {
            stack.add('(');
            dfs(n, openCount + 1, closeCount);
            // cleanup
            stack.pop();
        }

        if(closeCount < openCount) {
            stack.add(')');
            dfs(n, openCount, closeCount + 1);
            // cleanup
            stack.pop();
        }
    }
    List<String> generateParathesis(int n) {
        dfs(n, 0, 0);
        return result;
    }

    /* N Queens
    * Look for a place which doesnot exist in +ve, -ve diag or in column of existing queen
    * Postive diag relation : row - col is constant
    * Negative diag relation : row + col is constant
     */

    List<List<String>> resBoard = new LinkedList<>();
    List<Integer> cols = new LinkedList<>();
    List<Integer> positiveDiag = new LinkedList<>();
    List<Integer> negativeDiag = new LinkedList<>();

    void dfs(char[][] board, int row) {
        if(row >= board.length) {
            resBoard.add(Arrays.stream(board).map(String::new).toList());
        }
        for (int col = 0; col < board.length; col++) {
            if(cols.contains(col) || positiveDiag.contains(row - col) || negativeDiag.contains(row + col))
                continue;

            board[row][col] = 'Q';
            cols.add(col);
            positiveDiag.add(row - col);
            negativeDiag.add(row + col);

            dfs(board, row + 1);

            // cleanup for further rows
            board[row][col] = '.';
            cols.remove(Integer.valueOf(col)); // to avoid index removal - wrap with Integer
            positiveDiag.remove(Integer.valueOf(row - col));
            negativeDiag.remove(Integer.valueOf(row + col));
        }
    }
    List<List<String>> nQueens(int n) {
        char[][] board = new char[n][n];
        for(var b: board) Arrays.fill(b, '.');
        dfs(board, 0);
        return resBoard;
    }


    public static void main(String[] args) {
        var pg = new Cheatsheet();
        var out = pg.binarySearch(new ArrayList<>(
                Arrays.asList(1, 5, 2,  3, 322, -33, 3, 0)
        ), 322);
//        var out = pg.isStringPalindrome("Naman");
        System.out.println(out);
    }
}
