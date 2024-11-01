package programmers;

import java.util.*;

public class 도넛과막대그래프 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}})));
        System.out.println(Arrays.toString(solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11},
                {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}})));
    }

    public static int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        Map<Integer, Integer> outCount = new HashMap<>();
        Set<Integer> inSet = new HashSet<>();
        Map<Integer, Integer> parent = new TreeMap<>();
        Map<Integer, Integer> connectCount = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();
        int addNode = 0;
        int maxConnect = 0;

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            inSet.add(b);
            outCount.put(a, outCount.getOrDefault(a, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : outCount.entrySet()) {
            if (inSet.contains(entry.getKey())) continue;
            if (entry.getValue() > maxConnect) {
                maxConnect = entry.getValue();
                addNode = entry.getKey();
            }
        }
        answer[0] = addNode;

        outCount.keySet().forEach(node -> parent.put(node, node));
        inSet.forEach(node -> parent.put(node, node));

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (a == addNode) {
                continue;
            }

            if (findParent(parent, a) != findParent(parent, b)) {
                unionParent(parent, a, b);
            }
        }

        for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
            int node = entry.getKey();
            if (node == addNode) continue;
            int p = findParent(parent, entry.getValue());
            parent.put(node, p);
            connectCount.put(p, connectCount.getOrDefault(p, 0) + 1);
        }

        for (int[] edge : edges) {
            int a = edge[0];
            if (a == addNode) {
                continue;
            }

            int pa = parent.get(a);
            edgeCount.put(pa, edgeCount.getOrDefault(pa, 0) + 1);
        }

        for (Integer node : connectCount.keySet()) {
            int nc = connectCount.get(node);
            int ec = edgeCount.getOrDefault(node, 0);
            if (nc == ec) {
                answer[1]++;
            } else if (nc == ec + 1) {
                answer[2]++;
            } else {
                answer[3]++;
            }
        }

        return answer;
    }

    private static int findParent(Map<Integer, Integer> parent, int x) {
        if (parent.get(x) != x) {
            parent.put(x, findParent(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    private static void unionParent(Map<Integer, Integer> parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);
        if (a < b) {
            parent.put(b, a);
        } else {
            parent.put(a, b);
        }
    }
}
