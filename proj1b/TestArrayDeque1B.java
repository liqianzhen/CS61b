/**
 * Created by chien on 4/14/2017.
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDeque1B {
    @Test
    public void TestStudentArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> solution= new ArrayDequeSolution<Integer>();

        int i=StdRandom.uniform(3);
        int MethodCounter=0;

        if (i==0) {
            solution.addFirst(8);
            student.addFirst(8);
            assertEquals(solution.get(0),student.get(0));
            MethodCounter++;
        } else if (i==1 && MethodCounter>0) {
             student.removeFirst();
             solution.removeFirst();
             MethodCounter--;
        } else if (i==2) {
             student.addLast(3);
             solution.addLast(3);
             ass
             MethodCounter++;
        } else if (i==3 && MethodCounter>0) {
             student.removeLast();
             solution.removeLast();
             MethodCounter--;
        }

    }
}
