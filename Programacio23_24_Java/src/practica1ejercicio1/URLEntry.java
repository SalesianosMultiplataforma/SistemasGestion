package src.practica1ejercicio1;

import java.net.URL;
import java.util.Date;

/**
 * Class that contains an URLEntry
 * @author Guillermo Bernal y Miguel Reyna
 */
public class URLEntry {

    // TODO: Implement any missing methods / variables you may need
    // TODO: IMPORTANT!!! Do NOT change the shortURL, longURL and expirationTimestamp variables nor their types

    /**
     * The short URL
     */
    private URL shortURL;

    /**
     * THe long URL
     */
    private URL longURL;

    /**
     * The expiration timestamp
     */
    private Date expirationTimestamp;

    /**
     * Creates a URLEntry without expiration date
     *
     * @param shortURL The short URL
     * @param longURL The long URL
     */
    public URLEntry(URL shortURL, URL longURL) {
        this.shortURL = shortURL;
        this.longURL = longURL;
    }
    /**
     * Creates a URLEntry with expiration date
     *
     * @param shortURL The short URL
     * @param longURL The long URL
     * @param expirationTimestamp The expiration date
     */
    public URLEntry(URL shortURL, URL longURL, Date expirationTimestamp) {
        this.shortURL = shortURL;
        this.longURL = longURL;
        this.expirationTimestamp = expirationTimestamp;
    }
    public URL getShortURL() {
        return shortURL;
    }

    public URL getLongURL() {
        return longURL;
    }

    public Date getExpirationTimestamp() {
        return expirationTimestamp;
    }
}