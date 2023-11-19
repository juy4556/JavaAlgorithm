import java.util.*;

public class Solution {

    public static int solution(String s) {
        int count = 0;

        char[] opens = {'(', '[', '{'};
        char[] closes = {')', ']', '}'};
        char[] small = {'(', ')'};
        char[] mid = {'{', '}'};
        char[] large = {'[', ']'};
        Map<Character, Character> openCloseMapping = new HashMap<>();
        initOpenCloseMapping(openCloseMapping);


        int length = s.length();
        char lack = findLack(s);
        char opposite = openCloseMapping.get(lack);
        char[] lackType = {lack, opposite};
        char[] otherType = {};
        char[] anotherType = {};
        Stack<Character> lackStack = new Stack<>();
        Stack<Character> otherStack = new Stack<>();
        Stack<Character> anotherStack = new Stack<>();

        if (Arrays.equals(lackType, small)) {
            otherType = mid;
            anotherType = large;
        } else if (Arrays.equals(lackType, mid)) {
            otherType = small;
            anotherType = large;
        } else {
            otherType = small;
            anotherType = mid;
        }

        Stack<Character> stack = new Stack<>();
        int rightCount = 0;
        List<int[]> right = new ArrayList<>();
        int lastIndex = 0;

        if (Arrays.binarySearch(opens, lack) >= 0) {
            for (int i = 0; i < length; i++) {
                char current = s.charAt(i);

                if (new String(opens).indexOf(current) >= 0) {
                    if (Arrays.binarySearch(lackType, current) >= 0) {
                        stack.push('0');
                        lackStack.push((char) i);
                    } else if (Arrays.binarySearch(otherType, current) >= 0) {
                        stack.push('1');
                        otherStack.push((char) i);
                    } else {
                        stack.push('2');
                        anotherStack.push((char) i);
                    }
                } else {
                    if (Arrays.binarySearch(lackType, current) >= 0) {
                        if (!stack.isEmpty() && stack.peek() != '0') {
                            lastIndex = i;
                            break;
                        }

                        if (!lackStack.isEmpty()) {
                            lackStack.pop();
                        } else {
                            lastIndex = i;
                            break;
                        }
                    } else if (Arrays.binarySearch(otherType, current) >= 0) {
                        if (!stack.isEmpty() && stack.peek() == '1') {
                            stack.pop();
                        }
                        if (!otherStack.isEmpty()) {
                            int a = otherStack.pop();
                            int b = i;
                            right.add(new int[]{a, b});
                        }
                    } else {
                        if (!stack.isEmpty() && stack.peek() == '2') {
                            stack.pop();
                        }
                        if (!anotherStack.isEmpty()) {
                            int a = anotherStack.pop();
                            int b = i;
                            right.add(new int[]{a, b});
                        }
                    }
                }
            }
            int other = 0;
            int another = 0;
            if (!otherStack.isEmpty()) {
                other = otherStack.peek();
            }
            if (!anotherStack.isEmpty()) {
                another = anotherStack.peek();
            }
            int startIndex = Math.max(other, another);
            System.out.println("start, last: " + startIndex + ", " + lastIndex);
            while (startIndex <= lastIndex) {
                for (int i = 0; i < right.size(); i++) {
                    if (startIndex == right.get(i)[0]) {
                        startIndex = right.get(i)[1] + 1;
                        continue;
                    }
                }
                if (Arrays.binarySearch(lackType, s.charAt(startIndex)) >= 0) {
                    count++;
                }
                startIndex++;
            }
            count += right.size();

        } else {
            for (int i = length - 1; i >= 0; i--) {
                char current = s.charAt(i);

                if (Arrays.binarySearch(closes, current) >= 0) {
                    if (Arrays.binarySearch(lackType, current) >= 0) {
                        stack.push('0');
                        lackStack.push((char) i);
                    } else if (Arrays.binarySearch(otherType, current) >= 0) {
                        stack.push('1');
                        otherStack.push((char) i);
                    } else {
                        stack.push('2');
                        anotherStack.push((char) i);
                    }
                } else {
                    if (Arrays.binarySearch(lackType, current) >= 0) {
                        if (!stack.isEmpty() && stack.peek() != '0') {
                            lastIndex = i;
                            break;
                        }

                        if (!lackStack.isEmpty()) {
                            lackStack.pop();
                        } else {
                            lastIndex = i;
                            break;
                        }
                    } else if (Arrays.binarySearch(otherType, current) >= 0) {
                        if (!stack.isEmpty() && stack.peek() == '1') {
                            stack.pop();
                        }
                        if (!otherStack.isEmpty()) {
                            int a = otherStack.pop();
                            int b = i;
                            right.add(new int[]{b, a});
                        }
                    } else {
                        if (!stack.isEmpty() && stack.peek() == '2') {
                            stack.pop();
                        }
                        if (!anotherStack.isEmpty()) {
                            int a = anotherStack.pop();
                            int b = i;
                            right.add(new int[]{b, a});
                        }
                    }
                }
            }
            int other = length;
            int another = length;
            if (!otherStack.isEmpty()) {
                other = otherStack.peek();
            }
            if (!anotherStack.isEmpty()) {
                another = anotherStack.peek();
            }
            int startIndex = Math.min(other, another) - 1;
            System.out.println("start, last: " + startIndex + ", " + lastIndex);
            right.sort((a, b) -> Integer.compare(a[0], b[0]));
            while (lastIndex <= startIndex) {
                int flag = 0;
                for (int i = 0; i < right.size(); i++) {
                    if (lastIndex == right.get(i)[0]) {
                        lastIndex = right.get(i)[1] + 1;
                        rightCount++;
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) continue;
                if (Arrays.binarySearch(lackType, s.charAt(lastIndex)) >= 0) {
                    count++;
                }
                lastIndex++;
            }
            System.out.println(count + ", " + rightCount);
            count += rightCount;
        }

        System.out.println(lack);
        System.out.println(stack);
        System.out.println("last_idx: " + lastIndex);
        System.out.println("right: " + right);
        System.out.println("lack_stack: " + lackStack);
        System.out.println("other: " + otherStack);
        System.out.println("another: " + anotherStack);

        return count;
    }

    private static void initOpenCloseMapping(Map<Character, Character> openCloseMapping) {
        openCloseMapping.put('(', ')');
        openCloseMapping.put('[', ']');
        openCloseMapping.put('{', '}');
        openCloseMapping.put(')', '(');
        openCloseMapping.put(']', '[');
        openCloseMapping.put('}', '{');
    }

    private static char findLack(String s) {
        Map<Character, Integer> bracketCount = new HashMap<>();
        bracketCount.put('(', 0);
        bracketCount.put(')', 0);
        bracketCount.put('[', 0);
        bracketCount.put(']', 0);
        bracketCount.put('{', 0);
        bracketCount.put('}', 0);

        for (char c : s.toCharArray()) {
            bracketCount.put(c, bracketCount.get(c) + 1);
        }

        if (bracketCount.get('(') < bracketCount.get(')')) {
            return '(';
        } else if (bracketCount.get('(') > bracketCount.get(')')) {
            return ')';
        } else if (bracketCount.get('[') < bracketCount.get(']')) {
            return '[';
        } else if (bracketCount.get('[') > bracketCount.get(']')) {
            return ']';
        } else if (bracketCount.get('{') < bracketCount.get('}')) {
            return '{';
        } else if (bracketCount.get('{') > bracketCount.get('}')) {
            return '}';
        } else {
            throw new IllegalArgumentException("Invalid bracket string");
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("[]([[]){}"));  // 3, ]
        System.out.println(solution("{([()]))}"));  // 4, (
        System.out.println(solution("(()()()"));  // 7, )
        System.out.println(solution("{(([)]))}"));  // 1, (
        System.out.println(solution("{[[{}()[]]}"));  // 7, ]
        System.out.println(solution("["));
    }
}
