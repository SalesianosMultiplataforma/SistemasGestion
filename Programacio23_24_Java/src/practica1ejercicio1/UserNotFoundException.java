package src.practica1ejercicio1;
/**
 * Exception that triggers when a user is not found in the DLL
 * @author Guillermo Bernal y Miguel Reyna
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}