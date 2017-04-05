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
        if (size==items.length) {
            Resize(Rfactor*size);
        }
        items[nextFirst]=x;
        nextFirst=MinusOne(nextFirst);
        size+=1;
    }

    private void Resize(int capacity) {
        blorp [] a=(blorp[]) new Object [capacity];
        System.arraycopy(items,PlusOne(nextFirst),a,PlusOne(nextFirst),size);
        items=a;
    }

    private int MinusOne (int x) {
        x-=1;
        if (x<0) {
            x=x+items.length;
        }
        return x;
    }

    public void addLast (blorp x) {
        if (size==items.length) {
            Resize(Rfactor*size);
        }
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
            blorp p=items[nextFirst];
            items[nextFirst]=null;
            size-=1;
            downsize(size/items.length);
            return p;
        }
    }

    public blorp removeLast() {
        if (MinusOne(nextLast)==nextFirst) {
            return null;
        } else {
            nextLast=MinusOne(nextLast);
            blorp p=items[nextLast];
            items[nextLast]=null;
            size-=1;
            downsize(size/items.length);
            return p;
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

    private void downsize(double Ufactor) {
        if (size>=16 && Ufactor<0.25) {
            Resize(items.length/2);
        }
    }
}
