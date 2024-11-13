package programmers;


import java.util.LinkedList;
import java.util.List;

public class 표병합 {

    static int N = 50;
    static String[] space = new String[N * N];
    static int[] parent = new int[N * N];

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"}));
        System.out.println(solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"}));
        System.out.println(solution(new String[]{"UPDATE 1 1 A", "UPDATE 2 2 B", "UPDATE 3 3 C", "UPDATE 4 4 D", "MERGE 1 1 2 2", "MERGE 3 3 4 4", "MERGE 1 1 4 4", "UNMERGE 3 3", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3", "PRINT 4 4"}));
    }

    public static List<String> solution(String[] commands) {
        List<String> answer = new LinkedList<>();

        for (int i = 0; i < N * N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");
            String cmd = command[0];
            switch (cmd) {
                case "UPDATE" -> {
                    if (command.length == 3) {
                        String a = command[1];
                        String b = command[2];

                        for (int j = 0; j < N * N; j++) {
                            if (a.equals(space[j])) {
                                int pj = findParent(j);
                                for (int k = 0; k < N * N; k++) {
                                    if (findParent(k) == pj) {
                                        space[k] = b;
                                    }
                                }
                            }
                        }
                    } else if (command.length == 4) {
                        int r = Integer.parseInt(command[1]) - 1;
                        int c = Integer.parseInt(command[2]) - 1;
                        int pn = findParent(r * N + c);
                        for (int j = 0; j < N * N; j++) {
                            if (pn == findParent(j)) {
                                space[j] = command[3];
                            }
                        }
                    }
                }
                case "MERGE" -> {
                    int r1 = Integer.parseInt(command[1]) - 1;
                    int c1 = Integer.parseInt(command[2]) - 1;
                    int r2 = Integer.parseInt(command[3]) - 1;
                    int c2 = Integer.parseInt(command[4]) - 1;

                    if (r1 == r2 && c1 == c2) {
                        continue;
                    }
                    mergeParent(r1, c1, r2, c2);
                }
                case "UNMERGE" -> {
                    int r = Integer.parseInt(command[1]) - 1;
                    int c = Integer.parseInt(command[2]) - 1;
                    unmergeParent(r, c);
                }
                case "PRINT" -> {
                    int r = Integer.parseInt(command[1]) - 1;
                    int c = Integer.parseInt(command[2]) - 1;
                    print(r, c, answer);
                }
            }
        }

        return answer;
    }

    private static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    private static void mergeParent(int r1, int c1, int r2, int c2) {
        int a = r1 * N + c1;
        int b = r2 * N + c2;
        a = findParent(a);
        b = findParent(b);

        for (int i = 0; i < N * N; i++) {
            if (parent[i] == b) {
                parent[i] = a;
            }
        }

        if (space[a] != null) {
            for (int i = 0; i < N * N; i++) {
                if (parent[i] == a) {
                    space[i] = space[a];
                }
            }
        } else {
            if (space[b] != null) {
                for (int i = 0; i < N * N; i++) {
                    if (parent[i] == a) {
                        space[i] = space[b];
                    }
                }
            }
        }
    }

    private static void unmergeParent(int r, int c) {
        int a = r * N + c;
        int pa = findParent(a);
        String value = space[a];
        for (int i = 0; i < N * N; i++) {
            if (parent[i] == pa) {
                parent[i] = i;
                space[i] = null;
            }
        }
        space[a] = value;
    }

    private static void print(int r, int c, List<String> answer) {
        int a = r * N + c;
        a = findParent(a);
        if (space[a] == null) {
            answer.add("EMPTY");
            return;
        }
        answer.add(space[a]);
    }
}
