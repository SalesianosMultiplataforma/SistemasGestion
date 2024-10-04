package src.practica1ejercicio1;
/**
 * Exception that triggers when an URL is not found in the DLL
 * @author Guillermo Bernal y Miguel Reyna
 */
public class URLNotFoundException extends Exception {
    public URLNotFoundException(String message) {
        super(message);
    }
}