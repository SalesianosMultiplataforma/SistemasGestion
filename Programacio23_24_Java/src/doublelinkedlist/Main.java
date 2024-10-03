package src.doublelinkedlist;

public class Main {
    public static void main(String[] args){
        DoubleLinkedList d = new DoubleLinkedList();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        d.add(n1);
        d.add(n2);
        d.add(n3);
        d.show();
        d.delete(0);
        System.out.println(("Despues"));
        d.show();
    }
}
