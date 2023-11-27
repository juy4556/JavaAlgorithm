package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어두운_굴다리_17266 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] positions = new int[M];
        int maxDist = 0;
        int result = 0;
        for (int i = 0; i < M; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }
        result = Math.max(positions[0], N - positions[positions.length - 1]);
        int flag = 0;
        for (int i = 1; i < M; i++) {
            int dist = positions[i] - positions[i - 1];
            if (maxDist < dist) {
                maxDist = dist;
                flag = 1;
            }
        }
        if (flag == 1) {
            int temp = maxDist / 2;
            if ((maxDist & 1) == 1) {
                temp += 1;
            }
            result = Math.max(result, temp);
        }
        System.out.println(result);
    }
}
