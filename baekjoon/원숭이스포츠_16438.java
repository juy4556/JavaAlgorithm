package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 원숭이스포츠_16438 {
    private static int N;
    private static String[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new String[7][N];

        divideAndConquer(0, 0, N - 1);

        for (int i = 0; i < 7; i++) {
            int aCount = 0;
            for (int j = 0; j < N; j++) {
                if (result[i][j].equals("A")) {
                    aCount++;
                }
            }
            for (int j = 0; j < N - 1; j++) {
                System.out.print(result[i][j]);
            }
            if (aCount == N) {
                System.out.print("B");
            } else {
                System.out.print(result[i][N - 1]);
            }
            System.out.println();
        }
    }

    private static void divideAndConquer(int depth, int left, int right) {
        if (left > right || depth == 7) {
            return;
        }

        int mid = (left + right) / 2;

        for (int i = left; i <= right; i++) {
            if (i <= mid) {
                result[depth][i] = "A";
            } else {
                result[depth][i] = "B";
            }
        }

        divideAndConquer(depth + 1, left, mid);

        divideAndConquer(depth + 1, mid + 1, right);
    }
}
