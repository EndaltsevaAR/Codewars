package _6kyu;

/*
Description:
You probably know the "like" system from Facebook and other pages. People can "like" blog posts, pictures or other items.
We want to create the text that should be displayed next to such an item.

Implement the function which takes an array containing the names of people that like an item. It must return the display text as
shown in the examples:

[]                                -->  "no one likes this"
["Peter"]                         -->  "Peter likes this"
["Jacob", "Alex"]                 -->  "Jacob and Alex like this"
["Max", "John", "Mark"]           -->  "Max, John and Mark like this"
["Alex", "Jacob", "Mark", "Max"]  -->  "Alex, Jacob and 2 others like this"
Note: For 4 or more names, the number in "and 2 others" simply increases.
 */


class WhoLikesIt {
    public static String whoLikesIt(String... names) {
        String[] likes = names;
        if (likes.length == 0) {
            return "no one likes this";
        } else if (likes.length == 1) {
            return likes[0] + " likes this";
        } else if (likes.length == 2) {
            return likes[0] + " and " + likes[1] + " like this";
        } else if (likes.length == 3) {
            return likes[0] + ", " + likes[1] + " and " + likes[2] + " like this";
        } else {
            return likes[0] + ", " + likes[1] + " and " + (likes.length - 2) + " others like this";
        }
    }
}
