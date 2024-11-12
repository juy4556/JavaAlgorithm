package programmers;

import java.util.Arrays;

public class 택배배달과수거하기 {
    public static void main(String[] args) {
        System.out.println(solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
        System.out.println(solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
    }

    private static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long dist = 0;
        int dEnd = n - 1;
        int pEnd = n - 1;

        while (dEnd >= 0 && deliveries[dEnd] == 0) {
            dEnd -= 1;
        }

        while (pEnd >= 0 && pickups[pEnd] == 0) {
            pEnd -= 1;
        }

        while (dEnd >= 0 || pEnd >= 0) {
            dist += (Math.max(dEnd, pEnd) + 1) * 2;
            int canMove = cap;

            while (dEnd >= 0) {
                if (canMove >= deliveries[dEnd]) {
                    canMove -= deliveries[dEnd];
                    deliveries[dEnd] = 0;
                    dEnd -= 1;
                    continue;
                }
                deliveries[dEnd] -= canMove;
                break;
            }

            canMove = cap;
            while (pEnd >= 0) {
                if (canMove >= pickups[pEnd]) {
                    canMove -= pickups[pEnd];
                    pickups[pEnd] = 0;
                    pEnd -= 1;
                    continue;
                }
                pickups[pEnd] -= canMove;
                break;
            }
        }
        return dist;
    }
}
