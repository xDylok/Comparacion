package ed.u2.sorting;

import java.util.Arrays;

public class SortingUtils {

    // HABILITAR TRAZAS EN 3 CLASES:
    public static final boolean HABILITAR_TRAZAS = false;

    // Colores en consola
    public static final String C_RESET = "\u001B[0m";
    public static final String C_ROJO = "\u001B[31m";
    public static final String C_VERDE = "\u001B[32m";
    public static final String C_AMARILLO = "\u001B[33m";
    public static final String C_AZUL = "\u001B[34m";
    public static final String C_CELESTE = "\u001B[36m";

    // SIRVE PARA CALCULAR CONSTANTE DE KAPREKAR: 19/11/25
    // convertir array a int y viceversa para constante de kaprekar
    public static int arrayToInt(int[] array) {
        String op = Arrays.toString(array);
        String digitosOp = op.replaceAll("[^0-9]", "");
        return Integer.parseInt(digitosOp);
    }

    public static int[] intToArray(int numero) {
        String s = String.format("%04d", numero);
        int[] array = new int[4];
        for (int i = 0; i < 4; i++) {
            array[i] = Character.getNumericValue(s.charAt(i));
        }
        return array;
    }


    // mostrar arreglo:
    public static String mostrarArregloFinal(int[] arreglo){
        return SortingUtils.C_AMARILLO +
                "| ---------- "+ "Resultado Final: " +
                SortingUtils.C_AZUL + Arrays.toString(arreglo) +
                SortingUtils.C_AMARILLO +" ---------- |\n" +
                SortingUtils.C_RESET;
    }

    // dataSets:
    private static final int[] DATA_SET_A = {8, 3, 6, 3, 9};
    private static final int[] DATA_SET_B = {5, 4, 3, 2, 1}; // inverso
    private static final int[] DATA_SET_C = {1, 2, 3, 4, 5}; // Ordenado, caso borde
    private static final int[] DATA_SET_D = {2, 2, 2, 2}; // duplicados
    private static final int[] DATA_SET_E = {9, 1, 8, 2};
    // casos borde (CB)
    private static final int[] DATA_SET_F = {}; // 1. array vacio
    private static final int[] DATA_SET_G = {2}; // 2. un elemento
    private static final int[] DATA_SET_H = {-1, -10, -8, -5, -4}; // 3. elementos negativos
    private static final int[] DATA_SET_I = {0, -8, -3, 1, 3, -10}; // 4. elementos negativos y positivos
    private static final int[] DATA_SET_J = {1, 3, 4, 10, 6}; // 5. casi ordenado
    private static final int[] DATA_SET_K = {10, 1, 3, 4, 6}; // 6. casi ordenado, un elemento mal en el inicio

    public static int[] getDataSetA() {
        return Arrays.copyOf(DATA_SET_A, DATA_SET_A.length);
    }

    public static int[] getDataSetB() {
        return Arrays.copyOf(DATA_SET_B, DATA_SET_B.length);
    }

    public static int[] getDataSetC() {
        return Arrays.copyOf(DATA_SET_C, DATA_SET_C.length);
    }

    public static int[] getDataSetD() {
        return Arrays.copyOf(DATA_SET_D, DATA_SET_D.length);
    }

    public static int[] getDataSetE() {
        return Arrays.copyOf(DATA_SET_E, DATA_SET_E.length);
    }

    public static int[] getDataSetF() {
        return Arrays.copyOf(DATA_SET_F, DATA_SET_F.length);
    }

    public static int[] getDataSetG() {
        return Arrays.copyOf(DATA_SET_G, DATA_SET_G.length);
    }

    public static int[] getDataSetH() {
        return Arrays.copyOf(DATA_SET_H, DATA_SET_H.length);
    }

    public static int[] getDataSetI() {
        return Arrays.copyOf(DATA_SET_I, DATA_SET_I.length);
    }

    public static int[] getDataSetJ() {
        return Arrays.copyOf(DATA_SET_J, DATA_SET_J.length);
    }

    public static int[] getDataSetK() {
        return Arrays.copyOf(DATA_SET_K, DATA_SET_K.length);
    }
}
