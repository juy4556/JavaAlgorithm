package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회문_17609 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            int start = 0;
            int end = s.length() - 1;
            int diffCount = 0;
            while (start < end) {
                System.out.println(s.charAt(start) + " " + s.charAt(end));
                System.out.println(start + " " + end);
                if (s.charAt(start) == s.charAt(end)) {
                    start += 1;
                    end -= 1;
                } else if (start + 1 < end && s.charAt(start + 1) == s.charAt(end)) {
                    diffCount += 1;
                    start += 1;
                } else if (start < end - 1 && s.charAt(start) == s.charAt(end - 1)) {
                    diffCount += 1;
                    end -= 1;
                } else {
                    diffCount += 2;
                }
                if (diffCount > 1) {
                    break;
                }
            }
            // 회문 : 0, 유사회문 : 1, 그 외 : 2
            if (diffCount > 1) {
                System.out.println(2);
            } else {
                System.out.println(diffCount);
            }
        }
    }
}
