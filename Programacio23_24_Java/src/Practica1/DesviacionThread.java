package src.Practica1;

/**
 * Clase que calcula la desviacion tipica
 * del array de enteros [numbers]
 * y la almacena en la variable [desviacion]
 * dentro del objeto [resultado]
 */
public class DesviacionThread extends Thread{
    private int[] numbers;
    private Resultado resultado;
    public DesviacionThread(int[] numbers, Resultado resultado) {
        this.numbers = numbers;
        this.resultado = resultado;
    }
    public void run() {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double media = sum / numbers.length;

        // Calcular la desviación típica
        double sumDesviacion = 0;
        for (int num : numbers) {
            sumDesviacion += Math.pow(num - media, 2);
        }
        resultado.setDesviacion(Math.sqrt(sumDesviacion / numbers.length));
    }
}
