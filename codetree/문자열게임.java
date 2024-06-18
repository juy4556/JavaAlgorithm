package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class 문자열게임 {
    static String S, T;
    static ArrayDeque<Character> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        queue.add(S.charAt(0));
        dfs(queue, false);
        System.out.println(-1);
    }

    private static void dfs(ArrayDeque<Character> q, boolean isReversed) {
        System.out.println(q + " " + isReversed);
        if (q.size() == T.length()) {
            StringBuilder sb = new StringBuilder();
            if (isReversed) {
                for (Character c : q) {
                    sb.append(c);
                }
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(sb.length() - i - 1) != T.charAt(i)) {
                        return;
                    }
                }
                System.out.println(1);
                System.exit(0);
            } else {
                for (Character c : q) {
                    sb.append(c);
                }
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) != T.charAt(i)) {
                        return;
                    }
                }
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (isReversed) {
            q.addFirst('A');
            dfs(q, true);
            q.pollFirst();
            q.addFirst('B');
            dfs(q, false);
            q.pollFirst();
        } else {
            q.addLast('A');
            dfs(q, false);
            q.pollLast();
            q.addLast('B');
            dfs(q, true);
            q.pollLast();
        }

    }
}
