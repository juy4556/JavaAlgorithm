import java.util.Arrays;

public class sol3 {
    public static void main(String[] args) {
        String s1 = "ppwwwbpbbwwbw";
        String s2 = "pwbwpwwbw";
        System.out.println(new sol3().solution(s1, s2));
        s1 = "b";
        s2 = "w";
        System.out.println(new sol3().solution(s1, s2));
    }
    static StringBuilder sb;
    class Node {

        char color;
        Node[] children = new Node[4];

        Node(char color) {
            this.color = color;
        }
    }

    private Node parseQuadTree(String s, int[] index) {
        char ch = s.charAt(index[0]++);
        Node node = new Node(ch);
        if (ch == 'p') {
            for (int i = 0; i < 4; i++) {
                node.children[i] = parseQuadTree(s, index);
            }
        }
        return node;
    }

    private Node mergeTrees(Node t1, Node t2) {
        if (t1.color == 'b' || t2.color == 'b') {
            return new Node('b');
        } else if (t1.color == 'w') {
            return t2;
        } else if (t2.color == 'w') {
            return t1;
        } else {
            Node merged = new Node('p');
            for (int i = 0; i < 4; i++) {
                merged.children[i] = mergeTrees(t1.children[i], t2.children[i]);
            }
            return merged;
        }
    }

    private Node compressTree(Node node) {
        if (node.color != 'p') {
            return node;
        }
        boolean allBlack = true, allWhite = true;
        for (Node child : node.children) {
            if (child.color == 'p') {
                child = compressTree(child);
            }
            if (child.color != 'b') {
                allBlack = false;
            }
            if (child.color != 'w') {
                allWhite = false;
            }
        }
        if (allBlack) {
            return new Node('b');
        }
        if (allWhite) {
            return new Node('w');
        }
        return node;
    }

    private int countBlack(Node node, int level) {
        if (node.color == 'b') {
            return 1 << (2 * level);
        } else if (node.color == 'w') {
            return 0;
        } else {
            int sum = 0;
            for (Node child : node.children) {
                sum += countBlack(child, level - 1);
            }
            return sum;
        }
    }

    public int solution(String s1, String s2) {
        int[] index = {0};
        Node tree1 = parseQuadTree(s1, index);
        System.out.println(Arrays.toString(index));
        index[0] = 0; // Reset index for second string
        Node tree2 = parseQuadTree(s2, index);
        Node mergedTree = mergeTrees(tree1, tree2);
        sb = new StringBuilder();
        dfs(mergedTree);
        System.out.println(sb.toString());
        Node compressedTree = compressTree(mergedTree);
        sb = new StringBuilder();
        dfs(compressedTree);
        System.out.println(sb.toString());
        return countBlack(compressedTree, 5);
    }

    public void dfs(Node node) {
        sb.append(node.color);
        for (Node child : node.children) {
            if (child != null) {
                dfs(child);
            }
        }
    }

}