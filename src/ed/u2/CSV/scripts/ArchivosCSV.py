import csv
import random
from datetime import datetime, timedelta

# ==========================================
# CONFIGURACIÓN GENERAL
# ==========================================
SEED = 42
random.seed(SEED)  # [cite: 3, 5, 9] Semilla fija para reproducibilidad

# Pools de datos simulados
APELLIDOS_POOL_30 = [
    "Garcia", "Romero", "Silva", "Mendoza", "Vargas", "Castro", "Perez", "Rios", 
    "Izquierdo", "Beltran", "Chavez", "Guerrero", "Naranjo", "Cedeño", "Ortega", 
    "Acosta", "Flores", "Morales", "Espinosa", "Castillo", "Valencia", "Reyes", 
    "Jaramillo", "Vera", "Torres", "Suarez", "Cabrera", "Paez", "Leon", "Vega"
] # [cite: 2]

APELLIDOS_POOL_50 = APELLIDOS_POOL_30 + [
    "Andrade", "Bermeo", "Carpio", "Diaz", "Estrella", "Fajardo", "Gomez", "Hidalgo", 
    "Ibarra", "Jimenez", "Lopez", "Mena", "Narváez", "Orellana", "Pinto", "Quezada", 
    "Ramirez", "Salazar", "Tapia", "Ullauri"
] # [cite: 7]

INSUMOS_POOL = [
    "Guante Nitrilo Talla M", "Alcohol 70% 1L", "Gasas 10x10", "Jeringa 5ml", 
    "Mascarilla N95", "Suero Fisiologico", "Venda Elastica", "Paracetamol 500mg",
    "Bisturi #11", "Algodon Paquete", "Termometro Digital", "Cateter 18G"
] # [cite: 11]

# ==========================================
# FUNCIONES AUXILIARES
# ==========================================

def generar_fecha_laboral():
    """
    Genera fecha entre 2025-03-01 y 2025-03-31, de 08:00 a 18:00.
    [cite: 3]
    """
    start_date = datetime(2025, 3, 1)
    days_offset = random.randint(0, 30) # 31 días en marzo
    day = start_date + timedelta(days=days_offset)
    
    # Hora entre 08:00 y 18:00
    # Generamos hora entre 8 y 17, minutos 0-59 para asegurar estar en rango
    hour = random.randint(8, 17) 
    minute = random.randint(0, 59)
    
    final_dt = day.replace(hour=hour, minute=minute)
    return final_dt.strftime("%Y-%m-%dT%H:%M")

def escribir_csv(nombre_archivo, encabezados, datos):
    """
    Escribe el archivo asegurando UTF-8 y separador ;
    
    """
    print(f"Generando {nombre_archivo}...")
    with open(nombre_archivo, mode='w', encoding='utf-8', newline='') as f:
        writer = csv.writer(f, delimiter=';')
        writer.writerow(encabezados)
        writer.writerows(datos)

# ==========================================
# GENERACIÓN DE DATASETS
# ==========================================

# --- 1) citas_100.csv ---
datos_citas = []
for i in range(1, 101):
    id_cita = f"CITA-{i:03d}" # [cite: 1] Relleno 3 dígitos
    apellido = random.choice(APELLIDOS_POOL_30) # [cite: 2]
    fecha = generar_fecha_laboral() # [cite: 3]
    datos_citas.append([id_cita, apellido, fecha])

escribir_csv("citas_100.csv", ["id", "apellido", "fechaHora"], datos_citas)


# --- 2) citas_100_casi_ordenadas.csv ---
# Base: partir del archivo ordenado por fechaHora ascendente 
datos_ordenados = sorted(datos_citas, key=lambda x: x[2])

# Realizar exactamente 5 intercambios (5%) 
swaps_realizados = 0
pares_usados = set()
n = len(datos_ordenados)

while swaps_realizados < 5:
    idx1 = random.randint(0, n - 1)
    idx2 = random.randint(0, n - 1)
    
    if idx1 != idx2:
        pair = tuple(sorted((idx1, idx2)))
        if pair not in pares_usados:
            # Swap
            datos_ordenados[idx1], datos_ordenados[idx2] = datos_ordenados[idx2], datos_ordenados[idx1]
            pares_usados.add(pair)
            swaps_realizados += 1

escribir_csv("citas_100_casi_ordenadas.csv", ["id", "apellido", "fechaHora"], datos_ordenados)


# --- 3) pacientes_500.csv ---
# Distribución sesgada para apellidos (60% grupo A, 30% grupo B, 10% grupo C) 
grupo_A = APELLIDOS_POOL_50[:10]  # 10 apellidos muy comunes
grupo_B = APELLIDOS_POOL_50[10:30] # 20 apellidos medios
grupo_C = APELLIDOS_POOL_50[30:]   # 20 apellidos raros

population = grupo_A + grupo_B + grupo_C
# Ajustamos pesos para cada elemento individual según el grupo al que pertenece
weights = ([0.6/len(grupo_A)] * len(grupo_A)) + \
          ([0.3/len(grupo_B)] * len(grupo_B)) + \
          ([0.1/len(grupo_C)] * len(grupo_C))

datos_pacientes = []
for i in range(1, 501):
    id_pac = f"PAC-{i:04d}" # [cite: 7]
    apellido = random.choices(population, weights=weights, k=1)[0]
    prioridad = random.choice([1, 2, 3]) # [cite: 9]
    datos_pacientes.append([id_pac, apellido, prioridad])

escribir_csv("pacientes_500.csv", ["id", "apellido", "prioridad"], datos_pacientes)


# --- 4) inventario_500_inverso.csv ---
datos_inventario = []
for i in range(1, 501):
    id_item = f"ITEM-{i:04d}" # [cite: 11]
    insumo = random.choice(INSUMOS_POOL)
    # Stock estrictamente descendente: 500, 499... 1 [cite: 12]
    stock = 500 - (i - 1)
    datos_inventario.append([id_item, insumo, stock])

escribir_csv("inventario_500_inverso.csv", ["id", "insumo", "stock"], datos_inventario)

print("\n¡Proceso terminado! Los 4 archivos han sido creados.")