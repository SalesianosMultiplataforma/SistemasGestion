package src.practica1ejercicio2;
/**
 * Esta clase contiene los métodos para la creación
 * de una pila (LIFO).
 * @author Guillermo Bernal y Miguel Reyna
 */
public class Stach<T> {
    private int size;
    private SimpleNode<T> last;

    public Stach(){
        this.size = 0;
        this.last = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SimpleNode<T> getLast() {
        return last;
    }

    public void setLast(SimpleNode<T> last) {
        this.last = last;
    }

    /**
     * Metodo que anade un nodo [n] al final
     * de la pila
     * @param n nodo a anadir
     */
    public void push(SimpleNode<T> n) {
        n.setNext(last);
        setLast(n);
        size++;
    }

    /**
     * Metodo que quita el ultimo elemento
     * anadido de la pila y lo devuelve
     * @return el nodo retirado
     */
    public SimpleNode<T> pop() {
        if (isEmpty()) return null;
        SimpleNode<T> p = last;
        setLast(last.getNext());
        size--;
        return p;
    }
    /**
     *Indica si la pila esta vacia o no.
     * @return devuelve [true] si y solo si la pila no contiene
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
        SimpleNode<T> p = last;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }
}
