/*
    하루 5개 공급 가능
    최대 공급량?
 */
public class Main {
    public static void main(String[] args) {
        int producePerDay = 5;
        int requestsCount = 6;
        // day, consumeCount
        int[][] requests = new int[requestsCount][2];
        requests[0] = new int[]{10, 60};
        requests[1] = new int[]{12, 60};
        requests[2] = new int[]{15, 30};
        requests[3] = new int[]{20, 80};
        requests[4] = new int[]{25, 30};
        requests[5] = new int[]{30, 20};
        int total = requests[requestsCount - 1][0] * producePerDay;
        int[][] dp = new int[requestsCount + 1][total + 1];
        for (int i = 1; i < requestsCount + 1; i++) {
            int day = requests[i - 1][0];
            int consumeCount = requests[i - 1][1];
            for (int j = 0; j < total + 1; j++) {
                if (consumeCount <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j - consumeCount] + consumeCount, dp[i - 1][j]);
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[requestsCount][total]);
    }
}
