package src.practica1ejercicio1;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, UserNotFoundException, MalformedURLException, IllegalAccessException {
        URLShortenerService urlShortener = new URLShortenerService();
        urlShortener.addUser("Guille", "1234");
        System.out.println("Comprobación de que funciona correctamente la autentificación y la fecha de caducidad");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        urlShortener.shortenURL("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "Guille", "1234");
        urlShortener.shortenURL("https://www.youtube.com/watch?v=QB7ACr7pUuE", "Guille", "1234", yesterday);
        urlShortener.shortenURL("https://www.youtube.com/watch?v=xvFZjo5PgG0", "Guille", "1234");
        String[] array = urlShortener.getURLsByUsername("Guille", "1234");
        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println("Comprobación de las URL sin usuario: ");
        urlShortener.shortenURL("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        System.out.println(urlShortener.looseURLs.get(0).getContent().getShortURL().toString());
    }
}
