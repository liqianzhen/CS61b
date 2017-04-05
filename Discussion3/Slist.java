public class Slist {
    private class Snode {
        private int item;
        private Snode next;

        private Snode (int item, Snode next) {
            this.item = item;
            this.next = next;
        }
    }

    private Snode front;
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
        Snode previous=GetLocation(position);
        previous=new Snode(item,previous.next);
    }

    private Snode GetLocation(int position) {
        if (position>size-1) {
            position=size-1;
        }
        Snode p=front;
        while (position!=0) {
            p=p.next;
            position-=1;
        }
        return p;
    }

    public void reverse () {
        
    }

}