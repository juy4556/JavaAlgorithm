package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 연료_채우기_1826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 주유소 개수
        StringTokenizer st;
        int[][] stations = new int[N + 1][2];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int distance = Integer.parseInt(st.nextToken()); // 거리
            int fuel = Integer.parseInt(st.nextToken()); // 연료
            stations[i][0] = distance;
            stations[i][1] = fuel;
        }
        st = new StringTokenizer(br.readLine());
        int goal = Integer.parseInt(st.nextToken()); // 목적지
        int fuel = Integer.parseInt(st.nextToken()); // 원래 갖고 있던 연료
        stations[N][0] = goal;
        Arrays.sort(stations, Comparator.comparingInt(o -> o[0]));

        int index = 0;
        int start = 0;
        int count = 0;
        while (start + fuel < goal) {

            int max_move = 0;
            while (index < N + 1 && stations[index][0] - start <= fuel) {
                priorityQueue.add(stations[index][1]);
                index++;
            }
            if (index > 0) {
                max_move = stations[index - 1][0] - start;
                fuel -= max_move;
                start += max_move;
            }
            while (index < N + 1 && stations[index][0] > start + fuel) {
                if (priorityQueue.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                fuel += priorityQueue.poll();
                count++;
            }
            if (start + fuel >= goal) {
                break;
            }
        }
        System.out.println(count);
    }
}
