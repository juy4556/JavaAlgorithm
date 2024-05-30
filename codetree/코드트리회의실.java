package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리회의실 {
    static int n, m;
    static int[][] schedule;
    static int result;
    static TreeSet<int[]> rooms;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = n;
        schedule = new int[n][2];
        rooms = new TreeSet<>(Comparator.comparing((int[] o) -> o[0]).thenComparing(o -> o[1]));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(schedule, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++) {
            int start = schedule[i][0];
            int end = schedule[i][1];
            while (!rooms.isEmpty() && rooms.first()[0] <= start) {
                rooms.pollFirst();
            }
            if (rooms.size() < m) {
                rooms.add(new int[]{end, i});
            } else {
                if (rooms.last()[0] > end) {
                    rooms.pollLast();
                    rooms.add(new int[]{end, i});
                }
                result -= 1;
            }
        }
        System.out.println(result);
    }
}
