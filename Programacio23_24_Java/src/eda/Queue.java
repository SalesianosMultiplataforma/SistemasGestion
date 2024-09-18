package src.eda;
/*
 * Esta clase contiene los métodos para la creación
 * de una cola (FIFO).
 * @author Guillermo Bernal Lou
 */
public class Queue {
    private int size;
    private Node first;
    private Node last;
    public Queue(){
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }
    /**
     * Metodo que anade un nodo [n] al final
     * de la cola
     * @param n nodo a anadir
     */
    public void push(Node n){
        if(first != null){
            last.setNext(n);
            setLast(n);
        }else{
            setFirst(n);
            setLast(n);
        } size++;
    }

    /**
     * Metodo que quita el primer elemento
     * anadido de la cola y lo devuelve
     * @return el nodo retirado
     */
    public Node pop(){
        Node p = first;
        if(p != null) {
            setFirst(first.getNext());
            size--;
        }
        return p;
    }
    /**
     * Indica si la cola esta vacia o no.
     * @return devuelve [true] si y solo si la cola no contiene
     * ningun nodo. Devuelve [false] en caso contrario.
     */
    public boolean isEmpty(){
        return this.size == 0;
    }
    /**
     * Este método muestra por pantalla los nodos
     * que contiene la pila. Siempre desde la ultima (el
     * ultimo nodo en ser anadido) posición hasta la primera.
     */
    public void show(){
        Node p = first;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }
}
