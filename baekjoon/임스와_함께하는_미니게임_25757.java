package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 임스와_함께하는_미니게임_25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        char game = st.nextToken().charAt(0);
        Set nameSet = new HashSet<String>();
        int result = 0;

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            nameSet.add(name);
        }
        int personCount = nameSet.size();
        if (game == 'Y') {
            result = personCount;
        } else if (game == 'F') {
            result = personCount / 2;
        } else if (game == 'O') {
            result = personCount / 3;
        }

        System.out.println(result);
    }
}
