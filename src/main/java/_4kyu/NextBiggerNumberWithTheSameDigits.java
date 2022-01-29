package _4kyu;

/*
Description:
Create a function that takes a positive integer and returns the next bigger number that can be formed by rearranging its digits.
For example:

12 ==> 21
513 ==> 531
2017 ==> 2071
nextBigger(num: 12)   // returns 21
nextBigger(num: 513)  // returns 531
nextBigger(num: 2017) // returns 2071
If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):

9 ==> -1
111 ==> -1
531 ==> -1
nextBigger(num: 9)   // returns nil
nextBigger(num: 111) // returns nil
nextBigger(num: 531) // returns nil
 */

import java.util.*;
import java.util.stream.Collectors;

public class NextBiggerNumberWithTheSameDigits {
    public static void main(String[] args) {
        System.out.println(nextBiggerNumber(144));
    }

    public static long nextBiggerNumber(long n) {
        System.out.println(n);
        List<Integer> numbers = Arrays.stream(String.valueOf(n).split("")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(numbers);
        List<Integer> sorted = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (numbers.equals(sorted) || n <= 10) {
            return -1;
        }
        List<Integer> tail = new ArrayList<>();
        for (int i = numbers.size() - 1; i > 0; i--) {
            if (!(numbers.get(i) > numbers.get(i - 1))) {
                tail.add(numbers.get(i));
            } else {
                int temp = numbers.get(i - 1);
                if (tail.isEmpty()) {
                    numbers.set(i - 1, numbers.get(i));
                    numbers.set(i, temp);
                } else {
                    tail.add(numbers.get(i));
                    int minFromTail = minFromTail(tail, numbers.get(i - 1));
                    numbers.set(i - 1, minFromTail);
                    tail = tailWithoutMin(tail, minFromTail);
                    tail.add(temp);
                    tail.sort(Comparator.naturalOrder());
                    for (int j = i, k = 0; j < numbers.size(); j++, k++) {
                        numbers.set(j, tail.get(k));
                    }
                }
                return Long.parseLong(numbers.stream().map(String::valueOf).collect(Collectors.joining()));
            }
        }
        return -1;
    }

    private static List<Integer> tailWithoutMin(List<Integer> tail, int minFromTail) {
        int count = 1;
        List<Integer> temp = new ArrayList<>();
        for (Integer tailPart : tail) {
            if (minFromTail != tailPart || count == 0) {
                temp.add(tailPart);
            } else {
                count--;
            }
        }
        return temp;
    }

    private static int minFromTail(List<Integer> tail, Integer integer) {
        tail.sort(Comparator.naturalOrder());
        for (Integer tailsPart : tail) {
            if (tailsPart > integer) return tailsPart;
        }
        return 0;
    }
}
