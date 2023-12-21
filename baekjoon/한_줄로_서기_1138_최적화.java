package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한_줄로_서기_1138_최적화 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] == 0 && count == n) {
                    arr[j] = i;
                    break;
                }
                if (arr[j] == 0) {
                    count += 1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(' ');
        }
        System.out.print(sb);
    }
}
