package ed.u2.sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/*
 * Clase la cual se encarga de leer los archivos CSV (datasets), convierte las lineas de texto del archivo en objetos
 * (Cita, Paciente, Item) para procesarlos con los algoritmos de ordenamiento
 */
public class ArchivosCSV {
    // ruta donde se encuentran los archivos cvs generados
    private static final String RUTA_BASE = "src/ed/u2/CSV/";
    // lee el archivo csv de citas y lo convierte en un arreglo de obj
    public static Cita[] leerCitas(String nombreArchivo) throws IOException {
        List<Cita> lista = new ArrayList<>();
        // asegura que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // lee y descarta la primera linea (encabezado)
            // ciclo para leer el resto del archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // separa la linea usando como delimitador ";"
                String[] partes = linea.split(";");
                // asegura que la linea esta completa
                if (partes.length == 3) {
                    //se crea el obj cita
                    lista.add(new Cita(partes[0], partes[1], LocalDateTime.parse(partes[2])));
                }
            }
        }
        // convierte la lista a un array estatico para los algoritmos de ordenacion
        return lista.toArray(new Cita[0]);
    }

    //lee archivo csv pacientes, convirtiendo a un array de obj Paciente
    public static Paciente[] leerPacientes(String nombreArchivo) throws IOException {

        List<Paciente> lista = new ArrayList<>();
        // asegura que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // lee y descarta la primera linea (encabezado)
            // ciclo para leer el resto del archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // separa la linea usando como delimitador ";"
                String[] partes = linea.split(";");
                // asegura que la linea esta completa
                if (partes.length == 3) {
                    //se crea el obj paciente
                    lista.add(new Paciente(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        }
        return lista.toArray(new Paciente[0]);
    }

    //lee archivo csv inventario, convirtiendo a un array de obj Item
    public static Item[] leerInventario(String nombreArchivo) throws IOException {
        List<Item> lista = new ArrayList<>(); // lee y descarta la primera linea (encabezado)
        // asegura que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // leer y descartar encabezado
            while ((linea = br.readLine()) != null) {
                // separa la linea usando como delimitador ";"
                String[] partes = linea.split(";");
                // asegura que la linea esta completa
                if (partes.length == 3) {
                    //se crea el obj paciente
                    lista.add(new Item(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        }
        return lista.toArray(new Item[0]);
    }
}