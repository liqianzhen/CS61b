/**
 * Created by qianzhenli on 5/13/17.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {

        /* build a Deque where the characters in the deque appear in the same order as in the word */

        if (word.length() == 0) {
            Deque<Character> WordDeque = new LinkedListDequeSolution<Character>();
            return WordDeque;
        } else {
            Deque<Character> WordDeque = wordToDeque(word.substring(1, word.length()));
            WordDeque.addFirst(word.charAt(0));
            return WordDeque;
        }
    }


    /* return true if the word is Palindrome */
    public static boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }


    public static boolean isPalindromeHelper(Deque<Character> WordDeque) {
        if (WordDeque.size()<2) {
            return true;
        } else if (WordDeque.removeFirst()!=WordDeque.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(WordDeque);
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }


    public static boolean isPalindromeHelper(Deque<Character> WordDeque, CharacterComparator cc) {
        if (WordDeque.size()<2) {
            return true;
        } else if (!cc.equalChars(WordDeque.removeFirst(),WordDeque.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(WordDeque,cc);
        }
    }

    public static void main(String[] args) {
        wordToDeque("H").printDeque();
        System.out.print(isPalindrome("rrr"));
        System.out.print(isPalindrome("flake",new OffByOne()));
    }
}
