import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void TestStudentArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> solution= new ArrayDequeSolution<Integer>();

        int expected=0;
        int answer=0;
        int size=0;

        for(int j=0;j<1000;j++) {

            int i=StdRandom.uniform(5);

            if (i==0) {
                int a=StdRandom.uniform(9);
                solution.addFirst(a);
                student.addFirst(a);
                size++;
            } else if (i==1 && size>0) {
                expected=student.removeFirst();
                answer=solution.removeFirst();
                size--;
                assertEquals(expected,answer);
            } else if (i==2) {
                int b=StdRandom.uniform(9);
                student.addLast(b);
                solution.addLast(b);
                size++;
            } else if (i==3 && size>0) {
                expected=student.removeLast();
                answer=solution.removeLast();
                assertEquals(expected,answer);
                size--;
            }

        }
    }
}
