package programmers;

import java.util.HashMap;
import java.util.Map;

public class 성격유형검사하기 {
    public static void main(String[] args) {

    }

    private String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> score = new HashMap<>();
        score.put('R', 0);
        score.put('T', 0);
        score.put('C', 0);
        score.put('F', 0);
        score.put('J', 0);
        score.put('M', 0);
        score.put('A', 0);
        score.put('N', 0);

        for (int i = 0; i < survey.length; i++) {
            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);

            switch (choices[i]) {
                case 4:
                    break;
                case 1:
                    score.put(a, score.get(a) + 3);
                    break;
                case 2:
                    score.put(a, score.get(a) + 2);
                    break;
                case 3:
                    score.put(a, score.get(a) + 1);
                    break;
                case 5:
                    score.put(b, score.get(b) + 1);
                    break;
                case 6:
                    score.put(b, score.get(b) + 2);
                    break;
                case 7:
                    score.put(b, score.get(b) + 3);
                    break;
            }
        }

        if (score.get('R') >= score.get('T')) {
            sb.append('R');
        } else {
            sb.append('T');
        }

        if (score.get('C') >= score.get('F')) {
            sb.append('C');
        } else {
            sb.append('F');
        }

        if (score.get('J') >= score.get('M')) {
            sb.append('J');
        } else {
            sb.append('M');
        }

        if (score.get('A') >= score.get('N')) {
            sb.append('A');
        } else {
            sb.append('N');
        }

        return sb.toString();
    }
}
