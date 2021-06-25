import java.util.ArrayList;
import java.util.List;
public class MyTrieSet implements TrieSet61B {
    /* ASCII code number*/
    private static final int N = 256;
    private Node root;

    private static class Node {
        private Node[] next = new Node[N];
        private Boolean isEnd;
    }
    /** Clears all items out of Trie */
    public void clear() {
        root = null;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        Node x = root;
        if (x == null) {
            return false;
        }
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            x = x.next[c];
            if (x == null) {
                return false;
            }
        }
        return x.isEnd;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Null is not a legal key");
        }
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.isEnd = true;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, d + 1);
        x.isEnd = false;
        return x;
    }
    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        Node x = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            x = x.next[c];
            if (x == null) {
                return null;
            }
        }
        return collect(x, prefix);
    }

    public List<String> collect(Node x, String prefix) {
        List<String> keys = new ArrayList<>();
        StringBuilder sb = new StringBuilder(prefix);
        colHelp(x, sb, keys);
        return keys;
    }

    private void colHelp(Node x, StringBuilder sb, List<String> keys) {
        if (x.isEnd) {
            keys.add(sb.toString());
        }
        for (int c = 0; c < N; c++) {
            if (x.next[c] != null) {
                colHelp(x.next[c], sb.append((char)c), keys);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return;
    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
