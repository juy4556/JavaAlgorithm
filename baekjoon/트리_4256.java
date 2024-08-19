package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 트리_4256 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] preOrder = br.readLine().split(" ");
            String[] inOrder = br.readLine().split(" ");

            String result = getPostOrder(preOrder, inOrder, 0, 0, n - 1);
            System.out.println(result);
        }

    }

    private static String getPostOrder(String[] preOrder, String[] inOrder,
                                       int rootIndexInPreOrder, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return "";
        } else if (leftIndex == rightIndex) {
            return preOrder[rootIndexInPreOrder] + " ";
        }
        String root = preOrder[rootIndexInPreOrder];
        int rootIndexInInOrder = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (inOrder[i].equals(root)) {
                rootIndexInInOrder = i;
                break;
            }
        }
        String left = getPostOrder(preOrder, inOrder, rootIndexInPreOrder + 1, leftIndex, rootIndexInInOrder - 1);
        String right = getPostOrder(preOrder, inOrder, rootIndexInPreOrder + rootIndexInInOrder + 1 - leftIndex, rootIndexInInOrder + 1, rightIndex);
        return left + right + root + " ";
    }
}
