public class Slist {
    private class Snode {
        private int item;
        private Snode next;

        private Snode (int item, Snode next) {
            this.item = item;
            this.next = next;
        }
    }

    public Snode front;
    public int size;

    public Slist() {
        front=null;
        size=0;
    }

    public void insertfront(int x) {
        front=new Snode(x,front);
        size+=1;
    }

    public void insert(int item, int position) {
        if (position==0) {
            insertfront(item);
            size+=1;
        } else if (position>size) {
            position=size-1;
        } else {
        Snode p= front;
        while (position>1) {
            p=p.next;
            position-=1;
        }
        p.next=new Snode(item,p.next);
        size+=1;}
    }

   /* public void reverseIteration () {
        int index=size;
        Snode p=GetLocation(index);
        Snode q=p;
        while (index>1) {
            index-=1;
            q=q.next;
            q=GetLocation(index);
        }
        q.next=null;
        front=p;
    }
    */

    public static void main(String[] args) {
        Slist a=new Slist();
        a.insertfront(1);
        a.insertfront(2);
        a.insertfront(3);
        a.insert(5,3);
        System.out.println(a.front.item);
        System.out.println(a.front.next.item);
        System.out.println(a.front.next.next.item);
        System.out.println(a.front.next.next.next.item);

    }
}