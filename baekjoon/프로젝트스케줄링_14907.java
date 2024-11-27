package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 프로젝트스케줄링_14907 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, Integer> time = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        while (true) {
            String input = br.readLine();
            if (input.equals("\n")) {
                break;
            }
            String[] split = input.split(" ");
            String task = split[0];
            int t = Integer.parseInt(split[1]);
            String prev = split[2];
            time.put(task, t);

            for (int i = 0; i < prev.length(); i++) {
                String c = String.valueOf(prev.charAt(i));
                indegree.put(c, indegree.getOrDefault(c, 0) + 1);

            }
        }
    }
}
