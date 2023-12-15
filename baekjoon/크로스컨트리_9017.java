package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 크로스컨트리_9017 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            HashMap<Integer, Integer> teamCount = new HashMap<>();
            HashMap<Integer, ArrayList<Integer>> teamScore = new HashMap<>();
            init(arr, st, n, teamCount);
            scoreTeam(n, arr, teamCount, teamScore);
            ArrayList<Integer> candidates = getCandidates(teamCount, teamScore);
            printResult(teamScore, candidates);
        }
    }

    private static void printResult(HashMap<Integer, ArrayList<Integer>> teamScore, ArrayList<Integer> candidates) {
        int winner = candidates.get(0);
        if (candidates.size() > 1) {
            int min = 1001;
            for (int candidate : candidates) {
                int fifthScore = teamScore.get(candidate).get(4);
                if (fifthScore < min) {
                    min = fifthScore;
                    winner = candidate;
                }
            }
        }
        System.out.println(winner);
    }

    private static ArrayList<Integer> getCandidates(HashMap<Integer, Integer> teamCount, HashMap<Integer, ArrayList<Integer>> teamScore) {
        ArrayList<Integer> candidates = new ArrayList<>();
        int min = 6000;
        for (Integer key : teamCount.keySet()) {
            if (teamCount.get(key) < 6) {
                continue;
            }
            int summary = 0;
            for (int k = 0; k < 4; k++) {
                summary += teamScore.get(key).get(k);
            }
            if (min > summary) {
                min = summary;
                candidates = new ArrayList<>();
                candidates.add(key);
            } else if (min == summary) {
                candidates.add(key);
            }
        }
        return candidates;
    }

    private static void init(int[] arr, StringTokenizer st, int n, HashMap<Integer, Integer> teamCount) {
        for (int i = 0; i < n; i++) {
            int teamNumber = Integer.parseInt(st.nextToken());
            arr[i] = teamNumber;
            teamCount.put(teamNumber, teamCount.getOrDefault(teamNumber, 0) + 1);
        }
    }

    private static void scoreTeam(int n, int[] arr, HashMap<Integer, Integer> teamCount, HashMap<Integer, ArrayList<Integer>> teamScore) {
        int score = 1;
        for (int j = 0; j < n; j++) {
            int num = arr[j];
            if (teamCount.get(num) < 6) {
                continue;
            }
            if (!teamScore.containsKey(num)) {
                teamScore.put(num, new ArrayList<>());
            }
            teamScore.get(num).add(score);
            score += 1;
        }
    }
}
