package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리공장2 {
    static int N, M, Q;
    static Map<Integer, Deque<Integer>> belts;
    static Map<Integer, Integer> objectValues;
    static Map<Integer, Integer> objectBelts;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        belts = new HashMap<>();
        objectValues = new HashMap<>();
        objectBelts = new HashMap<>();

        for (int i = 1; i <= M; i++) {
            belts.put(i, new ArrayDeque<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int uniqueId = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int beltNum = Integer.parseInt(st.nextToken());
            objectValues.put(uniqueId, value);
            objectBelts.put(uniqueId, beltNum);
            Deque<Integer> belt = belts.getOrDefault(beltNum, new ArrayDeque<>());
            belt.addLast(uniqueId);
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());

            if (q == 1) {
                int value = Integer.parseInt(st.nextToken());
                deliveryObject(value); // 물건 배달(배송비 출력)
            } else if (q == 2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                moveObject(a, b);  // 벨트 a -> 벨트 b로 물건 이동
            } else if (q == 3) {
                int beltNum = Integer.parseInt(st.nextToken());
                cleanBelt(beltNum); // 벨트 청소
            } else if (q == 4) {
                int objectNum = Integer.parseInt(st.nextToken());
                removeObject(objectNum); // 물건 제거
            } else if (q == 5) {
                int beltNum = Integer.parseInt(st.nextToken());
                int order = Integer.parseInt(st.nextToken());
                getObjectNumFromBelt(beltNum, order);  // 해당 벨트에 입력 순서에 있는 번호 출력
            }
        }
    }

    private static void deliveryObject(int value) {
        int beltNum = -1;
        int diff = 20000;
        for (int i = M; i >= 1; i--) {
            if (belts.get(i).isEmpty()) {
                continue;
            }
            Integer objectNum = belts.get(i).getFirst();
            int v = objectValues.get(objectNum);
            if (Math.abs(value - v) <= diff) {
                beltNum = i;
                diff = Math.abs(value - v);
            }
        }
        if (beltNum == -1) {
            System.out.println(-1);
            return;
        }
        Integer objectNum = belts.get(beltNum).pollFirst();
        objectBelts.put(objectNum, -1);
        System.out.println(beltNum + objectValues.get(objectNum));
    }

    private static void moveObject(int a, int b) {
        Deque<Integer> beltA = belts.get(a);
        Deque<Integer> beltB = belts.get(b);
        Deque<Integer> moveObjects = new ArrayDeque<>();
        int moveAmount;

        if (beltA.isEmpty()) {
            return;
        }

        if (beltA.size() % 3 == 0) {
            moveAmount = beltA.size() / 3;
        } else {
            moveAmount = beltA.size() / 3 + 1;
        }


        for (int i = 0; i < moveAmount; i++) {
            moveObjects.add(beltA.pollFirst());
        }

        while (!moveObjects.isEmpty()) {
            int objectNum = moveObjects.pollLast();
            beltB.addFirst(objectNum);
            objectBelts.put(objectNum, b);
        }
    }

    private static void cleanBelt(int beltNum) {
        Deque<Integer> belt = belts.get(beltNum);

        if (belt.isEmpty()) {
            return;
        }

        while (!belt.isEmpty()) {
            int objectNum = belt.pollFirst();
            int value = objectValues.get(objectNum);
            int newBeltNum = value % M + 1;

            if (newBeltNum == beltNum) {
                newBeltNum = 1;
            }
            Deque<Integer> newBelt = belts.get(newBeltNum);
            newBelt.addFirst(objectNum);
            objectBelts.put(objectNum, newBeltNum);
        }
    }

    private static void removeObject(int objectNum) {
        int beltNum = objectBelts.get(objectNum);

        if (beltNum == -1) {
            return;
        }

        belts.get(beltNum).remove(objectNum);
    }

    private static void getObjectNumFromBelt(int beltNum, int order) {
        Deque<Integer> belt = belts.get(beltNum);

        if (order > belt.size()) {
            System.out.println(-1);
            return;
        }

        Stack<Integer> stack = new Stack<>();

        if (order > belt.size() / 2) {
            order = belt.size() - order + 1;

            while (order > 0) {
                stack.add(belt.pollLast());
                order--;
            }

            System.out.println(belt.pollLast());

            while (!stack.isEmpty()) {
                belt.addLast(stack.pop());
            }
        } else {
            while (order > 0) {
                stack.add(belt.pollFirst());
                order--;
            }

            System.out.println(belt.pollFirst());

            while (!stack.isEmpty()) {
                belt.addFirst(stack.pop());
            }
        }
//        List<Integer> list = new ArrayList<>(belt);
//        System.out.println(list.get(order - 1));
    }
}
