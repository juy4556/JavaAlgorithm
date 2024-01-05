public class 연습1 {
    public static int solution(int n, int k, int[][] grid) {
        int maxArea = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int size = maxSquareSize(n, k, grid, r, c);
                maxArea = Math.max(maxArea, size * size);
            }
        }

        return maxArea;
    }

    private static int maxSquareSize(int n, int k, int[][] grid, int r, int c) {
        int size = 1;
        int totalChanges = 0;

        while (true) {
            int[] expandResult = expandSquare(n, grid, r, c, size);
            if (!canExpand(expandResult) || totalChanges + changesNeeded(expandResult) > k) {
                break;
            }
            totalChanges += changesNeeded(expandResult);
            size++;
        }

        return size;
    }

    private static int[] expandSquare(int n, int[][] grid, int r, int c, int size) {
        if (r + size >= n || c + size >= n) {
            return new int[]{0, 0};  // Cannot expand
        }

        int changes = 0;
        int color = grid[r][c];

        for (int i = r; i <= r + size; i++) {
            if (grid[i][c + size] != color) changes++;
        }
        for (int j = c; j < c + size; j++) {
            if (grid[r + size][j] != color) changes++;
        }

        return new int[]{1, changes};  // Can expand
    }

    private static boolean canExpand(int[] expandResult) {
        return expandResult[0] == 1;
    }

    private static int changesNeeded(int[] expandResult) {
        return expandResult[1];
    }

    public static void main(String[] args) {
        int n1 = 4, k1 = 3;
        int[][] grid1 = {{1, 2, 2, 2}, {1, 2, 1, 1}, {1, 2, 2, 1}, {3, 2, 1, 1}};

        int n2 = 3, k2 = 2;
        int[][] grid2 = {{1, 1, 1}, {1, 2, 2}, {1, 2, 4}};

        int n3 = 100, k3 = 1000;
        int[][] grid3 = new int[100][100];
        int num = 1;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                grid3[i][j] = num + 1;
                num = (num + 1) % 10;
            }
        }

        System.out.println("Result 1: " + solution(n1, k1, grid1));  // Expected output: 9
        System.out.println("Result 2: " + solution(n2, k2, grid2));  // Expected output: 4
        System.out.println("Result 3: " + solution(n3, k3, grid3));  // Expected output: 4
    }
}
