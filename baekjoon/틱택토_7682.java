package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 틱택토_7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;
            int xCount = 0;
            int oCount = 0;
            boolean[] xBingo = {false, false, false};
            boolean[] yBingo = {false, false, false};
            boolean[] xyBingo = {false, false};
            boolean isValid = true;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == 'X') {
                    xCount++;
                } else if (c == 'O') {
                    oCount++;
                }
            }
            if (xCount != oCount && xCount != oCount + 1) {
                System.out.println("invalid");
                continue;
            }
            checkXBingo(str, xBingo);
            checkYBingo(str, yBingo);
            checkXYBingo(str, xyBingo);
            int xBingoCount = 0;
            int yBingoCount = 0;
            int xyBingoCount = 0;
            if (xyBingo[0]) {
                xyBingoCount++;
                char ox = str.charAt(4);
                if (ox == 'O' && xCount != oCount) {
                    isValid = false;
                } else if (ox == 'X' && xCount != oCount + 1) {
                    isValid = false;
                }
            }
            if (xyBingo[1]) {
                xyBingoCount++;
                char ox = str.charAt(4);
                if (ox == 'O' && xCount != oCount) {
                    isValid = false;
                } else if (ox == 'X' && xCount != oCount + 1) {
                    isValid = false;
                }
            }
            for (int j = 0; j < 3; j++) {
                if (xBingo[j]) {
                    xBingoCount++;
                    char ox = str.charAt(j * 3);
                    if (ox == 'O' && xCount != oCount) {
                        isValid = false;
                    } else if (ox == 'X' && xCount != oCount + 1) {
                        isValid = false;
                    }
                }
                if (yBingo[j]) {
                    yBingoCount++;
                    char ox = str.charAt(j);
                    if (ox == 'O' && xCount != oCount) {
                        isValid = false;
                    } else if (ox == 'X' && xCount != oCount + 1) {
                        isValid = false;
                    }
                }
            }

            if (xBingoCount > 1 || yBingoCount > 1) {
                isValid = false;
            }

            if (xBingoCount == 0 && yBingoCount == 0 && xyBingoCount == 0 && xCount + oCount < 9) {
                isValid = false;
            }
            if (isValid) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }


    private static void checkXYBingo(String str, boolean[] xyBingo) {
        char c1 = str.charAt(0);
        char c2 = str.charAt(4);
        char c3 = str.charAt(8);

        if (c1 == c2) {
            if (c1 == c3) {
                if (c1 == 'X' || c1 == 'O')
                    xyBingo[0] = true;
            }
        }
        c1 = str.charAt(2);
        c3 = str.charAt(6);
        if (c1 == c2) {
            if (c1 == c3) {
                if (c1 == 'X' || c1 == 'O')
                    xyBingo[1] = true;
            }
        }
    }

    private static void checkYBingo(String str, boolean[] yBingo) {
        for (int i = 0; i < 3; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 3);
            char c3 = str.charAt(i + 6);
            if (c1 == c2) {
                if (c1 == c3) {
                    if (c1 == 'X' || c1 == 'O')
                        yBingo[i] = true;
                }
            }
        }
    }

    private static void checkXBingo(String str, boolean[] xBingo) {
        for (int i = 0; i < 9; i += 3) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            char c3 = str.charAt(i + 2);
            if (c1 == c2) {
                if (c1 == c3) {
                    if (c1 == 'X' || c1 == 'O')
                        xBingo[i / 3] = true;
                }
            }
        }
    }
}
