public class Slist {
    private class Snode {
        private int items;
        private Snode next;

        private Snode (int item, Snode next) {
            items = item;
            next = next;
        }

    }
    private Snode front;
    public int size;

    public void insertfront(int x) {
        front=new Snode(x,front);
        size+=1;
    }
/*
    public void insert(int item, int position) {

    }

}