package src.practica1ejercicio2;
/**
 * Esta clase contiene los métodos y atributos de la clase
 * aeropuerto. Las constantes [QUEUE_SIZE] y [STACH_SIZE] marcan
 * el tamano maximo de la pila y la cola.
 * @author Guillermo Bernal y Miguel Reyna
 */
public class Aeropuerto {
    final int QUEUE_SIZE = 2;
    final int STACH_SIZE = 3;
    Queue<Pasajero> colaMostrador = new Queue<>();
    Stach<Pasajero> salaEspera = new Stach<>();
    DoubleLinkedList<Avion> hangar = new DoubleLinkedList<>();
    public Aeropuerto(){
        Avion avion1 = new Avion("123");
        Avion avion2 = new Avion("023");
        DoubleNode<Avion> nodoAvion1 = new DoubleNode<>(avion1);
        DoubleNode<Avion> nodoAvion2 = new DoubleNode<>(avion2);
        hangar.add(nodoAvion1);
        hangar.add(nodoAvion2);
    }

    /**
     * Clase que maneja la llegada de un pasajero [pasajero] al aeropuerto.
     * Si la cola del mostrador tiene espacio, irá allí.
     * Si está llena ira a la sala de espera. Si tambien está llena,
     * se monstrara una advertencia por pantalla.
     * El tamano de la cola y la sala se define en [QUEUE_SIZE] y [STACH_SIZE]
     * @param pasajero
     */
    public void handleLlegadaPasajero(Pasajero pasajero){
        if(colaMostrador.getSize() < QUEUE_SIZE){
            SimpleNode<Pasajero> nodoPasajero = new SimpleNode<>(pasajero);
            colaMostrador.push(nodoPasajero);
        }else{
            if(salaEspera.getSize() < STACH_SIZE){
                SimpleNode<Pasajero> nodoPasajero = new SimpleNode<>(pasajero);
                salaEspera.push(nodoPasajero);
            }else{
                System.out.println("¡Lo sentimos! Aeropuerto lleno.");
            }
        }
    }

    /**
     * Metodo que maneja el acceso de un pasajero a un avion.
     * Si la cola del mostrador está vacia, se avisará y ningún pasajero entrara.
     * Si hay pasajeros que quieran entrar, se sacaran de la cola y meterán en [nodoPasajero]
     * Si hay alguien en la sala de espera, pasará a la cola (LIFO)
     * Se buscará el avion correspondiente de [nodoPasajero]. Si existe, el pasajero se metera dentro
     * @return Devolvera true solo si el pasajero ha accedido al avion correctamente
     * (aunque no se hace uso de esta característica en Main.java)
     */
    public boolean handleAccesoPasajeroAvion(){
        if(colaMostrador.isEmpty()){
            System.out.println("No hay más pasajeros que quieran entrar");
            return false;
        }
        SimpleNode<Pasajero> nodoPasajero = new SimpleNode<>(colaMostrador.pop().getContent());
        if (!salaEspera.isEmpty()) {
            colaMostrador.push(salaEspera.pop());
        }
        int i = 0;
        while (i < hangar.getSize() && !nodoPasajero.getContent().getAvion().equals(hangar.get(i).getContent().getIdentificador())) {
            i++;
        }
        if (i >= hangar.getSize()) {
            System.out.println("No se encontró el avión correspondiente para " + nodoPasajero.getContent().getNombre());
            return false;
        }
        hangar.get(i).getContent().getPasajeros().add(nodoPasajero);
        return true;
    }
}