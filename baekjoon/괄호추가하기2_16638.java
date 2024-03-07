package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 괄호추가하기2_16638 {
    static class Node {
        int value;
        char operator;
        Node left;
        Node right;

        public Node(int value, char operator, Node left, Node right) {
            this.value = value;
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static int result = Integer.MIN_VALUE;
    static Node[] nodes;
    static char[] operators;
    static int[] visited;
    static Queue<> queue;
    static int operatorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operatorCount = N / 2;
        nodes = new Node[operatorCount + 1];
        String expression = br.readLine();
        Node head = new Node(expression.charAt(0) - '0', ' ', null, null);
        nodes[0] = head;
        initNodes(expression, head, 1);
        dfs(0, new ArrayList<>());
        System.out.println(result);
    }

    public static void initNodes(String expression, Node now, int index) {
        Node node = new Node(expression.charAt(index + 1) - '0', expression.charAt(index), now, null);
        nodes[index / 2 + 1] = node;
        now.right = node;
        if (index + 2 < N) {
            initNodes(expression, node, index + 2);
        }
    }

    public static void dfs(int start, List<Integer> list) {
        if ((list.size() & 1) == 0) {
            int sum = 0;

            for (int i = 0; i < list.size(); i += 2) {
                int n1 = list.get(i);
                int n2 = list.get(i + 1);
                Node node1 = nodes[n1];
                Node node2 = nodes[n2];

                int temp = nodes[n1].value;
                int left = n1;
                int right = n2;
                while (left < right) {
                    temp = calculate(left, left + 1, nodes[n1 + 1].operator);
                    left += 1;
                }

                char operator = operators[i / 2];
                sum = calculate(sum, list.get(i), operator);
            }
            result = Math.max(result, sum);
        }
        for (int i = start; i < N / 2 + 1; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                list.add(i);
                dfs(i + 2, list);
                list.remove(list.size() - 1);
                visited[i] = 0;

            }
        }
    }


    public static int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
