package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 깊콘이_넘쳐흘러_17420 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long result = 0;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[][] B = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            B[i] = new int[]{n, i};
        }
        Arrays.sort(B, Comparator.comparingInt(o -> o[0]));
        int index = 0;
        while (index < N) {
            int useDay = B[index][0];
            int leftDay = A[B[index][1]];
            if (leftDay - useDay >= 0) {
                index++;
                continue;
            }
            result += (useDay - leftDay) / 30 + 1;
            index++;
        }
        System.out.println(result);
    }
}
