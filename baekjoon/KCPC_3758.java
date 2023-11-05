package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class KCPC_3758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] teams = new int[n + 1][k + 1];
            int[][] criteria = new int[n][4]; // 팀번호, 최종 점수, 제출 수, 마지막 제출 시간
            for (int idx = 0; idx < m; idx++) {
                st = new StringTokenizer(br.readLine());
                int teamId = Integer.parseInt(st.nextToken());
                int problemNumber = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());
                criteria[teamId - 1][0] = teamId;
                criteria[teamId - 1][2] += 1;
                criteria[teamId - 1][3] = idx;
                if (teams[teamId][problemNumber] < score) {
                    criteria[teamId - 1][1] += (score - teams[teamId][problemNumber]);
                    teams[teamId][problemNumber] = score;
                }
            }
            Arrays.sort(criteria, Comparator.comparingInt((int[] o) -> o[1]).reversed()
                    .thenComparingInt((int[] o) -> o[2])
                    .thenComparingInt((int[] o) -> o[3]));
            int rank = 0;
            for (int tn = 0; tn < n; tn++) {
                if (criteria[tn][0] == t) {
                    rank = tn + 1;
                    break;
                }
            }
            System.out.println(rank);
        }

    }
}
