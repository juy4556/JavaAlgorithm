package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 코드트리숫자만들기 {
    static int N, K;
    static int order = 0;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dfs(new StringBuilder());
        if (order < K) {
            System.out.println(-1);
        }
    }

    public static void dfs(StringBuilder sb) {
        if (sb.length() == N) {
            order++;
            if (order == K) {
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            if (sb.length() + i > N) {
                break;
            }
            for (int j = 0; j < i; j++) {
                sb.append(i);
            }
            dfs(sb);
            sb.delete(sb.length() - i, sb.length());
        }
    }
}
