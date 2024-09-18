package src.eda;
/*
 * Esta clase contiene los métodos para la creación
 * de una pila (LIFO).
 * @author Guillermo Bernal Lou
 */
public class Stach {
    private int size;
    private Node last;

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

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    /**
     * Metodo que anade un nodo [n] al final
     * de la pila
     * @param n nodo a anadir
     */
    public void push(Node n){
        if(last != null){
            n.setNext(last);
            setLast(n);
        }else{
            setLast(n);
        } size++;
    }

    /**
     * Metodo que quita el ultimo elemento
     * anadido de la pila y lo devuelve
     * @return el nodo retirado
     */
    public Node pop(){
        Node p = last;
        if(p != null) {
            setLast(last.getNext());
            size--;
        }
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
        Node p = last;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }
}
