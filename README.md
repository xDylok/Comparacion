# Taller 6 - Comparacion de Ordenacion

---

Ejecutar y analizar comparativamente los algoritmos de Burbuja, Selección e Inserción sobre casos de prueba, 
para determinar cuándo conviene cada uno en función de tamaño, grado de orden y duplicados.


**Autores:**
* **Vasquez Calderon Jostin Xavier**
* **Maldonado Machuca Martina Alejandra**

---
## Ejecucion del proyecto:

1. Clonar el repositorio.
2. Abrir el proyecto en un IDE.
3. **Verificar los DataSets:** En la carpeta `src/ed/u2/CSV/` se debe encontrar los archivos `.csv` generados.
4. **Ejecutar:**
   * **Ubicar la clase principal:** `src/ed/u2/sorting/SortingExperiment.java`.
   * Ejecutar el metodo `main`.
   * La consola mostrara una tabla con los resultados.

---
## Desiciones de diseño:

* **Genericos (`<T extends Comparable<T>>`):** Los algoritmos fueron refactorizados para ordenar cualquier tipo de 
objeto (no solo enteros), lo que permite el uso de modelos como `Cita`, `Paciente` e `Item`.
* **Aislamiento de I/O:** La lectura de los archivos CSV (`ArchivosCSV.java`) se realiza *antes* de iniciar el 
cronometro. Se pasan copias (`Arrays.copyOf`) a los algoritmos para evitar ordenar datos ya ordenados.
* **Instrumentacion sin Trazas:** Se creo la clase `SortContadores` (DTO) para retornar `tiempo`, `comparaciones` 
y `swaps` sin imprimir en consola durante la ejecucion, evitando fallos en la medicion del tiempo.
* **Metodologia Robusta:** Cada prueba se ejecuta **10 veces ($R=10$)**. Se descartan las 3 primeras ejecuciones 
(warm-up de la JVM) y se reporta la **mediana** de las 7 restantes.
* **Generacion de Datos:** Se incluye el script de Python (`scripts/ArchivosCSV.py`) utilizado para generar los 
datasets con semilla fija (42).

---
## Resultados;
| Algoritmo | Dataset (Tipo) | N | Comparaciones | Swaps | Tiempo (ns) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **BubbleSort** | Citas_100 (Aleatorio) | 100 | 4,922 | 2,148 | 257,800 |
| **SelectionSort** | Citas_100 (Aleatorio) | 100 | 4,950 | 94 | 167,700 |
| **InsertionSort** | Citas_100 (Aleatorio) | 100 | 2,245 | 2,148 | 93,400 |
---
| Algoritmo | Dataset (Tipo) | N | Comparaciones | Swaps | Tiempo (ns) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **BubbleSort** | Citas_CasiOrd (Casi Ord) | 100 | 4,170 | 243 | 64,900 |
| **SelectionSort** | Citas_CasiOrd (Casi Ord) | 100 | 4,950 | 5 | 56,300 |
| **InsertionSort** | Citas_CasiOrd (Casi Ord) | 100 | **342** | 243 | **17,900** |
---
| Algoritmo | Dataset (Tipo) | N | Comparaciones | Swaps | Tiempo (ns) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **BubbleSort** | Pacientes_500 (Duplicados) | 500 | 124,579 | 60,337 | 2,223,100 |
| **SelectionSort** | Pacientes_500 (Duplicados) | 500 | 124,750 | **490** | 1,081,500 |
| **InsertionSort** | Pacientes_500 (Duplicados) | 500 | 60,829 | 60,337 | 660,700 |
---
| Algoritmo | Dataset (Tipo) | N | Comparaciones | Swaps | Tiempo (ns) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **BubbleSort** | Inventario_Inv (Inverso) | 500 | 124,750 | 124,750 | 743,900 |
| **SelectionSort** | Inventario_Inv (Inverso) | 500 | 124,750 | 250 | 510,400 |
| **InsertionSort** | Inventario_Inv (Inverso) | 500 | 124,750 | 124,750 | 5,433,800 |