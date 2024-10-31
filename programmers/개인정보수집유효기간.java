package programmers;

import java.util.*;

public class 개인정보수집유효기간 {
    public Integer[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(today, ".");
        Map<String, Integer> termMap = new HashMap<>();
        int year = Integer.parseInt(st.nextToken()) - 2000;
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int now = 28 * 12 * year + 12 * month + day;

        for (String term : terms) {
            st = new StringTokenizer(term);
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            int validDays = termMap.get(st.nextToken()) * 28;
            st = new StringTokenizer(date, ".");
            year = Integer.parseInt(st.nextToken()) - 2000;
            month = Integer.parseInt(st.nextToken());
            day = Integer.parseInt(st.nextToken());
            int days = 28 * 12 * year + 12 * month + day;

            if (days + validDays <= now) {
                result.add(i + 1);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }
}
