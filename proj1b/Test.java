/**
 * Created by qianzhenli on 4/17/17.
 */
public class Test {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> solution= new ArrayDequeSolution<Integer>();

        student.addFirst(1);
       int a= student.removeFirst();
        student.addLast(2);
       int b= student.removeFirst();
        student.addLast(3);
        int c=student.removeFirst();
        Integer d=student.removeFirst();
        System.out.print(a);
        System.out.print(b);
        System.out.print(c);

    }
}
