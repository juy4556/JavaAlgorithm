package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 여러종류의트리순회 {
    static int N;
    static Map<String, String[]> nodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            nodes.put(input[0], new String[]{input[1], input[2]});
        }

        printPreOrder();
        printInOrder();
        printPostOrder();
    }

    private static void printPreOrder() {
        sb = new StringBuilder();
        System.out.println(preOrder("A"));
    }

    private static void printInOrder() {
        sb = new StringBuilder();
        System.out.println(inOrder("A"));
    }

    private static void printPostOrder() {
        sb = new StringBuilder();
        System.out.println(postOrder("A"));
    }

    private static String preOrder(String now) {
        if (now.equals(".")) {
            return "";
        }
        sb.append(now);
        preOrder(nodes.get(now)[0]);
        preOrder(nodes.get(now)[1]);
        return sb.toString();
    }

    private static String inOrder(String now) {
        if (now.equals(".")) {
            return "";
        }
        inOrder(nodes.get(now)[0]);
        sb.append(now);
        inOrder(nodes.get(now)[1]);
        return sb.toString();
    }

    private static String postOrder(String now) {
        if (now.equals(".")) {
            return "";
        }
        postOrder(nodes.get(now)[0]);
        postOrder(nodes.get(now)[1]);
        sb.append(now);
        return sb.toString();
    }

}
