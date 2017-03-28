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

    public void addFirst(Blorp item) {
        sentinel= new Node(null,null,null);
        sentinel.next=new Node(sentinel,item,sentinel);
        sentinel.previous=sentinel.next;
    }
}
