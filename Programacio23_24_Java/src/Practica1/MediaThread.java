package src.Practica1;
/**
 * Clase que calcula la mediadel array
 * de enteros [numbers]
 * y la almacena en la variable [media]
 * dentro del objeto [resultado]
 */
public class MediaThread extends Thread{
    private int[] numbers;
    private Resultado resultado;
    public MediaThread(int[] numbers, Resultado resultado) {
        this.numbers = numbers;
        this.resultado = resultado;
    }
    public void run() {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        resultado.setMedia(sum / numbers.length);
    }
}
