package baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class IF문_좀_대신_써줘_19637 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] powers = new int[N];
        Map<Integer, String> titles = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            powers[i] = power;
            if (!titles.containsKey(power)) {
                titles.put(power, title);
            }
        }
        Arrays.sort(powers);
        for (int i = 0; i < M; i++) {
            int p = Integer.parseInt(br.readLine());
            int start = 0;
            int end = N - 1;
            if (N > 1) {
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (powers[mid] >= p) {
                        end = mid - 1;
                        continue;
                    }
                    start = mid + 1;
                }
            }
            sb.append(titles.get(powers[start])).append('\n');
        }
        System.out.println(sb);
    }
}
