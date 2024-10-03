package src.practica1ejercicio2;
/*
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
    public void handleLlegadaPasajero(Pasajero pasajero){
        System.out.println("Gestionando a " + pasajero.getNombre());
        if(colaMostrador.getSize() < QUEUE_SIZE){
            SimpleNode<Pasajero> nodoPasajero = new SimpleNode<>(pasajero);
            colaMostrador.push(nodoPasajero);
            System.out.println(pasajero.getNombre() + " metido a colaMostrador");
        }else{
            if(salaEspera.getSize() < STACH_SIZE){
                SimpleNode<Pasajero> nodoPasajero = new SimpleNode<>(pasajero);
                salaEspera.push(nodoPasajero);
                System.out.println(pasajero.getNombre() + " metido a salaEspera");
            }else{
                System.out.println("¡Lo sentimos! Aeropuerto lleno.");
            }
        }
    }
    public boolean handleAccesoPasajeroAvion(){
        if(colaMostrador.isEmpty()){
            System.out.println("No hay más pasajeros que quieran entrar");
            return false;
        }
        SimpleNode<Pasajero> nodoPasajero = colaMostrador.pop();
        System.out.println("Moviendo a " + nodoPasajero.getContent().getNombre());
        if (!salaEspera.isEmpty()) {
            colaMostrador.push(salaEspera.pop());
        }
        int i = 0;
        while (!(nodoPasajero.getContent().getAvion().equals(hangar.get(i).getContent().getIdentificador())) && (i < hangar.getSize() - 1)) {
            i++;
        }
        hangar.get(i).getContent().getPasajeros().add(nodoPasajero);
        System.out.println("Movido " + nodoPasajero.getContent().getNombre() + " al avion " + hangar.get(i).getContent().getIdentificador());
        System.out.println("Confirmación: " );
        hangar.get(0).getContent().getPasajeros().show();
        int j = 0;
        i = 0;
        System.out.println("Skull");
        while(hangar.getSize() > i){
            System.out.println("Avión " + hangar.get(i).getContent().getIdentificador());
            System.out.println("Pasajeros:");
            while(hangar.get(i).getContent().getPasajeros().getSize() > j){
                System.out.println(hangar.get(i).getContent().getIdentificador() + hangar.get(i).getContent().getPasajeros().get(j).getContent().getNombre());
                j++;
            }
            i++;
        }
        System.out.println("fin Skull");
        return true;
    }
}