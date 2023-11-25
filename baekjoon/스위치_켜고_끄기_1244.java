package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치_켜고_끄기_1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] switches = new int[n];
        int sex = 0;
        int num = 0;
        int studentCount = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            sex = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());

            changeSwitch(switches, sex, num, n);

        }
        for (int i = 0; i < n - 1; i++) {
            sb.append(switches[i]).append(' ');
        }
        for (int i = 0; i < n - 1; i++) {
            System.out.print(switches[i]);
            System.out.print(' ');
            if ((i + 1) % 20 == 0) {
                System.out.println();
            }
        }
        System.out.print(switches[n - 1]);


    }

    private static void changeSwitch(int[] switches, int sex, int num, int n) {
        if (sex == 1) {
            for (int i = num - 1; i < n; i += num) {
                if (switches[i] == 0) {
                    switches[i] = 1;
                } else if (switches[i] == 1) {
                    switches[i] = 0;
                }
            }
        } else if (sex == 2) {
            int a = num - 2;
            int b = num;
            while (a >= 0 && b < n && switches[a] == switches[b]) {
                a -= 1;
                b += 1;
            }
            for (int i = a + 1; i <= b - 1; i++) {
                if (switches[i] == 0) {
                    switches[i] = 1;
                } else if (switches[i] == 1) {
                    switches[i] = 0;
                }
            }
        }
    }
}
