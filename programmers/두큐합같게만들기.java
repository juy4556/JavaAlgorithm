package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 두큐합같게만들기 {
    public static void main(String[] args) {
    }

    private int solution(int[] queue1, int[] queue2) {
        long dest = 0L;
        long sum1 = 0L;
        long sum2 = 0L;
        int count = 0;
        int limit = 3 * queue1.length - 3;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        dest = (sum1 + sum2) / 2;

        while (true) {
            if (count > limit) {
                count = -1;
                break;
            }
            if (sum1 == dest) {
                break;
            } else if (sum1 < dest) {
                int polled = q2.poll();
                sum1 += polled;
                sum2 -= polled;
                q1.add(polled);
                count += 1;
            } else {
                int polled = q1.poll();
                sum1 -= polled;
                sum2 += polled;
                q2.add(polled);
                count += 1;
            }
        }

        return count;
    }
}
