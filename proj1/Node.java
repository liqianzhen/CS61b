/**
 * Created by chien on 3/27/2017.
 */
public class Node {
    public Blorp item;
    public Node next;
    public Node previous;

    public Node (Node p, Blorp x, Node n) {
        previous=p;
        item=x;
        next=n;
    }
}
