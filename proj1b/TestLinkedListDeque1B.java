/**
 * Created by qianzhenli on 4/19/17.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestLinkedListDeque1B {
    @Test
    public void TestLinkedListDeque() {
        StudentLinkedListDeque<Integer> Student= new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> Solution=new LinkedListDequeSolution<Integer>();

        Integer a=0;
        Integer b=0;
        FailureSequence fs=new FailureSequence();

        for (int j=0;j<1000;j++) {
            int i=StdRandom.uniform(8);
            if (i==0) {
                int RandomAdd=StdRandom.uniform(10);
                Student.addFirst(RandomAdd);
                Solution.addFirst(RandomAdd);
                a=Solution.getFirst();
                b=Student.get(0);
                DequeOperation deque = new DequeOperation("addFirst", RandomAdd);
                fs.addOperation(deque);
            } else if (i==1) {
                a=Solution.removeFirst();
                b=Student.removeFirst();
                DequeOperation deque=new DequeOperation("removeFirst");
                fs.addOperation(deque);
            } else if (i==2) {
                int RandomAdd=StdRandom.uniform(10);
                Student.addLast(RandomAdd);
                Solution.addLast(RandomAdd);
                a=Solution.getLast();
                b=Student.get(Student.size()-1);
                DequeOperation deque=new DequeOperation("addLast",RandomAdd);
                fs.addOperation(deque);
            } else if (i==3) {
                a=Solution.removeLast();
                b=Student.removeLast();
                DequeOperation deque=new DequeOperation("removeLast");
                fs.addOperation(deque);
            } else if (i==4) {
                a=Solution.size();
                b=Student.size();
                DequeOperation deque=new DequeOperation("size");
                fs.addOperation(deque);
            } else if (i==5) {
                a=Solution.isEmpty()?1:0;
                b=Student.isEmpty()?1:0;
                DequeOperation deque=new DequeOperation("isEmpty");
                fs.addOperation(deque);
            } else if (i==6 && Student.size()>0) {
                int RandomGet=StdRandom.uniform(Student.size());
                a=Solution.get(RandomGet);
                b=Student.get(RandomGet);
                DequeOperation deque=new DequeOperation("get",RandomGet);
                fs.addOperation(deque);
            } else if (i==7 && Student.size()>0) {
                int RandomGet=StdRandom.uniform(Student.size());
                a=Solution.getRecursive(RandomGet);
                b=Student.getRecursive(RandomGet);
                DequeOperation deque=new DequeOperation("getRecursive",RandomGet);
                fs.addOperation(deque);
            }


            assertEquals(fs.toString(),a,b);

        }
    }

}
