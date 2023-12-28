package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 흙길_보수하기_1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int result = 0;
        int count = 0;
        List<int[]> puddles = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            puddles.add(new int[]{a, b});
        }
        puddles.sort(Comparator.comparingInt((int[] o) -> o[0]));
        int temp = 0;
        for (int i = 0; i < N; i++) {
            int a = puddles.get(i)[0];
            int b = puddles.get(i)[1];
            if (a < temp) {
                a = temp;
            }
            count = (b - a) / L;
            if ((b - a) % L > 0) count++;
            result += count;
            temp = a + count * L;
        }
        System.out.println(result);
    }
}
