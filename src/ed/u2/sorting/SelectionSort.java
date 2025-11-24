package ed.u2.sorting;
import java.util.Arrays;

// algoritmo generico de SelectionSort
public final class SelectionSort {
    // ordena un array generico
    public static <T extends Comparable<T>> SortContadores sort(T[] arreglo, boolean trace) {
        // inicializacion de contadores
        long comparaciones = 0;
        long swaps = 0;
        // inicio de medicion de tiempo en ns
        long tiempoInicio = System.nanoTime();
        // bucle externo: avanza a la pos donde se colocara el siguiente min
        for (int i = 0; i < arreglo.length - 1; i++) {
            int min = i; // el elemento actual es el min
            // bucle interno: busca el elemento mas pequeno en todo el array
            for (int j = i + 1; j < arreglo.length; j++) {
                comparaciones++;// cuenta cada comparacion hecha
                // uso de .compareTo ya que funciona con obj
                // si arreglo[min] > arreglo[j] encontro uno mas pequeño
                if (arreglo[min].compareTo(arreglo[j]) > 0) {
                    min = j; // actualiza el indice del min
                }
            }
            // si el min encontrado no esta en la pos actual, se hace swap
            if (min != i) {
                T temp = arreglo[i];
                arreglo[i] = arreglo[min];
                arreglo[min] = temp;
                swaps++; // minimiza swaps
            }
        }
        // fin de medicion de tiempo
        long tiempoFinal = System.nanoTime();
        return new SortContadores(tiempoFinal - tiempoInicio, comparaciones, swaps);
    }
}