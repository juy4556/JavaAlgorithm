package codetree;

import java.io.*;
import java.util.*;

public class 코드트리음식점 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        int recommend = 100001;
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            int n = Integer.parseInt(str);
            count.put(n, count.getOrDefault(n, 0) + 1);

            if (count.get(n) >= K && n < recommend) {
                recommend = n;
            }
            if (recommend == 100001) {
                System.out.println(-1);
            } else {
                System.out.println(recommend);
            }
        }

    }
}
