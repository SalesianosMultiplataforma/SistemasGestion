package src.eda;

public class Main {
    public static void main(String[] args){
        //ArrayList<Integer> l = new ArrayList<>();
        SimpleLinkedList l = new SimpleLinkedList();
        Stach s = new Stach();
        Queue q = new Queue();
        Node n = new Node(20);
        Node n2 = new Node(-3);
        Node n3 = new Node(50);
        Node n4 = new Node(77);
        l.add(n);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        l.show();
        System.out.println("Despues");
        l.move(1,4);
        l.show();
    }
}
