public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int N) {
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (x - 'a' < 0 || x - 'a' >= 26 || y - 'a' < 0 || y - 'a' >= 26) {
            return false;
        }
        int dif = x - y;
        return dif == n || dif == -n;
    }
}
