package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 가장긴증가하는부분수열3_12738 {
    static int N;
    static int[] A;
    static List<Integer> sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        sequence = new ArrayList<>();
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(input[i]);
        }

        sequence.add(A[0]);
        for (int i = 1; i < N; i++) {
            int now = A[i];
            if (sequence.get(sequence.size() - 1) < now) {
                sequence.add(now);
                continue;
            }
            int left = 0;
            int right = sequence.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (sequence.get(mid) >= now) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sequence.set(left, now);
        }

        System.out.println(sequence.size());
    }
}
