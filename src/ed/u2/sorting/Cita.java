package ed.u2.sorting;
// mdoelo de datyos para dataset citas medicas, se ordena cronologicamente por fechaHora
import java.time.LocalDateTime;

public class Cita implements Comparable<Cita> {
    public String id;
    public String apellido;
    public LocalDateTime fechaHora;

    public Cita(String id, String apellido, LocalDateTime fechaHora) {
        this.id = id;
        this.apellido = apellido;
        this.fechaHora = fechaHora;
    }

    @Override
    public int compareTo(Cita otra) {
        return this.fechaHora.compareTo(otra.fechaHora);
    }

    @Override
    public String toString() {
        return "Cita{" + id + ", " + apellido + ", " + fechaHora + "}";
    }
}