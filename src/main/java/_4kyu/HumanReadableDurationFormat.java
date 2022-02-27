package _4kyu;

/*
Description:
Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly
way.

The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a
combination of years, days, hours, minutes and seconds.

It is much easier to understand with an example:

TimeFormatter.formatDuration(62)   //returns "1 minute and 2 seconds"
TimeFormatter.formatDuration(3662) //returns "1 hour, 1 minute and 2 seconds"
For the purpose of this Kata, a year is 365 days and a day is 24 hours.

Note that spaces are important.

Detailed rules
The resulting expression is made of components like 4 seconds, 1 year, etc. In general, a positive integer and one of the valid units of
time, separated by a space. The unit of time is used in plural if the integer is greater than 1.

The components are separated by a comma and a space (", "). Except the last component, which is separated by " and ", just like it would
be written in English.

A more significant units of time will occur before than a least significant one. Therefore, 1 second and 1 year is not correct, but 1 year
and 1 second is.

Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.

A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1
minute.

A unit of time must be used "as much as possible". It means that the function should not return 61 seconds, but 1 minute and 1 second
instead. Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.

 */

import java.util.ArrayList;
import java.util.List;

public class HumanReadableDurationFormat {

    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "now";
        }
        List<String> res = new ArrayList<>();
        StringBuilder resultWord = new StringBuilder();
        int years = seconds / 365 / 24 / 60 / 60;
        int days = seconds / 24 / 60 / 60 % 365;
        int hours = seconds / 60 / 60 % 24;
        int minutes = seconds / 60 % 60;
        int sec = seconds % 60;

        if (years > 0) {
            res.add(years == 1 ? "1 year" : (years + " years"));
        }
        if (days > 0) {
            res.add(days == 1 ? "1 day" : (days + " days"));
        }
        if (hours > 0) {
            res.add(hours == 1 ? "1 hour" : (hours + " hours"));
        }
        if (minutes > 0) {
            res.add(minutes == 1 ? "1 minute" : (minutes + " minutes"));
        }
        if (sec > 0) {
            res.add(sec == 1 ? "1 second" : (sec + " seconds"));
        }

        if (res.size() == 1) {
            return res.get(0);
        } else {
            for (int i = 0; i < res.size() - 1; i++) {
                resultWord.append(res.get(i)).append(", ");
            }
        }
        resultWord.delete(resultWord.lastIndexOf(","), resultWord.lastIndexOf(" "));
        return resultWord.append("and ").append(res.get(res.size() - 1)).toString();
    }
}
