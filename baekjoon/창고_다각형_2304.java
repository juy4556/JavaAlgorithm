package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 창고_다각형_2304 {
    static class Line {
        private int position;
        private int height;

        public Line(int position, int height) {
            this.position = position;
            this.height = height;
        }

        public int getPosition() {
            return position;
        }

        public int getHeight() {
            return height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];
        int totalWidth = 0;
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            lines[n] = new Line(p, h);
        }
        int heighest = 0;
        int heighestIndex = 0;
        Arrays.sort(lines, Comparator.comparingInt(Line::getPosition));
        for (int i = 0; i < N; i++) {
            if (heighest < lines[i].getHeight()) {
                heighestIndex = i;
                heighest = lines[i].getHeight();
            }
        }
        totalWidth += heighest;

        int h = lines[0].getHeight();
        int p = lines[0].getPosition();
        for (int i = 1; i <= heighestIndex; i++) {
            if (h <= lines[i].getHeight()) {
                totalWidth += (lines[i].getPosition() - p) * h;
                h = lines[i].getHeight();
                p = lines[i].getPosition();
            }
        }
        h = lines[lines.length - 1].getHeight();
        p = lines[lines.length - 1].getPosition();
        for (int i = N - 2; i >= heighestIndex; i--) {
            if (h <= lines[i].getHeight()) {
                totalWidth += (p - lines[i].getPosition()) * h;
                h = lines[i].getHeight();
                p = lines[i].getPosition();
            }
        }

        System.out.println(totalWidth);
    }
}
