package src.practica1ejercicio1;

/*
 * Esta clase contiene los métodos para la creación
 * de un nodo para una DobleLinkedList.
 * @author Guillermo Bernal y Miguel Reyna
 */
public class DoubleNode<T> {
    private T content;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    public DoubleNode() {}
    public DoubleNode(T content){
        this.content = content;
    }


    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
    @Override
    public String toString(){
        return "Content = " + this.content;
    }
}
