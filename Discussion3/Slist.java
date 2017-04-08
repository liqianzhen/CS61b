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

    public void insertfront(int x) {
        front=new Snode(x,front);
    }

    public void insert(int item, int position) {
        if (position==0 || front==null) {
            insertfront(item);
        } else {
            Snode p=front;
            while (position>1 && p.next!=null) {
                p=p.next;
                position-=1;
            }
            p.next=new Snode(item,p.next);
        }
    }

    public void reverseIterative() {
        Snode frontofreverse=null;
        Snode NexttoAdd=front;
        while (NexttoAdd!=null) {
            Snode remainderoforigin=NexttoAdd.next;
            NexttoAdd.next=frontofreverse;
            frontofreverse=NexttoAdd;
            NexttoAdd=remainderoforigin;
        }
        front=frontofreverse;
    }




    public static void main(String[] args) {
        Slist a=new Slist();
        a.insertfront(1);
        a.insertfront(2);
        a.insertfront(3);
        a.insert(5,1);
        System.out.println(a.front.item);
        System.out.println(a.front.next.item);
        System.out.println(a.front.next.next.item);
        System.out.println(a.front.next.next.next.item);

    }
}