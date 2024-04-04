package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 너의평점은_25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Double> grades = new HashMap<>();
        grades.put("A+", 4.5);
        grades.put("A0", 4.0);
        grades.put("B+", 3.5);
        grades.put("B0", 3.0);
        grades.put("C+", 2.5);
        grades.put("C0", 2.0);
        grades.put("D+", 1.5);
        grades.put("D0", 1.0);
        grades.put("F", 0.0);
        double totalCredits = 0.0;
        double totalGrades = 0.0;
        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            double credit = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if (grade.equals("P")) continue;
            totalCredits += credit;
            totalGrades += grades.getOrDefault(grade, 0.0) * credit;
        }
        System.out.println(totalGrades / totalCredits);
    }
}
