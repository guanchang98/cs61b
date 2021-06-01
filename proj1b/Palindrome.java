public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            deque.addFirst(cur);
        }
        return deque;
    }
}
