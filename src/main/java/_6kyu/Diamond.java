/*
Description
Jamie is a programmer, and James' girlfriend. She likes diamonds, and wants a diamond string from James.
Since James doesn't know how to make this happen, he needs your help.

Task
You need to return a string that looks like a diamond shape when printed on the screen, using asterisk (*) characters.
Trailing spaces should be removed, and every line must be terminated with a newline character (\n).

Return null/nil/None/... if the input is an even number or negative, as it is not possible to print a diamond of even
or negative size.

Examples
A size 3 diamond:

 *
***
 *
...which would appear as a string of " *\n***\n *\n"
 */

package _6kyu;

public class Diamond {

    public static String print(int n) {
        if (n % 2 == 0 || n < 0) return null;
        StringBuilder start = new StringBuilder();
        start.append(star(n)).append("\n");
        StringBuilder diam_string = new StringBuilder(start);
        for (int i = n - 2; i > 0; i = i - 2) {
            StringBuilder line = new StringBuilder();
            line.append(" ".repeat(Math.max(0, (n - i) / 2)));
            line.append(star(i)).append("\n");
            diam_string.append(line);
            diam_string.insert(0, line);
        }
       return diam_string.toString();
    }

    private static String star(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*".repeat(Math.max(0, n)));
        return stringBuilder.toString();
    }

}
