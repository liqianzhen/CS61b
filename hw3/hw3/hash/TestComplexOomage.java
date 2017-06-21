package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.StdRandom;


public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    public boolean haveNiceHashCodeSpread(Set<ComplexOomage> oomages) {
        /* TODO: Write a utility function that ensures that the oomages have
         * hashCodes that would distribute them fairly evenly across
         * buckets To do this, mod each's hashCode by M = 10,
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int [] counter = new int [10];
        for (ComplexOomage x : oomages) {
            int location = (x.hashCode() & 0x7FFFFFFF) % 10;
            counter[location] += 1;
        }

        boolean answer = true;

        for (int i : counter) {
            if (i > oomages.size()/2.5 || i < oomages.size()/50 ) {
                answer = false;
            }
        }


        return answer;
    }


    @Test
    public void testRandomItemsHashCodeSpread() {
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    @Test
    public void testWithDeadlyParams() {
        /* TODO: Create a Set that shows the flaw in the hashCode function.
         */
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();

        int N = 10000;
        int lastDigit = 4;

        for (int i = 0; i < N; i += 1) {
            int X = StdRandom.uniform(1, N);
            List<Integer> params = new ArrayList<Integer>(X);

            for (int j = 0; j < X; j++) {
                int Y = StdRandom.uniform(0, 256);
                params.add(Y);
            }

            params.add(lastDigit);
            ComplexOomage oomage = new ComplexOomage(params);
            oomages.add(oomage);
        }

        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
