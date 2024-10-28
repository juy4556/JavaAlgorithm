package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 후위순회한결과 {
    static int N;
    static int[] inOrder;
    static int[] preOrder;
    static List<Integer> postOrder;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        preOrder = new int[N];
        postOrder = new ArrayList<>(N);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            preOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        findPostOrder(0, 0, N - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(postOrder.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static void findPostOrder(int preRootIndex, int inStart, int inEnd) {
//        System.out.println("preRoot: " + preRootIndex + ", inStart: " + inStart + ", inEnd: " + inEnd);
//        System.out.println(postOrder);
        if (inStart > inEnd) return;

        int inRootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == preOrder[preRootIndex]) {
                inRootIndex = i;
                break;
            }
        }
        if (inRootIndex == -1) return;

        int preLeftRootIndex = preRootIndex + 1;
        int preRightRootIndex = preRootIndex + inRootIndex - inStart + 1;
        // left
        findPostOrder(preLeftRootIndex, inStart, inRootIndex - 1);

        // right
        findPostOrder(preRightRootIndex, inRootIndex + 1, inEnd);

        // root
        postOrder.add(preOrder[preRootIndex]);
    }
}
