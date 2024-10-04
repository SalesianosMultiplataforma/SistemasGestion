package src.practica1ejercicio1;

/**
 * Class that models a User
 * @author Guillermo Bernal y Miguel Reyna
 */
public class User {

    // TODO: Implement any missing methods / variables you may need
    // TODO: IMPORTANT!!! Do NOT change the username, password and salt variables nor their types

    /**
     * The user's name
     */
    private String username;
    /**
     * The user's password hash
     * ATTENTION: The user's password MUST NOT BE STORED in plaintext !!!
     */
    private String password;

    /**
     * User's password salt
     */
    private String salt;

    /**
     * User's URLs
     */
    private DoubleLinkedList<URLEntry> urlEntries = new DoubleLinkedList<>();
    /**
     * Constructs a User
     * @param username User's name
     * @param password User's password hash
     * @param salt User's password salt
     */
    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
    /**
     * Get user's username
     * @return User's username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Get user's password hash
     * @return User's password hash
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get user's password salt
     * @return User's password salt
     */
    public String getPasswordSalt() {
        return salt;
    }
    public DoubleLinkedList<URLEntry> getUrlEntries(){ return urlEntries; }
}
