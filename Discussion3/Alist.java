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

    public static int[] xify(int[] x) {
        int[] newArray= new int[ArraySum(x)];
        int SumcopyNumber=0;
        for (int i=0; i<x.length; i++) {
            for (int j=0;j<x[i];j++) {
                newArray[SumcopyNumber+j]=x[i];
            }
            SumcopyNumber+=x[i];

        }
        return newArray;
    }

    private static int ArraySum (int[] x){
        int a=0;
        for (int i=0; i<x.length; i++) {
            a+=x[i];
        }
        return a;
    }

    public static void main(String[] args) {
        int [] array=new int[] {1,2,3,4};

        int [] resultarray=xify(array);

        System.out.print(resultarray[0]);
        System.out.print(resultarray[1]);
        System.out.print(resultarray[2]);
        System.out.print(resultarray[3]);
        System.out.print(resultarray[4]);
        System.out.print(resultarray[5]);
        System.out.print(resultarray[6]);
        System.out.print(resultarray[7]);
        System.out.print(resultarray[8]);
        System.out.print(resultarray[9]);


    }
}

