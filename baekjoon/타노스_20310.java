package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class 타노스_20310 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String S = br.readLine();
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1') {
                oneCount += 1;
            } else if (S.charAt(i) == '0') {
                zeroCount += 1;
            }
        }
        ArrayList<int[]> arrList = new ArrayList<>();
        int oneCnt = 0;
        int zeroCnt = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                zeroCnt += 1;
                arrList.add(new int[]{i, 0});
            }
            if (zeroCnt == zeroCount / 2) {
                break;
            }
        }

        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == '1') {
                oneCnt += 1;
                arrList.add(new int[]{i, 1});
            }
            if (oneCnt == oneCount / 2) {
                break;
            }
        }
        arrList.sort(Comparator.comparing((int[] o) -> o[0]));
        StringBuilder sb = new StringBuilder();
        for (int[] x : arrList) {
            sb.append(x[1]);
        }
        System.out.println(sb);
    }
}
