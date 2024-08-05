package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 괄호추가하기16637 {
    static int N;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] num = new int[N / 2 + 1];
        char[] operator = new char[N / 2];

        for (int i = 0; i < N; i++) {
            if ((i & 1) == 0) {
                num[i / 2] = str.charAt(i) - '0';
            } else {
                operator[i / 2] = str.charAt(i);
            }
        }

        max = num[0];

        for (int i = 0; i < N / 2; i++) {
            switch (operator[i]) {
                case '+':
                    max += num[i + 1];
                    break;
                case '-':
                    max -= num[i + 1];
                    break;
                case '*':
                    max *= num[i + 1];
                    break;
            }
        }

        dfs(num, operator, 0, new HashSet<>());
        System.out.println(max);
    }

    private static void dfs(int[] num, char[] operator, int begin, Set<Integer> operatorSet) {
        calculate(num, operator, operatorSet);

        for (int i = begin; i < N / 2; i++) {
            operatorSet.add(i);
            dfs(num, operator, i + 2, operatorSet);
            operatorSet.remove(i);
        }
    }

    private static void calculate(int[] num, char[] operator, Set<Integer> operatorSet) {
        List<Integer> tempNum = new ArrayList<>();
        List<Character> tempOperator = new ArrayList<>();
        int[] visitedNum = new int[N / 2 + 1];

        for (int i = 0; i < N / 2; i++) {
            if (operatorSet.contains(i)) {
                visitedNum[i] = 1;
                visitedNum[i + 1] = 1;
                switch (operator[i]) {
                    case '+':
                        tempNum.add(num[i] + num[i + 1]);
                        break;
                    case '-':
                        tempNum.add(num[i] - num[i + 1]);
                        break;
                    case '*':
                        tempNum.add(num[i] * num[i + 1]);
                        break;

                }
            } else {
                if (visitedNum[i] == 0) {
                    tempNum.add(num[i]);
                    visitedNum[i] = 1;
                }
                tempOperator.add(operator[i]);
            }
        }

        if (visitedNum[N / 2] == 0) {
            tempNum.add(num[N / 2]);
        }

        int sum = tempNum.get(0);

        for (int i = 0; i < tempOperator.size(); i++) {
            switch (tempOperator.get(i)) {
                case '+':
                    sum += tempNum.get(i + 1);
                    break;
                case '-':
                    sum -= tempNum.get(i + 1);
                    break;
                case '*':
                    sum *= tempNum.get(i + 1);
                    break;
            }
        }
        max = Math.max(max, sum);
    }
}
