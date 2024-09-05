package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 개똥벌레_3020 {
    private static int N, H;
    private static int minObstacle = 200001;
    private static int minCount = 0;
    private static int[] top, bottom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        H = Integer.parseInt(input[1]);
        top = new int[N / 2];
        bottom = new int[N / 2];

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if ((i & 1) == 1) {
                top[i / 2] = height;
            } else {
                bottom[i / 2] = height;
            }
        }

        Arrays.sort(top);
        Arrays.sort(bottom);

        for (int i = 1; i <= H; i++) {
            int topCount = getStrokeCount(top, H - i + 1);
            int bottomCount = getStrokeCount(bottom, i);
            int count = topCount + bottomCount;
            if (count < minObstacle) {
                minObstacle = count;
                minCount = 1;
            } else if (count == minObstacle) {
                minCount++;
            }

        }

        System.out.println(minObstacle + " " + minCount);
    }

    private static int getStrokeCount(int[] arr, int height) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= height) {
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }
        return arr.length - left;
    }
}
