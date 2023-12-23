package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거_분배_19941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        boolean[] visited = new boolean[N];
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            if (visited[i]) continue;
            for (int j = i + 1; j < i + K + 1 && j < N; j++) {
                if (visited[j]) continue;
                if (str.charAt(i) != str.charAt(j)) {
                    visited[i] = true;
                    visited[j] = true;
                    result += 1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
