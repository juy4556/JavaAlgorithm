package codetree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 점수뽑기 {
    static int N, K;
    static Map<Integer, Integer>[] classCount;
    static StringTokenizer st;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        classCount = new TreeMap[4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            classCount[i] = new TreeMap<>();
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                classCount[i].put(n, classCount[i].getOrDefault(n, 0) + 1);
            }
        }

        dfs(0, 0, 1);
        System.out.println(result);
    }

    private static void dfs(int depth, int sum, int count) {
        if (depth == 4) {
            if (sum == K) {
                result += count;
            }
            return;
        }

        for (Map.Entry<Integer, Integer> entry : classCount[depth].entrySet()) {
            int n = entry.getKey();
            int c = entry.getValue();
            if (sum + n > K) {
                return;
            }
            dfs(depth + 1, sum + n, count * c);
        }
    }
}
