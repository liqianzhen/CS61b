/**
 * Created by qianzhenli on 5/13/17.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {

        /* build a Deque where the characters in the deque appear in the same order as in the word */

        Deque<Character> WordDeque= new LinkedListDequeSolution<Character>();
        for (int i=0;i<word.length();i++) {
            WordDeque.addLast(word.charAt(i));
        }
        return WordDeque;
    }

    /* return true if the word is Palindrome */

    public static boolean isPalindrome(String word) {
        if (word.length()>1) {
            for (int i=0;i<word.length();i++) {
                if (word.charAt(i)!=word.charAt(word.length()-i-1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(isPalindrome("rar"));

    }
}
