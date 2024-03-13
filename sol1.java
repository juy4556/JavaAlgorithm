import java.util.*;

public class sol1 {
    public static void main(String[] args) {
        int k = 3;
        int[] score = {24, 22, 20, 10, 5, 3, 2, 1};
        System.out.println(solution(k, score));
        int k2 = 2;
        int[] score2 = {1300000000, 700000000, 668239490, 618239490, 568239490, 568239486, 518239486, 157658638, 157658634, 100000000, 100};
        System.out.println(solution(k2, score2));
    }

    public static int solution(int k, int[] score) {
        int answer = score.length;
        int[] diff = new int[score.length - 1];
        boolean[] check = new boolean[score.length];
        for (int i = 0; i < score.length - 1; i++) {
            diff[i] = score[i] - score[i + 1];
        }
        Map<Integer, Set<Integer>> count = new HashMap<>();
        for (int i = 0; i < diff.length; i++) {
            count.putIfAbsent(diff[i], new HashSet<>());
            count.get(diff[i]).add(i);
        }
        for (int key : count.keySet()) {
            Set<Integer> ranks = count.get(key);
            int size = ranks.size();
            if (size >= k) {
                System.out.println("rank:" + ranks);
                for (int n : ranks) {
                    check[n] = true;
                    check[n + 1] = true;
                }
            }
        }
        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                answer--;
            }
        }
        return answer;
    }
}
