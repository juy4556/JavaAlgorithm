package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 올바른쌍의괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int left = 0;
        int right = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                left++;
            } else if (str.charAt(i) == ')') {
                right++;
            }

            if (right > left) {
                System.out.println("No");
                return;
            }
        }

        if (left == right) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
