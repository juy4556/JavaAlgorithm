package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 가르침_1062 {
    static int N, K;
    static String[] words;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        words = new String[N];
        int alphabetMask = ((1 << 0) | (1 << ('n' - 'a')) | (1 << ('t' - 'a')) | (1 << ('i' - 'a')) | (1 << ('c' - 'a')));

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        if (K == 26) {
            System.out.println(N);
            return;
        }

        dfs(0, 5, alphabetMask);
        System.out.println(answer);
    }

    private static void dfs(int alphabetIndex, int count, int alphabetMask) {
        if (count == K) {
            int result = 0;
            for (String word : words) {
                boolean isPossible = true;
                for (int i = 4; i < word.length() - 4; i++) {
                    if ((alphabetMask & (1 << (word.charAt(i) - 'a'))) == 0) {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    result++;
                }
            }
            answer = Math.max(answer, result);
            return;
        }

        for (int i = alphabetIndex; i < 26; i++) {
            if ((alphabetMask & (1 << i)) > 0) continue;
            dfs(i + 1, count + 1, alphabetMask | (1 << i));
        }
    }
}
