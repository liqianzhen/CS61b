package editor;


import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

/**
 * Created by qianzhenli on 5/31/17.
 */
public class FastLinkedList {
    private Node sentinel;
    private int currentPos; //save the position of currentNode, like which line and column
    private Node currentNode;
    private int size;

    private Node [] leadingNode;
    private int [] leadingNodePos;
    private int leadingNodesize;

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
        currentPos = 0;
        size=0;
    }

    public void addChar(char x) {
        if (x == '\n') {
            x = '\r';
        }
        currentNode.next=new Node(currentNode, new Text("" + x), currentNode.next);
        currentNode.next.next.previous=currentNode.next;
        currentNode=currentNode.next;
        currentPos +=1;
        size+=1;
    }

    public void deleteChar() {
        if (currentNode!=sentinel) {
            currentNode.previous.next=currentNode.next;
            currentNode.next.previous=currentNode.previous;
            currentNode=currentNode.previous;
            currentPos-=1;
            size-=1;
        }
    }

    public Text getText() {
        return currentNode.nodeText;
    }

    /* leadingNode is an array that can record the first node of everyline,
    and setCurrentNode method can set currentNode according to the the row and column number
     row and column number should be got from other program*/

    public void arrowKeyHandler(KeyCode x) {
        if (x == KeyCode.UP) {
            Node temp = currentNode;
            while (currentNode != sentinel && (currentNode.nodeText.getY() >= temp.nodeText.getY() || currentNode.nodeText.getX() > temp.nodeText.getX()) ){
                currentNode=currentNode.previous;
            }
            if (currentNode == sentinel) {
                currentNode = temp;
            }
        } else if (x == KeyCode.DOWN) {
            Node temp = currentNode;
            while (currentNode != sentinel && (currentNode.nodeText.getY() <= temp.nodeText.getY() || currentNode.nodeText.getX() < temp.nodeText.getX()) ){
                currentNode=currentNode.next;
            }
            if (currentNode == sentinel) {
                currentNode = sentinel.previous;
            }
        } else if (x == KeyCode.RIGHT) {
            currentNode = NodeGet(currentNode, 1);
        } else if (x == KeyCode.LEFT) {
            currentNode = NodeGet(currentNode, -1);
        }
    }

    public void setCurrentNode(int row, int column) {
        currentNode = NodeGet(leadingNode[row], column-1);
        currentPos = leadingNodePos[row]+column-1;
    }

    /* Nodeget is a helper method that can help x next of Node a */

    private static Node NodeGet (Node a, int x) {
        if (x > 0 && a.next.nodeText != null) {
            return NodeGet(a.next,x-1);
        } else if (x < 0 && a.nodeText != null) {
            return NodeGet(a.previous, x+1);
        }   return a;
    }

    public void coordinateUpdate(int STARTING_TEXT_POSITION_X, int STARTING_TEXT_POSITION_y, int windowWidth) {
        //initiate and assign a new leadingNode array everytime updating the coordinates
        leadingNode = new Node [10];
        leadingNodesize = 0;

        Node p= sentinel.next;
        int x= STARTING_TEXT_POSITION_X;
        int y= STARTING_TEXT_POSITION_y;

        while (p != sentinel) {

            if (x + p.nodeText.getLayoutBounds().getWidth() > windowWidth && wordwrap(p, STARTING_TEXT_POSITION_X)==sentinel) {
               // try to make sure the white space not wrap

            } else if (p.nodeText.getText().equals("\r") ) {
                x = STARTING_TEXT_POSITION_X;
                y += p.nodeText.getLayoutBounds().getHeight();

                //add the first node of each new line to the leading nodes
                addLeadingNode(p);
            } else if (x + p.nodeText.getLayoutBounds().getWidth() > windowWidth) {
                p=wordwrap(p, STARTING_TEXT_POSITION_X);

                //start a new line

                x=STARTING_TEXT_POSITION_X;
                y+=p.nodeText.getLayoutBounds().getHeight();

                //add the first node of each new line to the leading nodes
                addLeadingNode(p);

            }
            p.nodeText.setX(x);
            p.nodeText.setY(y);
            x+=p.nodeText.getLayoutBounds().getWidth();
            p=p.next;
        }
    }

    //refer the current node to the place, where need wrap
    private Node wordwrap(Node current, int STARTING_TEXT_POSITION_X) {
        Node temp=current;

        //find the first character that need wrapped after spaces

        if (Character.isWhitespace(current.nodeText.getText().charAt(0))) {
            while (current!=sentinel && Character.isWhitespace(current.nodeText.getText().charAt(0))) {
                current=current.next;
            }
            return current;
        } else {

            //find the whole word needs to be wrapped, find the fir

            while (current!=sentinel && !Character.isWhitespace(current.nodeText.getText().charAt(0))) {
                current=current.previous;
            }
            current=current.next;

            //try to deal with a special case that the word exceeds the whole line, then the word will be broke inside

            if (current.nodeText.getX()==STARTING_TEXT_POSITION_X) {
                current=temp;
            }
            return current;
        }
    }

    /*
    if increase a new line, add a node at the end of leadingNode array
     */
    private void addLeadingNode(Node firstNode) {
        if (leadingNodesize == leadingNode.length) {
            Resize(leadingNode.length);
        }
        leadingNode[leadingNodesize] = firstNode;
        leadingNodesize += 1;
    }

    private void Resize(int capacity) {
        Node [] a= new Node [2*capacity];
        System.arraycopy(leadingNode, 0, a, 0, capacity);
        leadingNode = a;
    }

    public void print() {
        Node p= sentinel;
        while (p.next!=sentinel) {
            p=p.next;
            System.out.print(p.nodeText.getText());
        }
    }


}
