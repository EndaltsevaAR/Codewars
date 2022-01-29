package _5kyu;

/*
Description:
In this kata, you will write a function that returns the positions and the values of the "peaks" (or local maxima) of a numeric array.

For example, the array arr = [0, 1, 2, 5, 1, 0] has a peak at position 3 with a value of 5 (since arr[3] equals 5).

The output will be returned as a ``Map<String,List>with two key-value pairs:"pos"and"peaks". If there is no peak in the given array,
simply return {"pos" => [], "peaks" => []}`.

Example: pickPeaks([3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3]) should return {pos: [3, 7], peaks: [6, 3]} (or equivalent in other languages)

All input arrays will be valid integer arrays (although it could still be empty), so you won't need to validate the input.

The first and last elements of the array will not be considered as peaks (in the context of a mathematical function, we don't know what
is after and before and therefore, we don't know if it is a peak or not).

Also, beware of plateaus !!! [1, 2, 2, 2, 1] has a peak while [1, 2, 2, 2, 3] and [1, 2, 2, 2, 2] do not. In case of a plateau-peak,
please only return the position and value of the beginning of the plateau. For example: pickPeaks([1, 2, 2, 2, 1])
returns {pos: [1], peaks: [2]} (or equivalent in other languages)

Have fun!

ALGORITHMSARRAYSMAP/REDUCE
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickPeaks {

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Map<String, List<Integer>> results = new HashMap<>();
        results.put("pos", new ArrayList<>());
        results.put("peaks", new ArrayList<>());
        for (int i = 1; i < arr.length - 1; i++) {
            if ( (arr[i] > arr[i-1] && arr[i] > arr[i+1]) || isStartOfPlato(arr, i)) {
                results.get("pos").add(i);
                results.get("peaks").add(arr[i]);
            }
        }
        return results;
    }

    private static boolean isStartOfPlato(int[] arr, int i) {
        if (arr[i - 1] >= arr[i]) {
            return false;
        } else {
            do {
                if (i + 1 == arr.length) {
                    return false;
                }
                if (arr[i + 1] == arr[i]) {
                    i++;
                } else return arr[i + 1] <= arr[i];
            } while (i < arr.length && arr[i - 1] == arr[i]);
        }
        return false;
    }
}


