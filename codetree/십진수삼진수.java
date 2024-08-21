package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 십진수삼진수 {
    static String N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = br.readLine();
        int num = 0;
        int exp = 0;
        for (int i = N.length() - 1; i >= 0; i--) {
            num += (int) ((N.charAt(i) - '0') * Math.pow(3, exp++));
        }
        num *= 22;

        while (num > 0) {
            sb.append(num % 3);
            num /= 3;
        }
        System.out.println(sb.reverse());

    }
}
