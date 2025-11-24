package ed.u2.sorting;
// mdoelo de datyos para dataset inventario, se ordena alfabeticamente por stock
public class Item implements Comparable<Item> {
    public String id;
    public String insumo;
    public int stock;

    public Item(String id, String insumo, int stock) {
        this.id = id;
        this.insumo = insumo;
        this.stock = stock;
    }

    @Override
    public int compareTo(Item otro) {
        // Para ordenar stock (enteros)
        return Integer.compare(this.stock, otro.stock);
    }

    @Override
    public String toString() {
        return "Item{" + id + ", " + insumo + ", " + stock + "}";
    }
}