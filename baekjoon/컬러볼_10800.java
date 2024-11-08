package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 컬러볼_10800 {
    // 자기 공보다 크기가 작고 색이 다른 공 잡으면 그 공 크기만큼 점수 획득
    // 잡은 후엔 본인 공 색과 크기 변하지 않음

    static class Ball {
        int color;
        int size;
        int index;

        public Ball(int color, int size, int index) {
            this.color = color;
            this.size = size;
            this.index = index;
        }
    }

    static int N;
    static List<Ball> balls;
    static int[] colors;
    static int[] sizes;
    static StringTokenizer st;
    static int[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new ArrayList<>(N + 1);
        colors = new int[N + 1]; // 컬러마다 누적합
        sizes = new int[2001]; // 크기마다 누적합
        results = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls.add(new Ball(color, size, i));
        }

        balls.sort(Comparator.comparingInt((Ball o) -> o.size).thenComparingInt(o -> o.color));

        // 사이즈마다 모든 공들의 크기 합에서 자신과 같은 색깔의 공들 사이즈만 빼면 답
        int sum = 0;
        for (int i = 0; i < N; i++) {
            Ball ball = balls.get(i);
            int size = ball.size;
            int color = ball.color;
            int idx = ball.index;
            int result = 0;


            result = sum - colors[color] - sizes[size];
            results[idx] = result;

            sum += size;
            colors[color] += size;
            sizes[size] += size;

            if (i > 0 && balls.get(i - 1).size == balls.get(i).size && balls.get(i - 1).color == balls.get(i).color) {
                results[idx] = results[balls.get(i - 1).index];
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(results[i]);
        }
    }
}
