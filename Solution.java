import java.util.*;

public class Solution {
    public static int solution(int n, int[] orders) {
        int answer = -1;
        Map<Integer, Integer> numbers = new HashMap<>();
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.get(0)));
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();

        // Traversing priorityQueue to initialize treeMap
        int index = 0;
        while (!priorityQueue.isEmpty()) {
            List<Integer> polled = priorityQueue.poll();
            int num = polled.get(1);
            treeMap.put(index++, List.of(num, 2));
        }

        for (int order : orders) {
            int[] node = modifyAndDeleteNode(treeMap, order);
            System.out.println(Arrays.toString(node));
        }

        return answer;
    }

    public static int[] modifyAndDeleteNode(TreeMap<Integer, List<Integer>> treeMap, int targetValue) {
        Queue<Map.Entry<Integer, List<Integer>>> queue = new LinkedList<>();
        queue.offer(treeMap.firstEntry());
        int depth = 0;
        int total = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Map.Entry<Integer, List<Integer>> entry = queue.poll();
                if (treeMap.containsKey(entry.getKey())) {
                    total += entry.getValue().get(1);
                }

                if (entry.getKey() == targetValue) {
                    System.out.println("Node found at depth: " + depth);
                    total -= entry.getValue().get(1);

                    // Modify the node
                    int newValue = entry.getValue().get(1) - 1;
                    if (newValue == 0) {
                        treeMap.remove(entry.getKey());
                        if (!treeMap.isEmpty()) {
                            int newRootKey = treeMap.firstKey();
                            treeMap.put(newRootKey, List.of(treeMap.remove(newRootKey).get(0), 2));  // Adjusted to put 2 as the second element
                            System.out.println("New root set to: " + newRootKey);
                        }
                    } else {
                        treeMap.replace(entry.getKey(), List.of(entry.getValue().get(0), newValue));  // Adjusted to replace the second element
                    }

                    return new int[]{depth, total};
                }

                Integer leftChild = entry.getKey() * 2;
                Integer rightChild = entry.getKey() * 2 + 1;

                if (treeMap.containsKey(leftChild)) {
                    queue.offer(new AbstractMap.SimpleEntry<>(leftChild, treeMap.get(leftChild)));
                }

                if (treeMap.containsKey(rightChild)) {
                    queue.offer(new AbstractMap.SimpleEntry<>(rightChild, treeMap.get(rightChild)));
                }
            }

            depth++;
        }

        System.out.println("Node not found.");
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 1, 3, 3, 2}));  // 기대 결과: 3
        System.out.println(solution(4, new int[]{3, 3, 2, 2, 1, 1, 4, 4}));  // 기대 결과: 0
    }
}
