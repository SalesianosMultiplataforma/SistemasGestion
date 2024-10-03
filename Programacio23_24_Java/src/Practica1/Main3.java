package src.Practica1;

import java.util.Random;

public class Main3 {
    /**
     * Crea un vector de [n] numeros enteros aleatorios entre
     * 1 y 100 y devuelve la desviacion
     * tipica, la media y el valor maximo y minimo.
     * [ARRAY_SIZE] marca el tamano del array
     * @author Guillermo Bernal
     */
    public static void main(String[] args) {
        final int ARRAY_SIZE = 10;
        Random random = new Random();
        int[] a = new int[ARRAY_SIZE];
        for(int i = 0; i < ARRAY_SIZE; i++){
            a[i] = random.nextInt(100) + 1;
        }
        Resultado resultado = new Resultado();
        MediaThread t1 = new MediaThread(a, resultado);
        MinMaxThread t2 = new MinMaxThread(a, resultado);
        DesviacionThread t3 = new DesviacionThread(a, resultado);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            System.out.println("La media es: " + resultado.getMedia());
            System.out.println("El mínimo es: " + resultado.getMin() + " y el máximo: " + resultado.getMax());
            System.out.println("La desviación típica es: " + resultado.getDesviacion());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin");
    }
}
