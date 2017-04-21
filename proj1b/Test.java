/**
 * Created by qianzhenli on 4/17/17.
 */
public class Test {
    public static void main(String[] args) {
        StudentLinkedListDeque<Integer> student = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> solution= new LinkedListDequeSolution<Integer>();

        student.addFirst(1);
       Integer a= student.removeFirst();
       Integer b= student.removeFirst();
       Integer c=student.removeFirst();
        System.out.print(a);
        System.out.print(b);
        System.out.print(c);
        System.out.print(student.size());

    }
}
