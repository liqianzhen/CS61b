import java.util.Stack;

/**
 * Created by qianzhenli on 5/16/17.
 */
public class SQueue {
    // add any instance variables you like
    private Stack A;

    public SQueue(){
// add any code to the constructor you like
        A=new Stack();
    }

    public void enqueue(int item){
        A.push(item);
    }

    private static Stack QueueHelper(Stack A) {
        if (A.isEmpty()) {
            return new Stack();
        } else {
            int x=(int) A.pop();
            Stack B=QueueHelper(A);
            B.push(x);
            return B;
        }
    }

    public int dequeue(){
        return (int) QueueHelper(A).pop();
    }
}
