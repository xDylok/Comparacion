package ed.u2.sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingExperiment {
    // clase principal que ejecuta los datasets csv
    public static void main(String[] args) {
        // formato visual de la tabla:
        String lineaSeparadora = "+-----------------+-----------------+-------+-----------------+------------+-----------------+";
        // %-15s alinea el string a la izquierda, ancho 15
        String formatoTabla    = "| %-15s | %-15s | %-5s | %-15s | %-10s | %-15s |%n";
        // imprime encabezado
        System.out.println(lineaSeparadora);
        System.out.printf(formatoTabla, "Algoritmo", "Dataset", "N", "Comparaciones", "Swaps", "Tiempo(ns)");
        System.out.println(lineaSeparadora);

        try {
            // --- EXP 1: Citas (N=100, Aleatorio)
            // lee csv y ejecuta pruebas
            Cita[] citas = ArchivosCSV.leerCitas("citas_100.csv");
            correrSetDePruebas("Citas_100", citas);
            System.out.println(lineaSeparadora);

            // --- EXP 2: Citas Casi Ordenadas (N=100)
            // prueba para ver la eficiencia de insertionsort
            Cita[] citasCasi = ArchivosCSV.leerCitas("citas_100_casi_ordenadas.csv");
            correrSetDePruebas("Citas_CasiOrd", citasCasi);
            System.out.println(lineaSeparadora);

            // --- EXP 3: Pacientes (N=500, Duplicados)
            // prueba para ver estabilidad y comportamiento con valores repetidos
            Paciente[] pacientes = ArchivosCSV.leerPacientes("pacientes_500.csv");
            correrSetDePruebas("Pacientes_500", pacientes);
            System.out.println(lineaSeparadora);

            // --- EXP 4: Inventario (N=500, Inverso)
            // pero caso para bubble e insertion
            Item[] items = ArchivosCSV.leerInventario("inventario_500_inverso.csv");
            correrSetDePruebas("Inventario_Inv", items);
            System.out.println(lineaSeparadora);

        } catch (IOException e) {
            System.err.println("Error leyendo archivos CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // metoedo q ejecuta los 3 algoritmos para un dataset, es generico para aceptar cualquier arreglo  de obj comparable
    public static <T extends Comparable<T>> void correrSetDePruebas(String nombreDataset, T[] datosOriginales) {
        ejecutarAlgoritmo("BubbleSort", nombreDataset, datosOriginales);
        ejecutarAlgoritmo("SelectionSort", nombreDataset, datosOriginales);
        ejecutarAlgoritmo("InsertionSort", nombreDataset, datosOriginales);
    }

    // ejecuta 10 repeticiones, descarta las 3 primeras y calcula la mediana
    public static <T extends Comparable<T>> void ejecutarAlgoritmo(String algoritmo, String dataset, T[] datosOriginales) {
        List<Long> tiempos = new ArrayList<>(); // guarta swaps
        SortContadores ultimoResultado = null;
        int N = datosOriginales.length;

        for (int reps = 0; reps < 10; reps++) {
            // crea una copia para cada rep para evitar ordenar la original
            T[] copia = Arrays.copyOf(datosOriginales, datosOriginales.length);

            SortContadores stats;
            // seleccion de algoritmo
            switch (algoritmo) {
                case "BubbleSort" -> stats = BubbleSort.sort(copia, false);
                case "SelectionSort" -> stats = SelectionSort.sort(copia, false);
                case "InsertionSort" -> stats = InsertionSort.sort(copia, false);
                default -> throw new IllegalArgumentException("Algoritmo no reconocido");
            }
            // descarta las primeras 3 repeticiones
            if (reps >= 3) {
                tiempos.add(stats.tiempoNano);
            }
            ultimoResultado = stats;
        }
        // calcula la mediana
        Collections.sort(tiempos);
        long mediana = tiempos.get(tiempos.size() / 2);
        // imprime fila de la tabla
        System.out.printf("| %-15s | %-15s | %-5d | %-15d | %-10d | %-15d |%n",
                algoritmo,
                dataset,
                N,
                ultimoResultado.comparaciones,
                ultimoResultado.swaps,
                mediana);
    }
}