package editor;

import javafx.scene.text.Text;

/**
 * Created by qianzhenli on 5/31/17.
 */
public class FastLinkedList {
    private Node sentinel;
    private int currentPos;
    private Node currentNode;
    public Node [] leadingNode;
    private int size;

    private class Node {
        private Text nodeText;
        private Node next;
        private Node previous;

        private Node (Node p, Text x, Node n) {
            previous=p;
            nodeText=x;
            next=n;
        }
    }

    public FastLinkedList() {
        sentinel = new Node(null, null, null);
        sentinel.next=sentinel;
        sentinel.previous=sentinel;
        currentNode=sentinel;
        currentNode.next=currentNode;
        currentNode.previous=currentNode;
        currentPos = 0;
        size=0;
        leadingNode= new Node[100];
        leadingNode[0]=sentinel;
    }

    public void addChar(String x) {
        currentNode.next=new Node(currentNode, new Text(x), currentNode.next);
        currentNode.next.next.previous=currentNode.next;
        currentNode=currentNode.next;
        currentPos +=1;
        size+=1;
        recordLeadingNode(1);
    }

    public void deleteChar() {
        if (currentNode!=sentinel) {
            Node temp=currentNode.previous;
            currentNode=currentNode.next;
            currentNode.previous=temp;
            currentNode=currentNode.previous;
            currentPos-=1;
            size-=1;
            recordLeadingNode(-1);
        }
    }

    private void recordLeadingNode(int x) {
        //assume that a single line can only contain 10 character
        int TotalLineNumber=size/10;
        int remainder=size%10;
        if (remainder==0) {
            leadingNode[TotalLineNumber]=sentinel.previous;
        } else {
            HelpLeadingNode(x);
        }
    }

    private void HelpLeadingNode(int x) {
        for (int i=currentPos/10+1; i< size/10; i++) {
            if (x>0) {
                leadingNode[i]=leadingNode[i].next;
            } else {
                leadingNode[i]=leadingNode[i].previous;
            }
        }
    }

    public static void main(String[] args) {
        FastLinkedList test=new FastLinkedList();
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("1");
        test.addChar("2");
        test.deleteChar();
        test.addChar("1");



        System.out.print(test.leadingNode[1].nodeText.getText());

    }
}
