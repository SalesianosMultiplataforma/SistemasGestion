package src.practica1ejercicio2;
public class Main {
    public static void main(String[] args) {
        final String ID_AVION_DESEADO = "123";
        Pasajero pasajero1 = new Pasajero("Luis", "123123123H", "123");
        Pasajero pasajero2 = new Pasajero("Elena", "321321321D", "023");
        Pasajero pasajero3 = new Pasajero("Carmen", "17799874X", "123");
        Pasajero pasajero4 = new Pasajero("Manuel", "22669988D", "123");
        Aeropuerto aeropuerto = new Aeropuerto();
        // Los pasajeros llegan al aeropuerto
        aeropuerto.handleLlegadaPasajero(pasajero1);
        aeropuerto.handleLlegadaPasajero(pasajero2);
        aeropuerto.handleLlegadaPasajero(pasajero3);
        aeropuerto.handleLlegadaPasajero(pasajero4);
        // Los pasajeros entregan la documentación
        aeropuerto.handleAccesoPasajeroAvion();
        aeropuerto.handleAccesoPasajeroAvion();
        // Se busca el avión con id deseada
        int i = 0;
        while (i < aeropuerto.hangar.getSize() && !aeropuerto.hangar.get(i).getContent().getIdentificador().equals(ID_AVION_DESEADO)) {
            i++;
        }
        // Se muestra pasajeros en el avión
        if (i < aeropuerto.hangar.getSize()) {
            System.out.println("Pasajeros en el avión " + ID_AVION_DESEADO + ": ");
            for (int k = 0; k < aeropuerto.hangar.get(i).getContent().getPasajeros().getSize(); k++) {
                System.out.println(aeropuerto.hangar.get(i).getContent().getPasajeros().get(k).getContent().getNombre());
            }
        } else {
            System.out.println("El avión " + ID_AVION_DESEADO + " no se encontró.");
        }
        // Se entrega una documentación y se muestran todas las colecciones
        aeropuerto.handleAccesoPasajeroAvion();
        System.out.println("Aviones: ");
        int j;
        i = 0;
        while (aeropuerto.hangar.getSize() > i) {
            System.out.println("Avión " + aeropuerto.hangar.get(i).getContent().getIdentificador());
            System.out.println("Pasajeros:");
            j = 0;
            while (aeropuerto.hangar.get(i).getContent().getPasajeros().getSize() > j) {
                System.out.println("Pasajero del avión " + aeropuerto.hangar.get(i).getContent().getIdentificador() + ": " + aeropuerto.hangar.get(i).getContent().getPasajeros().get(j).getContent().getNombre());
                j++;
            }
            i++;
        }
        // Mostrar Cola mostrador
        i = 0;
        System.out.println("Cola mostrador:");
        Queue<Pasajero> colaMostradorCopia = aeropuerto.colaMostrador;
        while (!colaMostradorCopia.isEmpty()) {
            System.out.println(colaMostradorCopia.pop().getContent().getNombre());
        }
        // Mostrar Sala de espera
        System.out.println("Sala de espera:");
        Stach<Pasajero> salaEsperaCopia = aeropuerto.salaEspera;
        while (!salaEsperaCopia.isEmpty()) {
            System.out.println(salaEsperaCopia.pop().getContent().getNombre());
        }
    }
}
