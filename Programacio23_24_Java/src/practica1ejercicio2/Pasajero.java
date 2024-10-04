package src.practica1ejercicio2;
/**
 * Esta clase representa un pasajero
 * de un aeropuerto, conteniendo su DNI,
 * nombre y avion
 * @author Guillermo Bernal y Miguel Reyna
 */
public class Pasajero {
    private String nombre;
    private String dni;
    private String avion;
    public Pasajero( String nombre, String dni, String avion){
        this.nombre = nombre;
        this.dni = dni;
        this.avion = avion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAvion() {
        return avion;
    }

    public void setAvion(String avion) {
        this.avion = avion;
    }
}
