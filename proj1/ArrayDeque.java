/**
 * Created by qianzhenli on 3/31/17.
 */
public class ArrayDeque<blorp> {
    public blorp [] items;
    public int size;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque() {
        size=0;
        items=(blorp[]) new Object [8];
        nextFirst=0;
        nextLast=0;
    }

    public void addFirst(blorp x) {
        items[nextFirst+size]=x;
        nextLast=
    }



}
