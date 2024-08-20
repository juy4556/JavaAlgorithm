package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 세로읽기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder[] newWord = new StringBuilder[15];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            newWord[i] = new StringBuilder();
        }
        for (int i = 0; i < 4; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                newWord[j].append(word.charAt(j));
            }
        }

        for (int i = 0; i < 15; i++) {
            result.append(newWord[i].toString());
        }
        System.out.println(result);
    }
}
