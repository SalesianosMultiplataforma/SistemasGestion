package src.practica1ejercicio2;

public class SimpleNode<T> {
    private T content;
    private SimpleNode<T> next;
    public SimpleNode() {}
    public SimpleNode(T content){
        this.content = content;
        this.next = null; //no hace falta
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public SimpleNode<T> getNext() {
        return next;
    }

    public void setNext(SimpleNode<T> next) {
        this.next = next;
    }
    @Override
    public String toString(){
        return "Content = " + this.content;
    }
}
