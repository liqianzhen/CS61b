/**
 * Created by qianzhenli on 4/8/17.
 */
public class Alist {
    public static int [] insert (int [] x, int item, int position) {
        if (x==null) {
            int [] a=new int[] {item};
            return a;
        } else {
            int [] a=new int[x.length+1];
            a[position]=item;
            System.arraycopy(x,0,a,0,position);
            System.arraycopy(x,position,a,position+1,x.length-position);
            return a;
        }
    }

    public static void reverse(int[] x) {
        for (int i=0; i<x.length/2 ;i++) {
            int a=x[i];
            x[i]=x[x.length-1-i];
            x[x.length-1-i]=a;
        }
    }

    public static void main(String[] args) {
        int [] resultarray=new int[] {1,2,3,4,5};
        /*int [] resultarray=insert(testarray,9,3);*/

        System.out.print(resultarray[0]);
        System.out.print(resultarray[1]);
        System.out.print(resultarray[2]);
        System.out.print(resultarray[3]);
        System.out.print(resultarray[4]);
        reverse(resultarray);
        System.out.print(resultarray[0]);
        System.out.print(resultarray[1]);
        System.out.print(resultarray[2]);
        System.out.print(resultarray[3]);
        System.out.print(resultarray[4]);
    }
}

