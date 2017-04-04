/**
 * Created by chien on 3/27/2017.
 */
public class LinkedListDeque<Blorp> {
    private Node sentinel;
    public int size;

    public class Node {
        public Blorp item;
        public Node next;
        public Node previous;

        public Node (Node p, Blorp x, Node n) {
            previous=p;
            item=x;
            next=n;
        }
        /* cannot use static here
         */
        private Blorp Nodeget(Node x, int y) {
            if (y==0) {
                return x.item;
            }
            return Nodeget(x.next,y-1);
        }

    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next=sentinel;
        sentinel.previous=sentinel;
        size = 0;
    }

    public LinkedListDeque(Blorp a) {
        sentinel = new Node(null, null, null);
        sentinel.next= new Node(sentinel,a,sentinel);
        sentinel.previous=sentinel.next;
        size = 1;
    }

    public void addFirst(Blorp item) {
        sentinel.next=new Node(sentinel,item,sentinel.next);
        sentinel.next.next.previous=sentinel.next;
        size +=1;
    }

    public void addLast(Blorp item) {
        sentinel.previous=new Node(sentinel.previous,item,sentinel);
        sentinel.previous.previous.next=sentinel.previous;
        size +=1;
    }

    public boolean isEmpty() {
        if (sentinel.next==sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p= sentinel;
        while (p.next!=sentinel) {
            p=p.next;
            System.out.print(p.item);
        }
    }

    public Blorp removeFirst() {
        if (sentinel==sentinel.next){
            return null;
        } else {
            Blorp n=sentinel.next.item;
            sentinel.next=sentinel.next.next;
            sentinel.next.previous=sentinel;
            size-=1;
            return n;
        }
    }

    public Blorp removeLast() {
        if (sentinel==sentinel.next) {
            return null;
        } else {
            Blorp m=sentinel.previous.item;
            sentinel.previous=sentinel.previous.previous;
            sentinel.previous.next=sentinel;
            size-=1;
            return m;
        }
    }

    public Blorp getIteration(int index) {
        if (index+1>this.size()) {
            return null;
        } else {
            Node q=sentinel.next;
            while (index!=0) {
                q=q.next;
                index-=1;
            }
            return q.item;
        }
    }

    public Blorp getRecursive(int index) {
        if (index+1>this.size()) {
            return null;
        }
            return sentinel.Nodeget(sentinel.next,index);
    }
}
