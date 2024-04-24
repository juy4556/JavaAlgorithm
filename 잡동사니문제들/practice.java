package 잡동사니문제들;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class practice {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        String[] arr = {"a", "b", "c"};
        String result = arrayList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        System.out.println(result);
        System.out.println(String.join("", arr));
    }
}
