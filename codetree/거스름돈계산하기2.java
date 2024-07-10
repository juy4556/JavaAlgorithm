package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 거스름돈계산하기2 {
    static StringTokenizer st;
    static Map<Integer, Integer> coinCount;
    static int MAX_VALUE = 1_000_000_000;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        coinCount = new TreeMap<>((a, b) -> b - a);
        int[] change = new int[S + 1];
        for (int i = 1; i <= S; i++) {
            change[i] = MAX_VALUE;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            coinCount.put(value, count);
        }

        for (Map.Entry<Integer, Integer> entry : coinCount.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i <= S; i++) {
                for (int j = 1; j <= count; j++) {
                    int sum = i + value * j;
                    if (sum > S) {
                        break;
                    }
                    change[sum] = Math.min(change[sum], change[i] + j);
                }
            }
        }

        if (change[S] == MAX_VALUE) {
            result = -1;
        } else {
            result = change[S];
        }
        System.out.println(result);
    }
}
