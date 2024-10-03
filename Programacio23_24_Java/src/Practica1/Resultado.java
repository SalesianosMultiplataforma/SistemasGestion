package src.Practica1;

/**
 * Clase que contiene todos los parametros
 * pedidos en el ejercicio [min], [max],
 * [desviacion] y [media]. El uso de un objeto
 * permite replicar las llamadas por referencia
 * de otros lenguajes, los que nos da la oportunidad
 * de recibir los valores de las Thread directamente en
 * el main. Esto es posible dado que los Thread no comparten
 * valores, es decir, [min] solo se usa para calcular [min].
 * Si fuera el caso, sería necesario tomar acciones para
 * asegurar que se mantiene la coherencia en los datos entre
 * procesos.
 */
public class Resultado {
    private int min;
    private int max;
    private double desviacion;
    private double media;
    public Resultado(){
        this.min = 101;
        this.max = 0;
    }
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
