package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

public class 쿠키의_신체_측정_20125 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heart = new int[2];
        int[] bodyLength = new int[5]; // 왼팔,오른팔,허리,왼다리,오른다리
        int length = 0;
        String result = "";
        ArrayList<String> space = new ArrayList<>(N);
        Queue q = new ArrayDeque();
        for (int i = 0; i < N; i++) {
            space.add(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            int flag = 0;
            for (int j = 0; j < N; j++) {
                if (space.get(i).charAt(j) == '*') {
                    heart[0] = i + 1;
                    heart[1] = j;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }
        for (int i = heart[1] - 1; i >= 0; i--) {
            if (space.get(heart[0]).charAt(i) == '_') {
                break;
            }
            length += 1;
        }
        bodyLength[0] = length;

        length = 0;
        for (int i = heart[1] + 1; i < N; i++) {
            if (space.get(heart[0]).charAt(i) == '_') {
                break;
            }
            length += 1;
        }
        bodyLength[1] = length;

        length = 0;
        for (int i = heart[0] + 1; i < N; i++) {
            if (space.get(i).charAt(heart[1]) == '_') {
                break;
            }
            length += 1;
        }
        bodyLength[2] = length;
        int waist_length = length;
        length = 0;
        for (int i = heart[0] + waist_length + 1; i < N; i++) {
            if (space.get(i).charAt(heart[1] - 1) == '_') {
                break;
            }
            length += 1;
        }
        bodyLength[3] = length;

        length = 0;
        for (int i = heart[0] + waist_length + 1; i < N; i++) {
            if (space.get(i).charAt(heart[1] + 1) == '_') {
                break;
            }
            length += 1;
        }
        bodyLength[4] = length;

        result = Arrays.stream(bodyLength)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        System.out.printf("%d %d\n", heart[0] + 1, heart[1] + 1);
        System.out.println(result);
        br.close();
    }
}
