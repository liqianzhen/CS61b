import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void TestStudentArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> solution= new ArrayDequeSolution<Integer>();

        Integer solu=0;
        Integer stu=0;
        FailureSequence fs = new FailureSequence();

        while (solu==stu) {

            int i=StdRandom.uniform(6);

            if (i==0) {
                int a=StdRandom.uniform(10);
                solution.addFirst(a);
                student.addFirst(a);
                solu=solution.getFirst();
                stu=student.get(0);
                DequeOperation dequeOp = new DequeOperation("addFirst", a);
                fs.addOperation(dequeOp);
            } else if (i==1) {
                stu=student.removeFirst();
                solu=solution.removeFirst();
                DequeOperation dequeOp = new DequeOperation("removeFirst");
                fs.addOperation(dequeOp);
            } else if (i==2) {
                int b=StdRandom.uniform(10);
                student.addLast(b);
                solution.addLast(b);
                solu=solution.getLast();
                stu=student.get(student.size()-1);
                DequeOperation dequeOp = new DequeOperation("addLast", b);
                fs.addOperation(dequeOp);
            } else if (i==3) {
                stu=student.removeLast();
                solu=solution.removeLast();
                assertEquals(solu,stu);
                DequeOperation dequeOp = new DequeOperation("removeLast");
                fs.addOperation(dequeOp);
            } else if (i==4) {
                solu=solution.isEmpty()?1:0;
                stu=student.isEmpty()?1:0;
                DequeOperation dequeOp = new DequeOperation("isEmpty");
                fs.addOperation(dequeOp);
            } else if (i==5) {
                solu=solution.size();
                stu=student.size();
                DequeOperation dequeOp = new DequeOperation("size");
                fs.addOperation(dequeOp);
            }
        }
        assertEquals(fs.toString(),solu,stu);
    }
}
