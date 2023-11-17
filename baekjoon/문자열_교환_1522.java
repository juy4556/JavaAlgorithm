package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열_교환_1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int length = s.length();
        int aCount = 0;
        int bCount = 0;
        int i;
        int result = 1001;
        String window;
        String str = "";
        for (i = 0; i < length; i++) {
            if (s.charAt(i) == 'a') aCount += 1;
        }
        i = 0;
        while (i < length) {
            window = "";
            bCount = 0;
            if (i + aCount > length) {
                window = s.substring(i) + s.substring(0, aCount - (length - i));
            } else {
                window = s.substring(i, i + aCount);
            }
            for (int j = 0; j < window.length(); j++) {
                if (window.charAt(j) == 'b') bCount += 1;
            }
            result = Math.min(result, bCount);
            i += 1;
        }

        System.out.println(result);
    }
}
