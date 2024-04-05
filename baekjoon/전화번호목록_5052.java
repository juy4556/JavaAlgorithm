package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 전화번호목록_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Node root = new Node(-1, null);
            boolean hasConsistency = true;
            for (int j = 0; j < n; j++) {
                String number = br.readLine();
                if (!hasConsistency) {
                    continue;
                }
                Node prev = root;
                for (int k = 0; k < number.length(); k++) {
                    int num = number.charAt(k) - '0';
                    if (prev.childs.get(num) == null) {
                        Node node = new Node(num, prev);
                        prev.childs.put(num, node);
                        prev = node;
                    } else {
                        if (prev.childs.get(num).childs.isEmpty()) {
                            hasConsistency = false;
                            break;
                        } else if (k == number.length() - 1 && !prev.childs.get(num).childs.isEmpty()) {
                            hasConsistency = false;
                            break;
                        }
                        prev = prev.childs.get(num);
                    }
                }
            }
            if (hasConsistency) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

    private static class Node {
        int num;
        Node parent;
        Map<Integer, Node> childs;

        public Node(int num, Node node) {
            this.num = num;
            this.parent = node;
            this.childs = new HashMap<>();
        }
    }
}
