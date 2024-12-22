package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 너봄에는캡사이신이맛있단다_15824 {
    static final int MOD = 1_000_000_007;
    static int N;
    static List<Long> menu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;
        N = Integer.parseInt(br.readLine());
        menu = new ArrayList<>(N);
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            menu.add(Long.parseLong(input[i]));
        }
        menu.sort(Comparator.naturalOrder());

        if (menu.size() == 1) {
            System.out.println(0);
            return;
        }

        long count = 0;

        // (2^(n-1) - 1) * (menu.get(size() - 1) - menu.get(0)) + (2^(n-2) - 1) * (menu.get(size() - 2) - menu.get(1)) + ...
        for (int i = 0; i < menu.size(); i++) {
            long diff = (menu.get(i) - menu.get(menu.size() - 1 - i)) % MOD;
            result += (diff * count) % MOD;
            result %= MOD;
            count = ((count + 1) * 2 - 1) % MOD;
        }

        System.out.println(result % MOD);
    }
}
