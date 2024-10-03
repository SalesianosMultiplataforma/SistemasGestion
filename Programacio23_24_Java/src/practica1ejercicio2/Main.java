package src.practica1ejercicio2;

public class Main {
    public static void main(String[] args){
        //Se crea constante indicando el avion del que se desean ver los pasajeros
        final String ID_AVION_DESEADO = "123";
        //Se inicializan los pasajeros y el aeropuerto. La ID del avión se
        //crea como String dado que 023 como int se interpreta en base 8 y se transforma a 19
        Pasajero pasajero1 = new Pasajero("Luis","123123123H", "123");
        Pasajero pasajero2 = new Pasajero("Elena", "321321321D", "023");
        Pasajero pasajero3 = new Pasajero("Carmen", "17799874X", "123");
        Pasajero pasajero4 = new Pasajero("Manuel", "22669988D", "123");
        Aeropuerto aeropuerto = new Aeropuerto();
        //Los pasajeros llegan al aeropuerto
        aeropuerto.handleLlegadaPasajero(pasajero1);
        aeropuerto.handleLlegadaPasajero(pasajero2);
        aeropuerto.handleLlegadaPasajero(pasajero3);
        aeropuerto.handleLlegadaPasajero(pasajero4);
        //Los pasajeros entregan la documentacion
        aeropuerto.handleAccesoPasajeroAvion();
        aeropuerto.handleAccesoPasajeroAvion();
        //Se busca el aeropuerto con id deseada
        int i = 0;
        while((!aeropuerto.hangar.get(i).getContent().getIdentificador().equals(ID_AVION_DESEADO)) && (i < aeropuerto.hangar.getSize())){
            i++;
        }
        //Se muestra pasajeros en el avion
        System.out.println("Pasajeros en el avión " + ID_AVION_DESEADO + ": ");
        i = 0;
        while(i < aeropuerto.hangar.get(0).getContent().getPasajeros().getSize()){
            System.out.println(aeropuerto.hangar.get(0).getContent().getPasajeros().get(i).getContent().getNombre());
            i++;
        }
        //Se entrega una documentacion y se muestran todas las colecciones
        aeropuerto.handleAccesoPasajeroAvion();
        System.out.println("Aviones: ");
        int j = 0;
        i = 0;
        while(aeropuerto.hangar.getSize() > i){
            System.out.println("Avión " + aeropuerto.hangar.get(i).getContent().getIdentificador());
            System.out.println("Pasajeros:");
            while(aeropuerto.hangar.get(i).getContent().getPasajeros().getSize() > j){
                System.out.println("Pasajero del avion " +  aeropuerto.hangar.get(i).getContent().getIdentificador() + aeropuerto.hangar.get(i).getContent().getPasajeros().get(j).getContent().getNombre());
                j++;
            }
            i++;
        }
        i = 0;
        System.out.println("Cola mostrador:");
        Queue<Pasajero> colaMostradorCopia = aeropuerto.colaMostrador;
        while(!colaMostradorCopia.isEmpty()){
            System.out.println(colaMostradorCopia.pop().getContent().getNombre());
        }
        System.out.println("Sala de espera:");
        Stach<Pasajero> salaEsperaCopia = aeropuerto.salaEspera;
        while(!salaEsperaCopia.isEmpty()){
            System.out.println(salaEsperaCopia.pop().getContent().getNombre());
        }
    }
}
