public class Main {
    
    public static void main(String[] args) throws Exception {
        
        int nf1 = Integer.parseInt(args[0]);
        int nc1 = Integer.parseInt(args[1]);
        int nf2 = Integer.parseInt(args[2]);
        int nc2 = Integer.parseInt(args[3]);

        int tp = Integer.parseInt(args[4]);
        String nombreArchivo = args[5];

        LogicaGenerador.generadorArchivo(nf1, nc1, nf2, nc2, tp, nombreArchivo);

    }

}