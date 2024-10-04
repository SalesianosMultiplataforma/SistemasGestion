package src.practica1ejercicio2;
/**
 * Esta clase contiene los métodos para la creación
 * de una cola (FIFO).
 * @author Guillermo Bernal y Miguel Reyna
 */
public class Queue<T> {
    private int size;
    private SimpleNode<T> first;
    private SimpleNode<T> last;
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

    public SimpleNode<T> getFirst() {
        return first;
    }

    public void setFirst(SimpleNode<T> first) {
        this.first = first;
    }

    public SimpleNode<T> getLast() {
        return last;
    }

    public void setLast(SimpleNode<T> last) {
        this.last = last;
    }
    /**
     * Metodo que anade un nodo [n] al final
     * de la cola
     * @param n nodo a anadir
     */
    public void push(SimpleNode<T> n) {
        if (first != null) {
            last.setNext(n);
        } else {
            setFirst(n);
        }
        setLast(n);
        size++;
    }

    /**
     * Metodo que quita el primer elemento
     * anadido de la cola y lo devuelve
     * @return el nodo retirado
     */
    public SimpleNode<T> pop() {
        if (isEmpty()) return null;
        SimpleNode<T> p = first;
        setFirst(first.getNext());
        if (first == null) {
            last = null;
        }
        size--;
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
        SimpleNode<T> p = first;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }
}
