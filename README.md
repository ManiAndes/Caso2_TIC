202423975 - Manuel Mejía
202423192 - Isabella Bracho 

## Generador de Archivos - DVs

Para generar el archivo con las direcciones virtuales se debe compilar ambos archivos dentro del `src` folder y ejecutar Main.java con los command-line arguments requeridos de la siguiente manera:

java Main <nf1> <nc1> <nf2> <nc2> <tp> <nombreArchivo>

nf1 : número de filas M1
nc1 : número de columnas M1
nf2 : número de filas M2
nc2 : número de columnas M2
tp : tamaño de página
nombreArchivo : nombre del archivo de salida

# Ejemplo

java Main 4 6 6 8 64 archivo_dv.txt