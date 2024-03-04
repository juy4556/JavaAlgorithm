package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호추가하기_16637 {
    static int N;
    static int result = Integer.MIN_VALUE;
    static int[] numbers;
    static char[] operators;
    static int operatorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operatorCount = N / 2;
        numbers = new int[operatorCount + 1];
        operators = new char[operatorCount];
        String expression = br.readLine();
        for (int i = 0; i < N; i++) {
            if ((i & 1) == 0) {
                numbers[i / 2] = expression.charAt(i) - '0';
            } else {
                operators[i / 2] = expression.charAt(i);
            }
        }
        dfs(0, numbers[0]);
        System.out.println(result);
    }

    public static void dfs(int depth, int now) {
        if (depth == operatorCount) {
            result = Math.max(result, now);
            return;
        }
        int next = numbers[depth + 1];
        char operator = operators[depth];
        // normal calculation
        dfs(depth + 1, calculate(now, next, operator));
        // add bracket
        if (depth + 1 < operatorCount) {
            int nextNext = numbers[depth + 2];
            int bracketResult = calculate(next, nextNext, operators[depth + 1]);
            dfs(depth + 2, calculate(now, bracketResult, operator));
        }
    }

    public static int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
