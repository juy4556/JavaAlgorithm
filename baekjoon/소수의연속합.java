package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 소수의연속합 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> primeNumber = new ArrayList<>();
        int left = 0;
        int right = 1;
        int result = 0;

        for (int i = 2; i <= N; i++) {
            if (!isPrime(i)) {
                continue;
            }
            primeNumber.add(i);
        }
        int primeCounts = primeNumber.size();

        while (left < primeCounts) {
            int sum = primeNumber.get(left);
            while (right < primeCounts && sum < N) {
                sum += primeNumber.get(right++);
            }
            if (sum == N) {
                result++;
            }
            left += 1;
            right = left + 1;
            sum = 0;
        }

        System.out.println(result);
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
