/*
Description
Write a simple parser that will parse and run Deadfish.

Deadfish has 4 commands, each 1 character long:

i increments the value (initially 0)
d decrements the value
s squares the value
o outputs the value into the return array
Invalid characters should be ignored.

Deadfish.parse("iiisdoso") =- new int[] {8, 64};
 */

package _6kyu;

import java.util.ArrayList;
import java.util.List;

public class DeadFish {
    public static int[] parse(String data) {
        int result = 0;
        List<Integer> result_list = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            switch (data.charAt(i)) {
                case 'i' -> result++;
                case 'd' -> result--;
                case 's' -> result *= result;
                case 'o' -> result_list.add(result);
            }
        }
        return result_list.stream().mapToInt(i->i).toArray();
    }
}
