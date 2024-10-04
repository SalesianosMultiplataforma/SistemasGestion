package src.practica1ejercicio1;
/**
 * Exception that triggers when an URL has expired
 * @author Guillermo Bernal y Miguel Reyna
 */
public class URLExpiredException extends Exception {
    public URLExpiredException(String message) {
        super(message);
    }
}