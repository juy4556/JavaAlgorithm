package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계단수만들기 {
    static int N, K;
    static StringBuilder num;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = new StringBuilder();
        num.append(1);
        for (int i = 1; i < N; i++) {
            num.append(1);
        }

        int min = 1;
        int max = 3;
        while (K > 0) {

            K--;
        }

    }
}
