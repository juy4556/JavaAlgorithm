package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 리그오브레게노_23059 {
    static int N;
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        int itemCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            List<String> list = graph.getOrDefault(a, new ArrayList<>());
            list.add(b);
            graph.put(a, list);
            inDegree.put(b, inDegree.getOrDefault(b, 0) + 1);
            inDegree.putIfAbsent(a, 0);
        }

        Queue<Item> pq = new PriorityQueue<>(Comparator.comparingInt((Item o) -> o.depth)
                .thenComparing((Item o) -> o.name));

        for (Map.Entry<String, Integer> map : inDegree.entrySet()) {
            itemCount++;
            if (map.getValue() == 0) {
                pq.add(new Item(0, map.getKey()));
            }
        }

        while (!pq.isEmpty()) {
            Item now = pq.poll();
            int d = now.depth;
            String name = now.name;

            result.add(name);

            for (String next : graph.getOrDefault(name, new ArrayList<>())) {
                inDegree.put(next, inDegree.getOrDefault(next, 0) - 1);

                if (inDegree.get(next) == 0) {
                    pq.add(new Item(d + 1, next));
                }
            }
        }

        if (result.size() != itemCount) {
            System.out.print(-1);
        } else {
            for (String name : result) {
                System.out.println(name);
            }
        }
    }

    private static class Item {
        int depth;
        String name;

        public Item(int depth, String name) {
            this.depth = depth;
            this.name = name;
        }
    }
}
