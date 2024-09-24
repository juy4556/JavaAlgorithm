package baekjoon;

import java.io.*;
import java.util.*;

public class 환상의짝꿍2 {
    static int N, K;
    static Integer[] cards;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cards = new Integer[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards, Comparator.naturalOrder());

        for (int i = 0; i < N; i++) {
            int a = cards[i];
            int b = K - a;

            int left = i;
            int right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (cards[mid] == b) {
                    System.out.println("YES");
                    return;
                } else if (cards[mid] < b) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        System.out.println("NO");
    }
}
