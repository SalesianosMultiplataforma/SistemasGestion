package src.practica1ejercicio2;

/**
 * Esta clase contiene los métodos y atributos de la clase Avion.
 * Posee un identificador [identificador], un valor entero indicando la
 * capacidad maxima [capacidad] y una SimpleLinkedList [pasajeros] en la
 * que se almacena el numero de pasajeros dentro del avion
 * @author Guillermo Bernal y Miguel Reyna
 */
public class Avion extends DoubleNode{
    private String identificador;
    //private final int capacidad = 300; //Capacidad máxima del avión
    SimpleLinkedList<Pasajero> pasajeros = new SimpleLinkedList<>();
    public Avion(String identificador){
        this.identificador = identificador;
    }
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public SimpleLinkedList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(SimpleLinkedList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }
}
