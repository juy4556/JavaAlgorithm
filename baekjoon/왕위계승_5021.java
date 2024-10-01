package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 왕위계승_5021 {
    static int N, M;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String oldKing = br.readLine();
        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Double> closeScore = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        double maxScore = -1D;
        String newKing = "";
        closeScore.put(oldKing, 1D);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();
            List<String> relation1 = parents.getOrDefault(parent1, new ArrayList<>());
            List<String> relation2 = parents.getOrDefault(parent2, new ArrayList<>());
            relation1.add(child);
            relation2.add(child);
            parents.put(parent1, relation1);
            parents.put(parent2, relation2);
            closeScore.put(child, 0.0);
            inDegree.put(child, 2);
            inDegree.putIfAbsent(parent1, 0);
            inDegree.putIfAbsent(parent2, 0);
        }

        Deque<String> dq = new ArrayDeque<>();

        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                dq.add(entry.getKey());
            }
        }

        while (!dq.isEmpty()) {
            String now = dq.poll();
            double score = closeScore.getOrDefault(now, 0.0);

            for (String c : parents.getOrDefault(now, new ArrayList<>())) {
                closeScore.put(c, closeScore.getOrDefault(c, 0.0) + score / 2);
                inDegree.put(c, inDegree.get(c) - 1);
                if (inDegree.get(c) == 0) {
                    dq.add(c);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            String candidate = br.readLine();
            if (closeScore.getOrDefault(candidate, 0.0) > maxScore) {
                maxScore = closeScore.getOrDefault(candidate, 0.0);
                newKing = candidate;
            }
        }

        System.out.println(newKing);
    }
}
