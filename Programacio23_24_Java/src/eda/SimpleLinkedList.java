package src.eda;
/*
* Esta clase contiene los métodos para la creación
* de una lista enlazada simple.
* @author Guillermo Bernal Lou
 */
public class SimpleLinkedList {
    private int size;
    private Node first;
    public SimpleLinkedList(){
        this.size = 0;
        this.first = null;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
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
    public void add(Node node){
        if(isEmpty()) this.first = node;
        else{
            Node p = first;
            while(p.getNext() != null){
                p = p.getNext();
            } p.setNext(node);
        } size++;
    }

    /**
     * Este metodo anade polimorfismo al metodo add,
     * permitiendo que se pueda elegir la posicion a insertar del nodo.
     * Si la posicion es superior al tamano de la lista, se anadira en ultimo
     * lugar
     * @param node nodo a insertar
     * @param position posicion en la que se desea añadir el nodo
     */
    public void add(Node node, int position){
        if(position == 0){
            Node p = first;
            first = node;
            node.setNext(p);
        }else{
            Node p = first;
            for(int i = 0; i < position - 1 && i < size - 1; i++){
                p = p.getNext();
            }if(position < size){
                Node q = p.getNext();
                p.setNext(node);
                node.setNext(q);
            }
            else p.setNext(node);
        }
        size++;
    }

    /**
     * Este metodo mueve un nodo desde la
     * posicion [prevPosition] hasta la posicion
     * [nextPosition]. Si [nextPosition] es mayor a [size]
     * se pondra en ultimo lugar. Si [prevPosition] es mayor a
     * [size] (y por tanto no existe) no hara nada.
     * @param prevPosition posicion original del nodo
     * @param nextPosition posicion final del nodo
     */
    public void move(int prevPosition, int nextPosition){
        if(prevPosition < size){
            Node p = first;
            for(int i = 0; i < prevPosition - 1; i++){
                p = p.getNext();
            }
            Node nodoMovido;
            if(prevPosition == 0){
                 nodoMovido = p;
                 setFirst(p.getNext());
            }else{
                nodoMovido = p.getNext();
                p.setNext(nodoMovido.getNext());
            }
            p = first;
            //size - 2 porque falta el nodoMovido de la lista
            for(int i = 0; i < nextPosition - 1 && i < size - 2; i++){
                p = p.getNext();
            }
            Node r = p.getNext();
            p.setNext(nodoMovido);
            nodoMovido.setNext(r);
        }
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
            Node p = first;
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
    public Node get(int position){
        Node p = first;
        for(int i = 0; i < position - 1; i++){
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
        Node p = first;
        while(p != null){
            System.out.println(p);
            p = p.getNext();
        }
    }


}
