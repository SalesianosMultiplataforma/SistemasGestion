package src.practica1ejercicio1;

/*
 * Esta clase contiene los métodos para la creación
 * de una una DobleLinkedList.
 * @author Guillermo Bernal y Miguel Reyna
 */
public class DoubleLinkedList<T> {
    private int size;
    private DoubleNode<T> first;
    public DoubleLinkedList(){
        this.size = 0;
        this.first = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public DoubleNode<T> getFirst() {
        return first;
    }

    public void setFirst(DoubleNode<T> first) {
        this.first = first;
    }
    /**
     * Este método permite anadir un nuevo nodo a la lista
     * enlazada doble. Lo inserta en la ultima posicion.
     * @param node contiene el nodo a insertar.
     */
    public void add(DoubleNode<T> node){
        if (node == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo");
        }
        if(isEmpty()) {
            this.first = node;
        } else {
            DoubleNode<T> p = first;
            while(p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(node);
            node.setPrevious(p);
        }
        size++;
    }

    /**
     * Este método permite eliminar un nodo de la lista
     * en la posicon con valor [position]
     * En Java cuando un Nodo no es apuntado por ningun puntero,
     * se borra automaticamente.
     * @param position contiene la posicion del nodo a eliminar
     */
    public void delete(int position){
        if (position == 0) {
            first = first.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
        } else{
            DoubleNode<T> p = first;
            for(int i = 0; i < position - 1; i++){
                p = p.getNext();
            }if(position != (size - 1)){
                p.getNext().getNext().setPrevious(p);
                p.setNext(p.getNext().getNext());
            } else p.setNext(null);
        }
        size--;
    }

    /**
     * Devuelve el Nodo de la posicion [position]
     * @param position posicion del nodo que devuelve
     * @return nodo que se devuelve
     */
    public DoubleNode<T> get(int position){
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de los límites: " + position);
        }
        DoubleNode<T> p = first;
        for(int i = 0; i < position; i++){
            p = p.getNext();
        }
        return p;
    }
    /**
     *Indica si la lista esta vacia o no.
     * @return devuelve [true] si y solo si la lista no contiene
     * ningun nodo. Devuelve [false] en caso contrario.
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * Este método muestra por pantalla los nodos
     * que contiene la lista enlazada. Siempre desde la primera
     * posición hasta la ultima.
     */
    public void show(){
        DoubleNode<T> p = first;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }
}
