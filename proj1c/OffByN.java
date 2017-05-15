/**
 * Created by qianzhenli on 5/14/17.
 */
public class OffByN implements CharacterComparator {
    public int OffNumber;
    public OffByN (int N) {
        OffNumber=N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x-y)==OffNumber) {
            return true;
        } else {
            return false;
        }
    }
}
