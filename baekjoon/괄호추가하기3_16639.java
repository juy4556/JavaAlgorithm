package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 괄호추가하기3_16639 {
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
    static List<int[]> ranges;
    static int operatorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operatorCount = N / 2;
        nodes = new ArrayList<>();
        ranges = new ArrayList<>();
        String expression = br.readLine();
        Node head = new Node(expression.charAt(0) - '0', '+');
        nodes.add(head);
        if (N > 1) {
            initNodes(expression, 1);
        }
        addRanges(0, new ArrayList<>());
        ranges.sort((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        ranges.stream().forEach(range -> System.out.println(range[0] + " " + range[1]));
        dfs(0, new ArrayList<>());
        System.out.println(result);
    }

    public static void initNodes(String expression, int index) {
        Node node = new Node(expression.charAt(index + 1) - '0', expression.charAt(index));
        nodes.add(node);
        if (index + 2 < N) {
            initNodes(expression, index + 2);
        }
    }

    public static void addRanges(int start, List<Integer> list) {
        if (list.size() == 2) {
            ranges.add(new int[]{list.get(0), list.get(1)});
            return;
        }
        for (int i = start; i <= operatorCount; i++) {
            list.add(i);
            addRanges(i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void dfs(int start, List<int[]> rangeList) {
        int[] dp = new int[operatorCount + 1];
        int[] visited = new int[operatorCount + 1];
        dp[0] = nodes.get(0).value;
        System.out.println("rangeList");
        rangeList.stream().forEach(range -> System.out.println(range[0] + " " + range[1]));
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(Arrays.toString(dp));
            dp[i] = Math.max(dp[i], nodes.get(i).value);
            boolean isRange = false;
            for (int[] range : rangeList) {
                if (range[0] == i) {
                    int j = range[1];
                    visited[i] = 1;
                    int index = i + 1;
                    while (index <= j) {
                        dp[index] = Math.max(dp[index],
                                calculate(dp[index - 1], nodes.get(index).value, nodes.get(index).operator,
                                        nodes.get(range[0]).operator == '-'));
                        visited[index] = 1;
                        index++;
                    }
                    System.out.println(nodes.get(i).operator);
                    if (i > 0) {
                        System.out.println("111111111");
                        dp[j] = Math.max(dp[j], calculate(dp[i - 1], dp[j], nodes.get(i).operator,
                                nodes.get(i).operator == '-'));
                        System.out.println("j: " + j + " dp[j]: " + dp[j]);
                    }
                }
            }

            if (visited[i] > 0) {
                continue;
            }
            if (i > 0) {
                System.out.println("222222222");
                dp[i] = Math.max(dp[i], calculate(dp[i - 1], nodes.get(i).value, nodes.get(i).operator, false));
                System.out.println("i: " + i + "dp[i]: " + dp[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(operatorCount);
        result = Math.max(result, dp[operatorCount]);
        for (int i = start; i < ranges.size(); i++) {
            int[] range = ranges.get(i);
            boolean isPossible = true;
            for (int j = 0; j < rangeList.size(); j++) {
                if (range[0] > rangeList.get(j)[0] && range[0] < rangeList.get(j)[1] || range[1] >= rangeList.get(j)[1]) {
                    isPossible = false;
                    break;
                }
            }
            if (!isPossible) {
                continue;
            }
            rangeList.add(range);
            dfs(i + 1, rangeList);
            rangeList.remove(rangeList.size() - 1);
        }
    }


    public static int calculate(int a, int b, char operator, boolean wrrappedWithMinus) {
        if (wrrappedWithMinus) {
            switch (operator) {
                case '+':
                    return a - b;
                case '-':
                    return a + b;
                case '*':
                    return a * b;
                default:
                    return 0;
            }
        }
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                if (a < 0 && b < 0) {
                    return a + b;
                }
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
