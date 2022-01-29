package _5kyu;

/*
Description:
Implement a function that receives two IPv4 addresses, and returns the number of addresses between them (including the first one,
excluding the last one).

All inputs will be valid IPv4 addresses in the form of strings. The last address will always be greater than the first one.

Examples
ips_between("10.0.0.0", "10.0.0.50")  ==   50
ips_between("10.0.0.0", "10.0.1.0")   ==  256
ips_between("20.0.0.10", "20.0.1.0")  ==  246
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountIPAddresses {

    public static long ipsBetween(String start, String end) {
        long startNumber = transformIpToLong(start);
        long endNumber = transformIpToLong(end);
        return endNumber - startNumber;
    }

    private static long transformIpToLong(String ip) {
        List<Double> divs = Arrays.stream(ip.split("\\.")).map(Double::valueOf).collect(Collectors.toList());
        double result = 0;
        for (int i = 0; i < divs.size(); i++) {
            result = result + divs.get(i) * Math.pow(256, divs.size() - 1 - i);
        }

        return (long) result;
    }
}
