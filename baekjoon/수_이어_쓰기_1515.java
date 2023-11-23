package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 수_이어_쓰기_1515 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = 1;
        int idx = 0;
        while (n <= 30000) {
            String s = String.valueOf(n);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == str.charAt(idx)) {
                    idx += 1;
                }
                if (idx == str.length()) {
                    System.out.println(n);
                    return;
                }
            }
            n += 1;
        }
    }
}
