package 잡동사니문제들;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
숫자 n개의 모든 조합의 합이 주어질 때 n개의 숫자는 각각 무엇인지 출력하시오.
예를 들어 1, 2, 3의 모든 조합의 합은 1, 2, 3, 1+2, 1+3, 2+3, 1+2+3이므로
1, 2, 3을 출력하면 된다.
3
1 1 2 4 5 5 6
ans: 1 1 4
3
1 2 3 5 6 7 8
ans: 1 2 5
 */
public class 조합수 {
    static int n;
    static List<Integer> combs;
    static List<Integer> nums;
    static List<Integer> removed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());   // 숫자의 개수
        combs = new LinkedList<>();    // 조합의 합
        nums = new ArrayList<>();   // 답
        removed = new ArrayList<>();    // 제거된 숫자들
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            combs.add(num);
        }
        nums.add(combs.get(0));
        removed.add(combs.get(0));
        combs.remove(0);
        while (!combs.isEmpty()) {
            int first = combs.get(0);
            nums.add(first);
            int removedSize = removed.size();
            for (int i = 0; i < removedSize; i++) {
                removed.add(removed.get(i) + first);
                int removeIndex = combs.indexOf(removed.get(i) + first);
                combs.remove(removeIndex);
            }
            removed.add(first);
            combs.remove(0);
        }
        System.out.println(removed);
        System.out.println(nums);
    }
}
