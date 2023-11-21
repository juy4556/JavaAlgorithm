package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 비슷한_단어_2607 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String word = br.readLine();
        Map<Character, Integer> characters = new HashMap<>();
        int result = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            characters.put(c, characters.getOrDefault(c, 0) + 1);
        }
        int characterCount = characters.size();
        for (int i = 1; i < n; i++) {
            Map<Character, Integer> tempMap = new HashMap<>(characters);
            String str = br.readLine();
            Set<Character> charSet = new HashSet<>();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                charSet.add(c);
                tempMap.put(c, tempMap.getOrDefault(c, 0) - 1);
            }
            boolean similar = true;
            int diffCount = 0;
            int val = 0;
            for (Integer value : tempMap.values()) {
                int count = Math.abs(value);
                if (count > 1) {
                    similar = false;
                    break;
                } else if (count == 1) {
                    diffCount++;
                    val += value;
                }
            }

            if (diffCount > 2 || Math.abs(charSet.size() - characterCount) > 1) similar = false;
            if (diffCount == 2 && val != 0) similar = false;

            if (similar) {
                result += 1;
            }

        }
        System.out.println(result);
    }
}
