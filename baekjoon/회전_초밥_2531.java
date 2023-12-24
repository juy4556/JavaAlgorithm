package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회전_초밥_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] sushies = new int[N];
        int result = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            sushies[i] = num;
        }
        int start = 0;
        int end = k - 1;
        Map<Integer, Integer> sushiCount = new HashMap<>();

        for (int i = start; i <= end; i++) {
            int num = sushies[i];
            sushiCount.put(num, sushiCount.getOrDefault(num, 0) + 1);
        }
        if (!sushiCount.keySet().contains(c)) {
            result += 1;
        }
        end = (end + 1) % N;
        while (start < N) {
            int endNum = sushies[end];
            int startNum = sushies[start];
            sushiCount.put(endNum, sushiCount.getOrDefault(endNum, 0) + 1);
            sushiCount.put(startNum, sushiCount.getOrDefault(startNum, 0) - 1);
            if (sushiCount.get(startNum) <= 0) sushiCount.remove(startNum);
            int count = sushiCount.size();
            if (!sushiCount.keySet().contains(c)) count += 1;
            result = Math.max(result, count);
            start += 1;
            end = (end + 1) % N;
        }
        System.out.println(result);
    }
}
