public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int N) {
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int dif = x - y;
        return dif == n || dif == -n;
    }
}
