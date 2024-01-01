package 문법정리;

import baekjoon.창고_다각형_2304;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortPractice {
    public static void main(String[] args) {
        /*
            정수형 정렬
         */
        int[] arr = new int[]{6, 2, 8, 4, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        // Comparator나 Collections를 사용한 정렬은 Wrapper class에 한해서 가능
        // 따라서 스트림으로 boxing해야함
        // for 문으로 역순을 구현하는게 .sort(Array, Collection.reverseOrder())보다 빠름
        // 1)
        int[] newArr1 = new int[arr.length];
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            newArr1[i] = arr[arr.length - i - 1];
        }
        System.out.println(Arrays.toString(newArr1));
        // 2)
        Integer[] newArr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(newArr2, Comparator.reverseOrder());
        // same~
        Arrays.sort(newArr2, Collections.reverseOrder());
        System.out.println(Arrays.toString(newArr2));
        // 3)
        Integer[] newArr3 = newArr2.clone();
        System.out.println(newArr2[0]);
        Arrays.sort(newArr3, (a, b) -> b - a);
        System.out.println(Arrays.toString(newArr3));

        /*
            문자열 정렬
         */
        String[] strArr = new String[]{"abc", "ABC", "String", "STRING", "Mango", "mango"};
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));
        // 대소문자 구분없이 정렬 -> String.CASE_INSENSITIVE_ORDER
        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strArr));
        String[] strArr1 = {"1", "5", "8", "3"};
        Arrays.sort(strArr1);
        System.out.println(Arrays.toString(strArr1));

        /*
            ArrayList 정렬
         */
        List<String> strList = new ArrayList<>(Arrays.asList("i", "am", "so", "happy"));
        Collections.sort(strList);
        System.out.println(strList);

        Collections.reverse(strList);
        System.out.println(strList);

        Collections.sort(strList, Collections.reverseOrder());
        System.out.println(strList);

        strList.sort(Comparator.naturalOrder());
        System.out.println(strList);

        strList.sort(Comparator.reverseOrder());
        System.out.println(strList);

        /*
            stream 사용
         */
        // Array -> Stream -> Array
        IntStream intstream = Arrays.stream(arr).sorted();
        int[] newArr = intstream.toArray();
        System.out.println(Arrays.toString(newArr));

        Stream<Integer> integerStream1 = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder());
        Stream<Integer> integerStream2 = Arrays.stream(arr).boxed().sorted((a, b) -> b - a);
        int[] newArr4 = integerStream1.mapToInt(i -> i).toArray();
        int[] newArr5 = integerStream2.mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(newArr4));
        System.out.println(Arrays.toString(newArr5));

        // ArrayList -> Stream
        List<String> strList1 = new ArrayList<>(Arrays.asList("i", "am", "so", "happy"));

        Stream<String> sortedStrStream1 = strList1.stream().sorted();
        List<String> newStrList1 = sortedStrStream1.collect(Collectors.toList());
        System.out.println(newStrList1);

        Stream<String> sortedStrStream2 = strList1.stream().sorted(Comparator.reverseOrder());
        List<String> newStrList2 = sortedStrStream2.toList();
        System.out.println(newStrList2);

        Stream<String> sortedStrStream3 = strList1.stream().sorted(Collections.reverseOrder());
        List<String> newStrList3 = sortedStrStream3.toList();
        System.out.println(newStrList3);

        Stream<String> sortedStrStream4 = strList1.stream().sorted((a, b) -> b.compareTo(a));
        List<String> newStrList4 = sortedStrStream4.toList();
        System.out.println(newStrList4);

        // 클래스 배열 정렬
        Line[] lines = new Line[4];
        // Comparator의 compoare 인터페이스 구현을 통한 정렬
        Arrays.sort(lines, new Comparator<>() {
            @Override
            public int compare(Line l1, Line l2) {
                return l1.getPosition() - l2.getPosition();
            }
        });
        // 람다사용하여 정렬
        Arrays.sort(lines, (l1, l2) -> l1.getPosition() - l2.getPosition());
        Arrays.sort(lines, Comparator.comparingInt(Line::getPosition));
        Comparator<Line> reversedPosition = Comparator.comparingInt(Line::getPosition).reversed();
        Arrays.sort(lines, Comparator.comparingInt(Line::getPosition).thenComparing(reversedPosition));
    }

    // 클래스 배열 정렬
    static class Line {
        private int position;
        private int height;

        public Line(int position, int height) {
            this.position = position;
            this.height = height;
        }

        public int getPosition() {
            return position;
        }

        public int getHeight() {
            return height;
        }
    }
}
