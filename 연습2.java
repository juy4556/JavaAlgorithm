import java.util.HashSet;
import java.util.Set;

public class 연습2 {

    public static int solution(int[] playList, int listenTime) {
        int result;
        int start = 0;
        int end = listenTime;
        int totalPlay = 0;
        int playListLength = playList.length;
        for (int playTime : playList) {
            totalPlay += playTime;
        }

        if (totalPlay <= listenTime) {
            return playListLength;
        }

        int[] arr = new int[totalPlay];
        Set<Integer> playSet = new HashSet<>();

        int index = 0;
        for (int i = 0; i < playListLength; i++) {
            for (int m = 0; m < playList[i]; m++) {
                arr[index++] = playList[i];
            }
        }

        for (int i = start; i < end; i++) {
            playSet.add(arr[i]);
        }

        start += 1;
        result = playSet.size();

        while (start < totalPlay) {
            playSet.add(arr[end]);
            result = Math.max(result, playSet.size());
            end = (end + 1) % totalPlay;
            start += 1;

            if (start < totalPlay && arr[start - 1] != arr[start]) {
                playSet.remove(arr[start - 1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 3, 1, 4}, 3));  // 3
        System.out.println(solution(new int[]{1, 2, 3, 4}, 5));  // 4
        System.out.println(solution(new int[]{1, 2, 3, 4}, 20));  // 4
    }

}
