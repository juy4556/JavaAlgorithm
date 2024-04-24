package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문_17609 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            int start = 0;
            int end = s.length() - 1;
            int result = 0;
            while (start < end) {
                if (s.charAt(start) == s.charAt(end)) {
                    start += 1;
                    end -= 1;
                } else {
                    if (start + 1 >= end) {
                        result = 1;
                        break;
                    }
                    if (start + 1 < end) {
                        int l = 0;
                        int r = s.length() - 1;
                        if (l == start) l++;
                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) break;
                            l++;
                            if (l == start) l++;
                            r--;
                        }
                        if (l >= r) {
                            result = 1;
                            break;
                        }
                    }
                    if (start < end - 1) {
                        int l = 0;
                        int r = s.length() - 1;
                        if (r == end) r--;
                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) break;
                            l++;
                            r--;
                            if (r == end) r--;
                        }
                        if (l >= r) {
                            result = 1;
                            break;
                        }
                    }
                    result = 2;
                    break;
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
