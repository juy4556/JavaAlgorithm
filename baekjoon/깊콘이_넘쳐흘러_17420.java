package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 깊콘이_넘쳐흘러_17420 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long result = 0;
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> gifticons = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gifticons.add(new ArrayList<>());
            gifticons.get(i).add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            gifticons.get(i).add(n);
        }
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
                Comparator.comparing((List<Integer> list) -> list.get(1)).thenComparingInt(list -> list.get(0)));
        for (int i = 0; i < N; i++) {
            pq.add(gifticons.get(i));
        }
//    gifticons.sort(Comparator.comparing((List<Integer> list) -> list.get(1)).thenComparingInt(list -> list.get(0)));
        double lastDay = pq.peek().get(1);
        double lastMaxDay = pq.peek().get(1);
        double curMaxDay = 0;
        while (!pq.isEmpty()) {
            List<Integer> gifticon = pq.poll();
            double deadLine = gifticon.get(0);
            double planDay = gifticon.get(1);
            if (lastDay < planDay) {
                lastDay = planDay;
                lastMaxDay = curMaxDay;
            }

            if (deadLine < lastMaxDay || deadLine < planDay) {
                double maxDay = Math.max(lastMaxDay, planDay);
                long extendCount = (long) Math.ceil((maxDay - deadLine) / 30);
                deadLine += extendCount * 30;
                result += extendCount;
            }
            curMaxDay = Math.max(curMaxDay, deadLine);
        }

        System.out.println(result);
    }
}
