package ed.u2.sorting;
import java.util.Arrays;

// algoritmo generico de InsertionSort
public final class InsertionSort {
    // ordena un array generico
    public static <T extends Comparable<T>> SortContadores sort(T[] arreglo, boolean trace) {
        // inicializacion de contadores
        long comparaciones = 0;
        long swaps = 0;
        // inicio de medicion de tiempo en ns
        long tiempoInicio = System.nanoTime();
        // bucle principal, empieza desde el segundo elemento
        for (int i = 1; i < arreglo.length; i++) {
            T aux = arreglo[i]; // elemento a instertar
            int pos = i - 1; // indice del elemento anterior
            // bucle interno: mueve elementos mayores hacia la derecha
            while (pos >= 0) {
                comparaciones++;// cuenta cada comparacion hecha
                // uso de .compareTo ya que funciona con obj
                // si el resultado es mayor a 0 significa que arreglo[j] es mayor que arreglo[j+1]
                if (arreglo[pos].compareTo(aux) > 0) {
                    arreglo[pos + 1] = arreglo[pos];
                    pos--;
                    swaps++; //
                } else {
                    // si el elemento no es mayor, se ecnontro la posicion correcta, rompe el bucle
                    break;
                }
            }
            // inserta el elemento "aux" en la posicion ordenada
            arreglo[pos + 1] = aux;
        }
        // finaliza la medicion de tiempo
        long tiempoFinal = System.nanoTime();
        // retorna el conteo W
        return new SortContadores(tiempoFinal - tiempoInicio, comparaciones, swaps);
    }
}