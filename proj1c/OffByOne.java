/**
 * Created by qianzhenli on 5/14/17.
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x-y)==1) {
            return true;
        } else {
            return false;
        }
    }
}
