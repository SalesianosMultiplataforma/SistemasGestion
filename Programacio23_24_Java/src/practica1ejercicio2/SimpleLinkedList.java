package src.practica1ejercicio2;
/*
* Esta clase contiene los métodos para la creación
* de una lista enlazada simple.
* @author Guillermo Bernal y Miguel Reyna
 */
public class SimpleLinkedList<T> {
    private int size;
    private SimpleNode<T> first;
    public SimpleLinkedList(){
        this.size = 0;
        this.first = null;
    }

    public SimpleNode<T> getFirst() {
        return first;
    }

    public void setFirst(SimpleNode<T> first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Este método permite anadir un nuevo nodo a la lista
     * enlazada simple. Lo inserta en la ultima posicion.
     * @param node contiene el nodo a insertar.
     */
    public void add(SimpleNode<T> node){
        if (node == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo");
        }
        if(isEmpty()) this.first = node;
        else{
            SimpleNode<T> p = first;
            while(p.getNext() != null){
                p = p.getNext();
            } p.setNext(node);
        } size++;
    }
    /**
     * Este método permite eliminar un nodo de la lista
     * en la posicon con valor [position]
     * En Java cuando un Nodo no es apuntado por ningun puntero,
     * se borra automaticamente.
     * !! La mejor forma de hacer este ejercicio es hacer primero el get y
     *  programar el resto de metodos usandolo
     * @param position contiene la posicion del nodo a eliminar
     */
    public void delete(int position){
        if(position == 0){
            first = first.getNext();
        }else{
            SimpleNode<T> p = first;
            for(int i = 0; i < position - 1; i++){
                p = p.getNext();
            }if(position != (size - 1)) p.setNext(p.getNext().getNext());
            else p.setNext(null);
        }
        size--;
    }

    /**
     * Devuelve el Nodo de la posicion [position]
     * @param position posicion del nodo que devuelve
     * @return nodo que se devuelve
     */
    public SimpleNode<T> get(int position){
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de los límites: " + position);
        }
        SimpleNode<T> p = first;
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
        SimpleNode<T>p = first;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }


}
