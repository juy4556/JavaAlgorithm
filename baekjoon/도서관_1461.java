package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 도서관_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int farthest = 0;
        int result = 0;
        while (st.hasMoreTokens()) {
            int book = Integer.parseInt(st.nextToken());
            farthest = Math.max(farthest, Math.abs(book));
            if (book > 0) {
                positive.add(book);
            } else {
                negative.add(book);
            }
        }
        positive.sort(Comparator.reverseOrder());
        negative.sort(Comparator.naturalOrder());
        for (int i = 0; i < positive.size(); i += M) {
            result += positive.get(i) * 2;
        }
        for (int i = 0; i < negative.size(); i += M) {
            result += Math.abs(negative.get(i)) * 2;
        }
        result -= farthest;
        System.out.println(result);
    }
}
