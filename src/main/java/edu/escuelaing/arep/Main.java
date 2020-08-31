package edu.escuelaing.arep;

import com.sun.media.jfxmedia.logging.Logger;
import edu.escuelaing.arep.components.DirectoryReader;
import edu.escuelaing.arep.components.MiSpark;
import edu.escuelaing.arep.components.WebServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

import static com.sun.deploy.cache.Cache.copyStream;

public class Main {
    public static void main(String[] args) throws IOException {
        MiSpark miSpark = new MiSpark();
        miSpark.get("/probandoapi",()->"Hola");
        WebServer server = new WebServer(miSpark);
        server.start();

    }
}
