package programmers;

import java.util.*;

public class 주사위고르기 {

    static Map<Set<Integer>, Map<Integer, Integer>> combScoreMap;
    static Map<Set<Integer>, Map<Integer, Integer>> beforeCombScoreMap;

    static Map<Integer, Map<Integer, Integer>> diceScoreMap;
    static int maxWin = 0;
    static int[] answer;

    public static void main(String[] args) {
        // [1,4]
        System.out.println(solution(new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}}));
        // [2]
        System.out.println(solution(new int[][]{{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}}));
        // [1,3]
        System.out.println(solution(new int[][]{{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}}));
    }

    public static int[] solution(int[][] dice) {
        maxWin = 0;
        answer = new int[dice.length / 2];
        combScoreMap = new HashMap<>();
        beforeCombScoreMap = new HashMap<>();
        diceScoreMap = new HashMap<>();
        int index = 1;

        for (int i = 1; i <= dice.length; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            diceScoreMap.put(i, countMap);
            for (int j = 0; j < 6; j++) {
                int num = dice[i - 1][j];
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : diceScoreMap.entrySet()) {
            Integer num = entry.getKey();
            Map<Integer, Integer> scoreMap = entry.getValue();
            combScoreMap.put(new HashSet<>(List.of(num)), scoreMap);
        }

        while (index < dice.length / 2) {
            beforeCombScoreMap = new HashMap<>(combScoreMap);
            combScoreMap = new HashMap<>();

            for (Set<Integer> set : beforeCombScoreMap.keySet()) {
                Map<Integer, Integer> beforeMap = beforeCombScoreMap.get(set);
                for (Map.Entry<Integer, Map<Integer, Integer>> e1 : diceScoreMap.entrySet()) {
                    int diceNum = e1.getKey();
                    if (set.contains(diceNum)) {
                        continue;
                    }
                    Set<Integer> newSet = new HashSet<>(set);
                    newSet.add(diceNum);

                    if (combScoreMap.containsKey(newSet)) {
                        continue;
                    }

                    Map<Integer, Integer> scoreMap = e1.getValue();

                    Map<Integer, Integer> newMap = new HashMap<>();
                    combScoreMap.put(newSet, newMap);

                    for (Map.Entry<Integer, Integer> e2 : scoreMap.entrySet()) {
                        int num1 = e2.getKey();
                        int count1 = e2.getValue();

                        for (Map.Entry<Integer, Integer> e3 : beforeMap.entrySet()) {
                            int num2 = e3.getKey();
                            int count2 = e3.getValue();
                            int sum = num1 + num2;
                            newMap.put(sum, newMap.getOrDefault(sum, 0) + count1 * count2);
                        }
                    }
                }

            }
            index++;
        }
        dfs(new HashSet<>(), dice.length, 1);

        return answer;
    }

    private static void dfs(Set<Integer> diceSet, int diceSize, int begin) {
        if (diceSet.size() == diceSize / 2) {
            Set<Integer> opponentSet = new HashSet<>(diceScoreMap.keySet());
            opponentSet.removeAll(diceSet);
            Map<Integer, Integer> dice1 = combScoreMap.get(diceSet);
            Map<Integer, Integer> dice2 = combScoreMap.get(opponentSet);
            int[] wdl = new int[3]; // win, draw, lose

            for (Map.Entry<Integer, Integer> entry : dice1.entrySet()) {
                int num1 = entry.getKey();
                int count1 = entry.getValue();

                for (Map.Entry<Integer, Integer> entry2 : dice2.entrySet()) {
                    int num2 = entry2.getKey();
                    int count2 = entry2.getValue();
                    if (num1 > num2) {
                        wdl[0] += count1 * count2;
                    } else if (num1 < num2) {
                        wdl[1] += count1 * count2;
                    } else {
                        wdl[2] += count1 * count2;
                    }
                }
            }
            if (maxWin < wdl[0]) {
                maxWin = wdl[0];
                List<Integer> list = new ArrayList<>(diceSet);
                list.sort(Comparator.naturalOrder());
                for (int i = 0; i < list.size(); i++) {
                    answer[i] = list.get(i);
                }
            }
            return;
        }

        for (int i = begin; i <= diceSize; i++) {
            diceSet.add(i);
            dfs(diceSet, diceSize, i + 1);
            diceSet.remove(i);
        }
    }
}
