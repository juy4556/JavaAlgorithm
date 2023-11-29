package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class 영단어_암기는_괴로워_20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> words = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int count = words.getOrDefault(word, 0);
            words.put(word, count + 1);
        }
        List<String> keys = new ArrayList<>(words.keySet());
        keys.sort((o1, o2) -> {
            int count1 = words.get(o1);
            int count2 = words.get(o2);
            if (count1 == count2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return count2 - count1;
        });
        StringBuilder sb = new StringBuilder();
        for (String word : keys) {
            if (word.length() >= M) {
                sb.append(word).append('\n');
            }
        }
        System.out.println(sb);
    }
}
