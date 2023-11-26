package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스카이라인쉬운거1863 {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        Set<Integer> heightSet = new HashSet<>();
        int x = 0;
        int y = 0;
        int[][] buildings = new int[n][2];
        int count = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            buildings[i][0] = x;
            buildings[i][1] = y;
        }
//        Arrays.sort(buildings, new Comparator<int []>(){
//            @Override
//            public int compare(int[] a, int[] b) {
//                return b[0] - a[0];
//            }
//        });
//        Arrays.sort(buildings, (a, b) -> {return a[0] - b[0];});
//        Comparator<int[]> reverse = Comparator.comparingInt((int[] o) -> o[1]).reversed());
        Arrays.sort(buildings, Comparator.comparingInt((int[] o) -> o[0]));
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(buildings[i]));
//        }
        for (int i = 0; i < n; i++) {
            y = buildings[i][1];
            while (!stack.isEmpty() && stack.lastElement() > y) {
                heightSet.remove(stack.pop());
                count += 1;
            }
            if (y > 0 && !heightSet.contains(y) || !stack.empty() && stack.lastElement() < y) {
                stack.push(y);
                heightSet.add(y);
            }
        }
        count += stack.size();

        System.out.println(count);
    }
}
