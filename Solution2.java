import java.util.*;

public class Solution2 {
    static int[] basket;
    static List<int[]> apples;
    static int[] dp;

    public static void main(String[] args) {
        int N = 7;
        int T = 5;
        basket = new int[]{1, N};
        apples = List.of(new int[]{2, 1}, new int[]{2, 7}, new int[]{3, 2}, new int[]{3, 3}, new int[]{3, 6},
                new int[]{4, 1}, new int[]{4, 3}, new int[]{4, 7});
        dp = new int[N + 1];
        Map<Integer, List<Integer>> appleMap = new HashMap<>();
        for (int[] apple : apples) {
            int x = apple[0];
            int y = apple[1];
            appleMap.putIfAbsent(x, new ArrayList<>());
            appleMap.get(x).add(y);
        }
        for (int i = 1; i <= T; i++) {
            if (appleMap.containsKey(i)) {
                for (int num : appleMap.get(i)) {
                    if (num == 1) {
                        dp[num] = Math.max(dp[num], dp[num + 1]) + 1;
                    } else if (num == N) {
                        dp[num] = Math.max(dp[num], dp[num - 1]) + 1;
                    } else {
                        int temp = Math.max(dp[num - 1], dp[num + 1]);
                        dp[num] = Math.max(dp[num], temp) + 1;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
