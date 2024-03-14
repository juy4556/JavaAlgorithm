public class sol2 {
    public static void main(String[] args) {
        String[][] board = {{"A", "B", "T", "T", "T"}, {"T", "C", "D", "E", "T"}, {"T", "T", "T", "F", "T"}, {"B", "A", "H", "G", "F"}, {"C", "D", "E", "F", "G"}};
        String[][] board2 = {{"A", "B", "C", "D", "E"}, {"J", "I", "A", "E", "F"}, {"K", "L", "M", "N", "O"}, {"T", "S", "R", "Q", "P"}, {"U", "V", "W", "X", "Y"}};
        System.out.println(new sol2().solution(board)); // 15
        System.out.println(new sol2().solution(board2)); // 25
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;

    public int solution(String[][] board) {
        int length = 5;
        int[][] visited;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                visited = new int[length][length];
                visited[i][j] = 1;
                dfs(board, visited, i, j, true);
            }
        }
        return answer;
    }

    public static void dfs(String[][] board, int[][] visited, int x, int y, boolean chance) {
        answer = Math.max(answer, visited[x][y]);
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visited[nx][ny] > 0) {
                continue;
            }
            if (board[nx][ny].charAt(0) > board[x][y].charAt(0)) {
                visited[nx][ny] = visited[x][y] + 1;
                dfs(board, visited, nx, ny, chance);
                visited[nx][ny] = 0;
            } else {
                if (chance) {
                    visited[nx][ny] = visited[x][y] + 1;
                    dfs(board, visited, nx, ny, false);
                    visited[nx][ny] = 0;
                }
            }
        }
    }
}
