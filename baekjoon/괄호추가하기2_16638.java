package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 괄호추가하기2_16638 {
    static class Node {
        int value;
        char operator;

        public Node(int value, char operator) {
            this.value = value;
            this.operator = operator;
        }
    }

    static int N;
    static int result = Integer.MIN_VALUE;
    static List<Node> nodes;
    static int operatorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operatorCount = N / 2;
        nodes = new ArrayList<>();
        String expression = br.readLine();
        Node head = new Node(expression.charAt(0) - '0', '+');
        nodes.add(head);
        if (N > 1) {
            initNodes(expression, 1);
        }
        dfs(0, new HashSet<>());
        System.out.println(result);
    }

    public static void initNodes(String expression, int index) {
        Node node = new Node(expression.charAt(index + 1) - '0', expression.charAt(index));
        nodes.add(node);
        if (index + 2 < N) {
            initNodes(expression, index + 2);
        }
    }

    public static void dfs(int start, Set<Integer> indexSet) {
        int sum = 0;
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (indexSet.contains(i)) {
                Node node2 = nodes.get(i + 1);
                int temp = calculate(node.value, node2.value, node2.operator);
                if (node.operator == '*') {
                    Node popped = stack.pop();
                    popped.value = calculate(popped.value, temp, node.operator);
                    stack.add(popped);
                } else {
                    stack.add(new Node(temp, node.operator));
                }
                i++;
            } else {
                if (node.operator == '*') {
                    Node popped = stack.pop();
                    popped.value = calculate(popped.value, node.value, node.operator);
                    stack.add(popped);
                } else {
                    stack.add(new Node(node.value, node.operator));
                }
            }
        }

        while (!stack.isEmpty()) {
            Node popped = stack.pop();
            if (popped.operator == '+') {
                sum += popped.value;
            } else {
                sum -= popped.value;
            }
        }
        result = Math.max(result, sum);
        for (int i = start; i < operatorCount; i++) {
            indexSet.add(i);
            dfs(i + 2, indexSet);
            indexSet.remove(i);
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

