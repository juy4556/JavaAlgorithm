package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 회의실준비하기 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] meeting = new int[n][2];
        int result = 0;

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            meeting[i][0] = Integer.parseInt(input[0]);
            meeting[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(meeting, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int lastEnd = 0;
        for (int i = 0; i < n; i++) {
            int start = meeting[i][0];
            int end = meeting[i][1];
            if (lastEnd <= start) {
                lastEnd = end;
                result++;
            } else if (lastEnd > end) {
                lastEnd = end;
            }
        }

        System.out.println(result);
    }
}
