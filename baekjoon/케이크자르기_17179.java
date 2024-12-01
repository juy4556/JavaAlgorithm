package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 케이크자르기_17179 {
    static int N, M, L;
    static List<Integer> cutPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);

        cutPoint = new ArrayList<>(M + 2);
        cutPoint.add(0);
        for (int i = 0; i < M; i++) {
            cutPoint.add(Integer.parseInt(br.readLine()));
        }
        cutPoint.add(L);

        for (int i = 0; i < N; i++) {
            int cuttingCount = Integer.parseInt(br.readLine());
            int left = 0;
            int right = L;
            while (left <= right) {
                int mid = (left + right) / 2;
                int count = -1;
                int prev = cutPoint.get(0);
                for (int j = 1; j < cutPoint.size(); j++) {
                    if (cutPoint.get(j) - prev >= mid) {
                        count++;
                        prev = cutPoint.get(j);
                    }
                    if (count >= cuttingCount) {
                        break;
                    }
                }
                if (count >= cuttingCount) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            System.out.println(left - 1);
        }
    }
}
