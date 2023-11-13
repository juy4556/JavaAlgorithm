package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 겹치는_건_싫어_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        Map<Integer, Integer> numCnt = new HashMap<>();
        List<Integer> arrList = new ArrayList<>(N);
        arrList.add(arr[0]);
        numCnt.put(arr[0], 1);
        int result = 1;
        for (int i = 1; i < N; i++) {
            if (numCnt.getOrDefault(arr[i], 0) + 1 <= K) {
                arrList.add(arr[i]);
                numCnt.put(arr[i], numCnt.getOrDefault(arr[i], 0) + 1);
            } else {
                while (arrList.get(0) != arr[i]) {
                    numCnt.put(arrList.get(0), numCnt.get(arrList.get(0)) - 1);
                    arrList.remove(0);
                }
                arrList.remove(0);
                arrList.add(arr[i]);
            }
            result = Math.max(result, arrList.size());
        }
        System.out.println(result);
    }
}
