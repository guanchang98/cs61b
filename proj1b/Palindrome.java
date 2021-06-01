public class Palindrome {
    /** Change word to deque in same order*/
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            deque.addLast(cur);
        }
        return deque;
    }

    /** Check if the word is palindrome*/
    public boolean isPalindrome(String word) {
        int l = word.length();
        for (int i = 0; i < l / 2; i++) {
            if (word.charAt(i) != word.charAt(l - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
