package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발9935 {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        int bl = bomb.length();
        Stack<Character> st = new Stack<>();
        int sl = 0;

        for (int i = 0; i < str.length(); i++) {
            st.push(str.charAt(i));
            sl = st.size();

            if (sl >= bl && st.get(st.size() - 1) == bomb.charAt(bl - 1)) {
                boolean flag = false;
                for (int j = sl - 1; j > sl - 1 - bl; j--) {
                    if (st.get(j) != bomb.charAt(bl - 1 - (sl - 1 - j))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    for (int k = 0; k < bl; k++) {
                        st.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : st) {
            sb.append(c);
        }

        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}
