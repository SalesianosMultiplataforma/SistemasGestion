package src.doublelinkedlist;
/*
 * Esta clase contiene los métodos para la creación
 * de un nodo para una DobleLinkedList.
 * @author Guillermo Bernal Lou
 */
public class Node {
    private int content;
    private Node next;
    private Node previous;

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node () {}
    public Node(int content){
        this.content = content;
    }


    public void setNext(Node next) {
        this.next = next;
    }
    @Override
    public String toString(){
        return "Content = " + this.content;
    }
}
