package _6kyu;

/*
Description
The drawing below gives an idea of how to cut a given "true" rectangle into squares ("true" rectangle meaning that the
two dimensions are different).

Can you translate this drawing into an algorithm?

You will be given two dimensions

a positive integer length
a positive integer width
You will return a collection or a string (depending on the language; Shell bash, PowerShell, Pascal and Fortran return
a string) with the size of each of the squares.

Examples in general form:
(depending on the language)

  sqInRect(5, 3) should return [3, 2, 1, 1]
  sqInRect(3, 5) should return [3, 2, 1, 1]
 */

import java.util.ArrayList;
import java.util.List;

public class SqInRect {
    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) return null;
        List<Integer> result_list = new ArrayList<>();
        while (lng > 0 && wdth > 0) {
            int min_dim = Math.min(lng, wdth);
            result_list.add(min_dim);
            if (lng == min_dim) {
                wdth -= min_dim;
            } else {
                lng -= min_dim;
            }
        }
        return result_list;
    }
}
