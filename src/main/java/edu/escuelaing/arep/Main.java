package edu.escuelaing.arep;
import edu.escuelaing.arep.components.MiSpark;
import edu.escuelaing.arep.components.WebServer;



public class Main {
    public static void main(String[] args) {
        MiSpark miSpark = new MiSpark();
        miSpark.get("/probandoapi",()->"Hola");
        WebServer server = new WebServer(miSpark);
        server.start();

    }
}
