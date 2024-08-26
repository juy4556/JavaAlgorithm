package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 같이눈사람만들래_20366 {
    static int N;
    static int[] snow;
    static int[] visited;
    static int result = 1_000_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        snow = new int[N];
        visited = new int[N];
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(input[i]);
        }

        List<Snowman> snowmen = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmen.add(new Snowman(i, j, snow[i] + snow[j]));
            }
        }

        snowmen.sort(Comparator.comparingInt(o -> o.diameter));

        for (int i = 0; i < snowmen.size() - 1; i++) {
            Snowman sm1 = snowmen.get(i);
            int s1 = sm1.s1;
            int s2 = sm1.s2;
            int diameter = sm1.diameter;

            Snowman sm2 = snowmen.get(i + 1);
            int s3 = sm2.s1;
            int s4 = sm2.s2;
            int diameter2 = sm2.diameter;

            if (s1 == s3 || s1 == s4 || s2 == s3 || s2 == s4) {
                continue;
            }
            int dif = Math.abs(diameter - diameter2);
            result = Math.min(result, dif);

            if (result == 0) {
                break;
            }
        }

        System.out.println(result);
    }

    private static class Snowman {
        int s1, s2;
        int diameter;

        public Snowman(int s1, int s2, int diameter) {
            this.s1 = s1;
            this.s2 = s2;
            this.diameter = diameter;
        }
    }
}
