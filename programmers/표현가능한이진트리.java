package programmers;

import java.util.Arrays;

public class 표현가능한이진트리 {

    static StringBuilder sb;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new long[]{7, 42, 5, 63, 111, 95})));
        System.out.println(Arrays.toString(solution(new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
    }

    private static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = getBinary(numbers[i]);
            binary = addDummy(binary);
            if (isRepresentable(binary, binary.length() / 2)) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    private static String getBinary(long number) {
        sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % 2);
            number /= 2;
        }
        return sb.reverse().toString();
    }

    private static String addDummy(String binary) {
        sb = new StringBuilder(binary);
        while ((Math.log(sb.length() + 1) / Math.log(2)) % 1 != 0) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    private static boolean isRepresentable(String binary, int mid) {
        String left = binary.substring(0, mid);
        String right = binary.substring(mid + 1);

        if (binary.charAt(mid) == '0' &&
                (left.charAt(left.length() / 2) == '1' || right.charAt(right.length() / 2) == '1')) {
            return false;
        }

        if (left.length() > 1) {
            if (!isRepresentable(left, left.length() / 2)) {
                return false;
            }
            if (!isRepresentable(right, right.length() / 2)) {
                return false;
            }
        }

        return true;
    }

    /*
        1 1     1
        2 10    3
        3 11    3(2^2 - 1)
        4 100   3
        5 101   3
        6 110   3
        7 111   3
        8 1000  7
        9 1001  7

        1(2^0)인 경우 노드 수 1(2^1-1)
        2(2^1)~7(2^3-1)인 경우 노드 수 3(2^2-1)
        8(2^3)~127(2^7-1)인 경우 노드 수 7(2^3-1)
        128(2^7)~(2^15-1)인 경우 노드 수 15(2^4-1)
     */
}
