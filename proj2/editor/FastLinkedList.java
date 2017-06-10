package editor;


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
    private int leadingNodesize=0;

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
        currentNode.next=new Node(currentNode, new Text(""+x), currentNode.next);
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

    public void setCurrentNode(int row, int column) {
        currentNode=NodeGet(leadingNode[row], column-1);
        currentPos=leadingNodePos[row]+column-1;
    }

    /* Nodeget is a helper method that can help x next of Node a */

    private static Node NodeGet (Node a, int x) {
        if (x==0) {
            return a;
        } else {
            return NodeGet(a.next,x-1);
        }
    }

    public void coordinateUpdate(int STARTING_TEXT_POSITION_X, int STARTING_TEXT_POSITION_y, int windowWidth) {
        Node p= sentinel.next;
        int x= STARTING_TEXT_POSITION_X;
        int y= STARTING_TEXT_POSITION_y;

        while (p != sentinel) {

            if (x + p.nodeText.getLayoutBounds().getWidth() > windowWidth && wordwrap(p, STARTING_TEXT_POSITION_X)==sentinel) {
               // try to make sure the white space not wrap

            } else if (x + p.nodeText.getLayoutBounds().getWidth() > windowWidth) {
                p=wordwrap(p, STARTING_TEXT_POSITION_X);

                //start a new line

                x=STARTING_TEXT_POSITION_X;
                y+=p.nodeText.getLayoutBounds().getHeight();

                //add the first node of each new line to the leading nodes
                

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
    public void addLeadingNode() {
        leadingNode[leadingNodesize]=sentinel.previous;
        leadingNodesize+=1;
    }

     /*
    if deduct a line, deduct a node at the end of leadingNode array
     */

    public void deleteLeadingNode() {
        leadingNode[leadingNodesize-1]=null;
        leadingNodesize-=1;
    }

    /*
    move the leadingNode of everyline below x line one more next node
     */
    public void PushLeadingNode(int x) {
        for (int i=x+1; i<leadingNodesize; i++) {
            leadingNode[i]=leadingNode[i].next;
        }
    }

    /*
    move the leadingNode of everyline below x line one more back node
     */

    public void PullLeadingNode(int x) {
        for (int i=x+1; i<leadingNodesize; i++) {
            leadingNode[i]=leadingNode[i].previous;
        }
    }

    public void print() {
        Node p= sentinel;
        while (p.next!=sentinel) {
            p=p.next;
            System.out.print(p.nodeText.getText());
        }
    }

    /*private void recordLeadingNode(int x) {
        //assume that a single line can only contain 10 character
        int TotalLineNumber=size/10;
        int remainder=size%10;
        if (remainder==1) {
            leadingNode[TotalLineNumber]=sentinel.previous;
        } else {
            HelpLeadingNode(x);
        }
    }

    private void HelpLeadingNode(int x) {
        for (int i=currentPos/10+1; i< size/10+1; i++) {
            if (x>0) {
                leadingNode[i]=leadingNode[i].next;
            } else {
                leadingNode[i]=leadingNode[i].previous;
            }
        }
    }

    private Node [] Resize(Node [] x, int capacity) {
        Node [] a= new Node [2*capacity];
        System.arraycopy(x, 0, a, 0, capacity);
        return a;
    }

    private void downsize(double Ufactor) {
        if (size>=16 && Ufactor<0.25) {
            Resize(items.length/2);
        }
    }*/

    /*
        public static void main(String[] args) {
        FastLinkedList test=new FastLinkedList();
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('1');
        test.addChar('2');
        test.deleteChar();
        test.deleteChar();
        test.addChar('3');
        test.addChar('4');
        //test.deleteChar();





        System.out.print(test.leadingNode[1].nodeText.getText());
        System.out.print(test.sentinel.previous.nodeText.getText());
        System.out.print(test.currentNode.nodeText.getText());
          }
     */



}
