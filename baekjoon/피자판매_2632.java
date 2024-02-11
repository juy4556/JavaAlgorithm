package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 피자판매_2632 {
    static int pizzaSize;
    static int m, n;
    static int[] A, B;
    static List<Integer> a, b;

    public static void main(String[] args) throws IOException {
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pizzaSize = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = new int[m];
        B = new int[n];
        for (int i = 0; i < m; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(br.readLine());
        }
        a = new ArrayList<>(m * 1000);
        b = new ArrayList<>(n * 1000);
        int sum = 0;
        calculateSum(a, A, m);
        calculateSum(b, B, n);
        a.sort(Comparator.naturalOrder());
        b.sort(Comparator.naturalOrder());
        for (int i = 0; i <= pizzaSize; i++) {
            result += binarySearch(i, 0, a.size() - 1, a) *
                    binarySearch(pizzaSize - i, 0, b.size() - 1, b);
        }
        System.out.println(result);
    }

    public static void calculateSum(List<Integer> list, int[] arr, int size) {
        int sum = 0;
        list.add(0);
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < size - 1; j++) {
                sum += arr[(i + j) % size];
                list.add(sum);
            }
        }
        list.add(sum + arr[size - 2]);
    }

    public static int binarySearch(int target, int start, int end, List<Integer> list) {
        int count = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) == target) {
                count++;
                int temp = mid - 1;
                while (temp >= 0 && list.get(temp) == target) {
                    count++;
                    temp--;
                }
                temp = mid + 1;
                while (temp < list.size() && list.get(temp) == target) {
                    count++;
                    temp++;
                }
                return count;
            } else if (list.get(mid) > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return count;
    }
}
