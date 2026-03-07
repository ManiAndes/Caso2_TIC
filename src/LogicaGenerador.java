
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogicaGenerador {

    public static void generadorArchivo(int nf1, int nc1, int nf2, int nc2, int tp, String nombreArchivo) {

        if (nc1 == nf2) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {

                bw.write("TP=" + tp);
                bw.newLine();
                bw.write("NF1=" + nf1);
                bw.newLine();
                bw.write("NC1=" + nc1);
                bw.newLine();
                bw.write("NF2=" + nf2);
                bw.newLine();
                bw.write("NC2=" + nc2);
                bw.newLine();

                // Offsets en Bytes entre matrices
                int inicioM2 = 4*nf1*nc1;
                int inicioM3 = inicioM2 + 4*nf2*nc2;

                // NR : Accesos de cada matriz --> 2 accesos para cada for de k y 1 acceso para cada for de j
                int nr = (nf1*nc1*nc2*2) + (nf1*nc2);
                bw.write("NR=" + nr);
                bw.newLine();

                // NP : con base en las dvs
                int bytesPosTotales = 4*((nf1*nc1) + (nf2*nc2) + (nf1*nc2));
                int npEnteras = bytesPosTotales / tp;

                int np = npEnteras;
                if (bytesPosTotales % tp != 0) {
                    np += 1;
                }

                bw.write("NP=" + np);
                bw.newLine();

                // i : Filas de M1
                for (int i = 0; i < nf1; i++) {
                        
                    // j : Columnas de M2
                    for (int j = 0; j < nc2; j++) {

                        // k : Columnas de M1 y Filas de M2 --> Hace el producto punto
                        for (int k = 0; k < nc1; k++) {

                            // M1[i][k] y M2[k][j]
                            int bytePosM1 = 4*nc1*i + 4*k;
                            int bytePosM2 = inicioM2 + 4*nc2*k + 4*j;

                            // Número de página y offset
                            int numPaginaM1 = bytePosM1 / tp;
                            int numOffsetM1 = bytePosM1 % tp;

                            int numPaginaM2 = bytePosM2 / tp;
                            int numOffsetM2 = bytePosM2 % tp;

                            // Insertar en archivo
                            String dirM1 = String.format("[M1-%d-%d], %d, %d", i, k, numPaginaM1, numOffsetM1);
                            String dirM2 = String.format("[M2-%d-%d], %d, %d", k, j, numPaginaM2, numOffsetM2);

                            bw.write(dirM1);
                            bw.newLine();
                            bw.write(dirM2);
                            bw.newLine();

                        }

                        // M3[i][j] --> Resultado del producto punto
                        int bytePosM3 = inicioM3 + 4*nc2*i + 4*j;

                        // Número de página y offset
                        int numPaginaM3 = bytePosM3 / tp;
                        int numOffsetM3 = bytePosM3 % tp;

                        // Insertar en archivo
                        String dirM3 = String.format("[M3-%d-%d], %d, %d", i, j, numPaginaM3, numOffsetM3);
                        bw.write(dirM3);
                        bw.newLine();

                    }
                }

                bw.close();

            } catch (IOException e) {

                System.out.println("Nombre de archivo inválido...");

            }

        } else {

            throw new java.lang.Error("NC1 y NF2 deben ser iguales...");

        }

    }

}
