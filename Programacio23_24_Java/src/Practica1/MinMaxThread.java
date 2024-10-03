package src.Practica1;

/**
 * Clase que calcula el maximo y minimo del array
 * de enteros [numbers]
 * y la almacena en las variables [min] y [max]
 * dentro del objeto [resultado]
 */
public class MinMaxThread extends Thread{
    private int[] numbers;
    private Resultado resultado;
    public MinMaxThread(int[] numbers, Resultado resultado) {
        this.numbers = numbers;
        this.resultado = resultado;
    }
    public void run() {
        for (int num : numbers) {
            if (num < resultado.getMin()) resultado.setMin(num);
            if (num > resultado.getMax()) resultado.setMax(num);
        }
    }
}
