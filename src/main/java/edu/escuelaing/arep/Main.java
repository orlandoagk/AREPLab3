package edu.escuelaing.arep;
import edu.escuelaing.arep.components.MiSpark;
import edu.escuelaing.arep.components.Persistence;
import edu.escuelaing.arep.components.WebServer;

import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws UnknownHostException, URISyntaxException, SQLException {
        Persistence persistence = new Persistence();

        MiSpark miSpark = new MiSpark(persistence);
        miSpark.get("/probandoapi",(response)->"Hola");
        miSpark.get("/getBDMessage",(response)->{
            ArrayList<String[]> arregloMensajes = persistence.selectAllMessages();
            String stringMensajes = "Usuario               Mensaje\n";
            for(int i = 0; i<arregloMensajes.size();i++){
                stringMensajes+= (arregloMensajes.get(i)[0]+"      "+arregloMensajes.get(i)[1]+"-\n");
            }
            System.out.println(stringMensajes);
            return stringMensajes;
        });
        miSpark.get("/insertIntoDB",(response)->{
            persistence.insertIntoMessage(response.getQueryString("user"),response.getQueryString("message"));
            return "Is inserted please look in /API/getBDMessage";
        });
        WebServer server = new WebServer(miSpark);
        server.start();

    }
}
