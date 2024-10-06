package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 휴게소세우기_1477 {
    static int N, M, L;
    static Integer[] stations;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        stations = new Integer[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stations[i] = Integer.parseInt(st.nextToken());
        }
        stations[N] = 0;
        stations[N + 1] = L;
        Arrays.sort(stations, Comparator.naturalOrder());

        int start = 1;
        int end = L;
        while (start <= end) {
            int mid = (start + end) / 2;
            int buildCount = 0;

            for (int i = 0; i < N + 1; i++) {
                int diff = stations[i + 1] - stations[i];
                if (diff % mid == 0) {
                    buildCount += (diff / mid) - 1;
                } else {
                    buildCount += diff / mid;
                }
            }

            if (buildCount <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}
