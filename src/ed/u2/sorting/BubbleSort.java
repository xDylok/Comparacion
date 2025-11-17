package ed.u2.sorting;

import java.util.Arrays;

// Ordenamiento burbuja: compara entre dos elementos de un array
public final class BubbleSort {
    public static void sort(int[] arreglo) {
        sort(arreglo, false);
    }

    public static void sort(int[] arreglo, boolean trace) {
        int nro = arreglo.length;
        // si el array esta vacio o tiene un elemento, no ordena nada
        if (nro < 2) {
            // muestra el arreglo vacio o de un elemento
            System.out.println(SortingUtils.mostrarArregloFinal(arreglo));
            return; // termina la ejecucion
        }
        //bucle externo, contrala el nro de pasadas, se ejecuta n-1 veces
        for (int i = 0; i < arreglo.length; i++) {
            // reastrear si se hizo algun cambio: en esta pasada, se reinicia al inicio de cada pasada
            boolean swapped = false;
            if (trace) {
                System.out.println(SortingUtils.C_AZUL + "\n-Pasada: " + (i + 1) + SortingUtils.C_RESET);
                System.out.println("Actual: " + Arrays.toString(arreglo));
            }
            // bucle interno: hace comparaciones e intercambios, mueve el elemento mas grande hasta el final del array
            for (int j = 0; j < nro - i - 1; j++) {
                // compara elementos juntos
                if (arreglo[j] > arreglo[j + 1]) {
                    // si estan mal arreglados, se intercambian
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    swapped = true; // marca si hubo un intercambio
                    if (trace) {
                        System.out.println(SortingUtils.C_ROJO + "\t> SWAP: " + SortingUtils.C_RESET + arreglo[j + 1] + " <-> " + arreglo[j] +
                                " | " + SortingUtils.C_CELESTE + Arrays.toString(arreglo) + SortingUtils.C_RESET);
                    }
                }
            }
            //si swapped es falso, no hubo intercambios, el array ya esta ordenado
            if (!swapped) {
                if (trace) {
                    System.out.println(SortingUtils.C_VERDE +
                            "Corte Temprano: no hubo intercambios. Array ordenado." +
                            SortingUtils.C_RESET);
                }
                break; // rompe bucle externo
            }
        }
        // muestra el arreglo ordenado
        System.out.print(SortingUtils.mostrarArregloFinal(arreglo));
    }
}
