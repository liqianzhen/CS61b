/**
 * Created by chien on 3/27/2017.
 */
public class LinkedListDeque<Blorp> {
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
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

    public void addback(Blorp item) {
        sentinel.previous=new Node(sentinel.previous,item,sentinel);
        sentinel.previous.previous.next=sentinel.previous;
        size +=1;
    }

    public boolean isEmpty() {
        if (sentinel==null) {
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
            return Nodeget(sentinel.next,index);
    }
}