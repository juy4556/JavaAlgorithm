package codetree;

import java.util.*;
import java.io.*;

public class 건물짓는시간 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Integer> graph[] = new ArrayList[n + 1];
        int[] buildTimes = new int[n + 1];
        int[] indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int buildTime = Integer.parseInt(st.nextToken());
            int prevBuildCount = Integer.parseInt(st.nextToken());
            buildTimes[i] = buildTime;
            indegree[i] = prevBuildCount;

            for (int j = 0; j < prevBuildCount; j++) {
                int buildingNum = Integer.parseInt(st.nextToken());
                graph[buildingNum].add(i);
            }
        }

        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(new int[]{buildTimes[i], i});
            }
        }

        int result = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int time = now[0];
            int num = now[1];

            result = Math.max(result, time);
            for (int next : graph[num]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(new int[]{time + buildTimes[next], next});
                }
            }
        }

        System.out.println(result);
    }
}