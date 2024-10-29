package programmers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class n더하기1카드게임 {
    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12}));
    }

    public static int solution(int coin, int[] cards) {
        Set<Integer> firstCards = new HashSet<>();
        Set<Integer> chosenCards = new HashSet<>();
        int n = cards.length;
        int round = 0;
        int canNext = 1;
        boolean[] canOut = new boolean[n / 3];

        for (int i = 0; i < n / 3; i++) {
            if (canOut[i]) continue;
            for (int j = i + 1; j < n / 3; j++) {
                if (cards[i] + cards[j] == n + 1) {
                    canOut[i] = true;
                    canOut[j] = true;
                    canNext++;
                    break;
                }
            }
            if (!canOut[i]) {
                firstCards.add(cards[i]);
            }
        }

        int index = n / 3;
        while (index < n && canNext > 0) {
            round++;
            canNext--;
            chosenCards.add(cards[index]);
            chosenCards.add(cards[index + 1]);

            if (canNext == 0 && coin > 0) {
                List<Integer> cardList = chosenCards.stream().collect(Collectors.toList());
                for (int num : cardList) {
                    if (firstCards.contains(n + 1 - num) && coin > 0) {
                        canNext++;
                        coin--;
                        chosenCards.remove(num);
                        firstCards.remove(n + 1 - num);
                    }
                }
                if (canNext == 0 && coin > 0) {
                    cardList = chosenCards.stream().collect(Collectors.toList());
                    for (int num : cardList) {
                        if (chosenCards.contains(n + 1 - num) && coin > 1) {
                            canNext++;
                            coin -= 2;
                            chosenCards.remove(num);
                            chosenCards.remove(n + 1 - num);
                            break;
                        }
                    }
                }
            }

            index += 2;
        }
        round += canNext;

        return round;
    }
}
