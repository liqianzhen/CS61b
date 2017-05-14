/**
 * Created by qianzhenli on 5/13/17.
 */
public interface Deque<Blorp> {
    public void addFirst(Blorp x);
    public void addLast(Blorp x);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public Blorp removeFirst();
    public Blorp removeLast();
    public Blorp get(int index);
}
