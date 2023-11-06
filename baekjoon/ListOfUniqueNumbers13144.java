package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ListOfUniqueNumbers13144 {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        boolean[] visited = new boolean[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;
        long count = 0;

        while (left <= right && right < N) {
            while (visited[arr[right]]) {
                visited[arr[left]] = false;
                left += 1;
            }

            if (!visited[arr[right]]) {
                visited[arr[right]] = true;
                right += 1;
                count += right - left;
            }
        }

        System.out.println(count);
        br.close();
    }
}
