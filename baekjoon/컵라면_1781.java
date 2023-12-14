package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 컵라면_1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 0L;
        int[][] problems = new int[N][2]; // deadline, cup
        Queue<Integer> queue = new PriorityQueue<>(((o1, o2) -> Integer.compare(o2, o1)));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            problems[i][0] = deadline;
            problems[i][1] = cup;
        }
        Arrays.sort(problems, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        int index = 0;
        for (int i = N; i >= 1; i--) {
            while (index < N && problems[index][0] >= i) {
                queue.add(problems[index++][1]);
            }
            if (!queue.isEmpty()) {
                result += queue.poll();
            }
        }

        System.out.println(result);
    }
}
