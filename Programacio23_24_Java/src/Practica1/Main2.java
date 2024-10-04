package src.Practica1;
import java.util.Random;

public class Main2 {
    /**
     * Pre: ---
     * Post: crea 3 hilos distintos, cada uno con sus atributos
     * 		correspondientes y los ejecuta. Podemos observar
     * 		la ejecución entrelazada.
     */
    public static void main(String[] args) {
        /*
         * Creación de los hilos
         */
        final int NUM_THREADS = 10;
        Random random = new Random();
        Thread[] t = new Thread2[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++){
            //retardo e iteraciones aleatorias
            int retardo = random.nextInt(201) + 100;
            int veces = random.nextInt(11) + 5;
            t[i] = new Thread2("Soy " + (i + 1), retardo, veces);
        }
        /*
         * Inicialización de los hilos
         */
        for(int i = 0; i < NUM_THREADS; i++){
            t[i].start();
        }
        /*
         * Esperamos a que terminen los hilos
         */
        try {
            for(int i = 0; i < NUM_THREADS; i++){
                t[i].join();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Fin");
    }
}
