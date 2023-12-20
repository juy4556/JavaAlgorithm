package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 한_줄로_서기_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] mem = new int[N];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            mem[i] = n;
        }
        ArrayList<Integer> line = new ArrayList<>();
        int[] visited = new int[N + 1];
        dfs(line, visited, N, mem);
    }

    private static void dfs(ArrayList<Integer> line, int[] visited, int end, int[] mem) {
        if (line.size() == end) {
            int[] arr = new int[end];
            arr[line.get(0) - 1] = 0;
            if (mem[line.get(0) - 1] != 0) {
                return;
            }
            for (int i = 1; i < end; i++) {
                int cnt = 0;
                for (int j = 0; j < i; j++) {
                    if (line.get(i) < line.get(j)) {
                        cnt += 1;
                    }
                }
                arr[line.get(i) - 1] = cnt;
                if (mem[line.get(i) - 1] != cnt) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < end; i++) {
                sb.append(line.get(i)).append(' ');
            }
            System.out.println(sb);
            System.exit(0);
        }
        for (int i = 1; i <= end; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                line.add(i);
                dfs(line, visited, end, mem);
                line.remove(line.size() - 1);
                visited[i] = 0;
            }

        }
    }
}
