package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 가장많이받은선물 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}));
        System.out.println(solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"}));
        System.out.println(solution(new String[]{"a", "b", "c"}, new String[]{"a b", "b a", "c a", "a c", "a c", "c a"}));
    }

    private static int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftInfo = new HashMap<>();
        Map<String, Integer> giftCount = new HashMap<>();
        int result = 0;

        for (String friend : friends) {
            giftInfo.put(friend, new HashMap<>());
        }

        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String sender = split[0];
            String receiver = split[1];
            giftInfo.get(sender).put(receiver, giftInfo.get(sender).getOrDefault(receiver, 0) + 1);
            giftCount.put(sender, giftCount.getOrDefault(sender, 0) + 1);
            giftCount.put(receiver, giftCount.getOrDefault(receiver, 0) - 1);
        }

        for (String a : friends) {
            int count = 0;
            for (String b : friends) {
                int aTob = giftInfo.getOrDefault(a, new HashMap<>()).getOrDefault(b, 0);
                int bToa = giftInfo.getOrDefault(b, new HashMap<>()).getOrDefault(a, 0);
                if (aTob > bToa) {
                    count += 1;
                }
                if (aTob == bToa) {
                    if (giftCount.getOrDefault(a, 0) > giftCount.getOrDefault(b, 0)) {
                        count += 1;
                    }
                }
            }
            result = Math.max(result, count);
        }
        return result;
    }
}
