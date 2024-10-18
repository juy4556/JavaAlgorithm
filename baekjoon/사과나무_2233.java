package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 사과나무_2233 {
    static int N;
    static int appleNum = 0;

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String binaryTree = br.readLine();
        String[] rottenApple = br.readLine().split(" ");
        int X = Integer.parseInt(rottenApple[0]);
        int Y = Integer.parseInt(rottenApple[1]);
        int xNum = -1;
        int yNum = -1;

        int parentNum = 0;
        int[] parents = new int[N + 1];
        int[] depth = new int[N + 1];

        makeTree()
        for (int i = 0; i < binaryTree.length(); i++) {
            if (binaryTree.charAt(i) == '0') {
                appleNum++;
                parents[appleNum] = parentNum;
                depth[appleNum] = depth[depth[parentNum]] + 1;
                parentNum++;
            } else if (binaryTree.charAt(i) == '1') {
                parentNum--;
            }

            if (i == X - 1) {
                xNum = appleNum;
            } else if (i == Y - 1) {
                yNum = appleNum;
            }
        }

        int lca = findLCA(xNum, yNum, depth, parents);

        appleNum = 0;
        parentNum = 0;
        for (int i = 0; i < binaryTree.length(); i++) {
            if (binaryTree.charAt(i) == '0') {
                parentNum++;
                if (parentNum == lca) {
                    result.append(i + 1);
                    result.append(" ");
                }
            } else if (binaryTree.charAt(i) == '1') {
                parentNum--;
                if (parentNum == lca - 1) {
                    result.append(i + 1);
                }
            }

            if (result.length() == 3) {
                break;
            }
        }
        System.out.println("parents: " + Arrays.toString(parents));
        System.out.println("depth: " + Arrays.toString(depth));
        System.out.println(result);
    }

    private static void makeTree(int[] parents, int[] depth, int idx) {


    }

    private static int findLCA(int a, int b, int[] depth, int[] parents) {
        while (parents[a] != parents[b]) {
            if (depth[a] > depth[b]) {
                a = parents[a];
            } else if (depth[a] < depth[b]) {
                b = parents[b];
            }
        }
        if (a == b) {
            return a;
        } else {
            return parents[a];
        }
    }
}
