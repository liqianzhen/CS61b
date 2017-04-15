/**
 * Created by chien on 4/14/2017.
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDeque1B {
    @Test
    public void TestStudentArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        student.addLast(5);
        student.addFirst(1);
        student.removeFirst();
        student.removeLast();
        assertEquals(0,student.get(0).intValue());
    }
}
