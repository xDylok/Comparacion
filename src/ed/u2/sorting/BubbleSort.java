package ed.u2.sorting;
import java.util.Arrays;

// algoritmo generico de BubbleSort
public final class BubbleSort {

    public static SortContadores sort(int[] arreglo, boolean trace) {
        // no implementado
        return null;
    }

    // metodo de ordenacion generico
    // <T extends Comparable<T>>: restriccion que asegura que los obj sepan compararse entre ellos
    public static <T extends Comparable<T>> SortContadores sort(T[] arreglo, boolean trace) {
        // contadores
        long comparaciones = 0;
        long swaps = 0;
        // cronometrar con nanoTime() para mejor precision
        long tiempoInicio = System.nanoTime();
        int n = arreglo.length;
        // caso borde: si el array es muy pequeño, no se ordena nada, retornando tiempo 0
        if (n < 2) return new SortContadores(0, 0, 0);
        // bucle externo que controla las pasadas
        for (int i = 0; i < n - 1; i++) {
            // bandera para corte temprano
            boolean swapped = false;
            // bucle interno: intercambia el elemento mayor hasta el final
            // ignora los ultimos "i" elementos ya ordenados
            for (int j = 0; j < n - 1 - i; j++) {
                comparaciones++; // cuenta cada comparacion hecha
                // uso de .compareTo ya que funciona con obj
                // si el resultado es mayor a 0 significa que arreglo[j] es mayor que arreglo[j+1]
                if (arreglo[j].compareTo(arreglo[j + 1]) > 0) {
                    T temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    swapped = true; // marca que hubo cambio para evitar corte temprano
                    swaps++; // cuenta el intercambio
                }
            }
            // si se termina con una pasada completa sin hacer swaps, el array esta terminado
            if (!swapped) break;
        }
        // detiene el cronometro
        long tiempoFinal = System.nanoTime();
        // retorna el obj con los datos
        return new SortContadores(tiempoFinal - tiempoInicio, comparaciones, swaps);
    }
}