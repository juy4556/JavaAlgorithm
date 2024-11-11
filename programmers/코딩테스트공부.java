package programmers;


public class 코딩테스트공부 {
    static final int MAX_POWER = 151;

    public static void main(String[] args) {
        System.out.println(solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}}));
        System.out.println(solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}}));
        System.out.println(solution(10, 1, new int[][]{{1, 1, 1, 1, 1}, {5, 5, 1, 1, 3}}));

    }

    private static int solution(int alp, int cop, int[][] problems) {
        int answer = MAX_POWER;
        int[][] dp = new int[MAX_POWER][MAX_POWER];  // 0 ≤ alp,cop ≤ 150

        int maxA = alp;  // 주어진 문제 중 가장 높은 alp
        int maxC = cop;  // 주어진 문제 중 가장 높은 cop

        for (int i = 0; i < problems.length; i++) {
            maxA = Math.max(maxA, problems[i][0]);
            maxC = Math.max(maxC, problems[i][1]);
        }

        for (int i = alp; i < MAX_POWER; i++) {
            for (int j = cop; j < MAX_POWER; j++) {
                dp[i][j] = i + j - alp - cop;
            }
        }

        for (int i = alp; i < MAX_POWER; i++) {
            for (int j = cop; j < MAX_POWER; j++) {
                if (i + 1 < MAX_POWER) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 < MAX_POWER) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (int k = 0; k < problems.length; k++) {
                    int[] problem = problems[k];
                    int alpReq = problem[0];
                    int copReq = problem[1];
                    int alpRwd = problem[2];
                    int copRwd = problem[3];
                    int cost = problem[4];

                    if (i < alpReq || j < copReq) {
                        continue;
                    }
                    int a = Math.min(maxA, i + alpRwd);
                    int c = Math.min(maxC, j + copRwd);
                    dp[a][c] = Math.min(dp[a][c], dp[i][j] + cost);
                }
            }
        }

        for (int i = maxA; i < MAX_POWER; i++) {
            for (int j = maxC; j < MAX_POWER; j++) {
                answer = Math.min(answer, dp[i][j]);
            }
        }

        return answer;
    }
}
