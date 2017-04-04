/**
 * Created by qianzhenli on 3/31/17.
 */
public class ArrayDeque<blorp> {
    private blorp [] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int Rfactor=2;

    public ArrayDeque() {
        size=0;
        items=(blorp[]) new Object [8];
        nextFirst=0;
        nextLast=1;
    }

    public void addFirst(blorp x) {
        items[nextFirst]=x;
        nextFirst=MinusOne(nextFirst);
        size+=1;
    }

    private void Resize() {
        blorp [] a=(blorp[]) new Object [size*Rfactor];
        System.arraycopy(items,);
    }

    private int MinusOne (int x) {
        x-=1;
        if (x<0) {
            x=x+items.length;
        }
        return x;
    }

    public void addLast (blorp x) {
        items[nextLast]=x;
        nextLast=PlusOne(nextLast);
        size+=1;
    }

    private int PlusOne (int x) {
        x+=1;
        if (x==items.length) {
            x=x-items.length;
        }
        return x;
    }

    public boolean isEmpty() {
        if (size==0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int x=PlusOne(nextFirst);
        while (x!=nextLast) {
            System.out.print(items[x]);
            x=PlusOne(x);
        }
    }

    public blorp removeFirst() {
        if (PlusOne(nextFirst)==nextLast) {
            return null;
        } else {
            nextFirst=PlusOne(nextFirst);
            return items[nextFirst];
            items[nextFirst]=null;
        }
    }

    public blorp removeLast() {
        if (MinusOne(nextLast)==nextFirst) {
            return null;
        } else {
            nextLast=MinusOne(nextLast);
            return items[nextLast];
            items[nextLast]=null;
        }
    }

    public blorp get(int index) {
        if (index+1>size) {
            return null;
        }
        return items[HelpGet(index)];
    }

    private int HelpGet(int x) {
        if (PlusOne(nextFirst)+x>items.length-1) {
            return PlusOne(nextFirst)+x-items.length;
        }
        return PlusOne(nextFirst)+x;
    }

}
