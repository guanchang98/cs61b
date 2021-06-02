public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x - 'a' < 0 || x - 'a' >= 26 || y - 'a' < 0 || y - 'a' >= 26) {
            return false;
        }
        int dif = x - y;
        return dif == 1 || dif == -1;
    }
}
