package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 에디터_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for (int n = 0; n < str.length(); n++) {
            stack1.push(str.charAt(n));
        }
        for (int m = 0; m < M; m++) {
            String command = br.readLine();
            if (command.charAt(0) == 'P') {
                char letter = command.charAt(2);
                stack1.push(letter);
            } else if (command.equals("L")) {
                if (!stack1.empty()) {
                    Character c = stack1.pop();
                    stack2.push(c);
                }
            } else if (command.equals("D")) {
                if (!stack2.empty()) {
                    Character c = stack2.pop();
                    stack1.push(c);
                }
            } else if (command.equals("B")) {
                if (!stack1.empty()) {
                    stack1.pop();
                }
            }
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        while (!stack2.empty()) {
            sb.append(stack2.pop());
        }
        System.out.println(sb);
    }

}
