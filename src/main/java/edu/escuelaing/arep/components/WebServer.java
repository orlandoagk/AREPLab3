package edu.escuelaing.arep.components;

import com.sun.net.httpserver.HttpServer;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;


public class WebServer {
    private int port = 36000;
    private boolean isRunning = true;
    private MiSpark spark = null;


    public WebServer() {

    }
    public WebServer(MiSpark miSpark) {
        this.spark = miSpark;
    }
    public WebServer(int port, MiSpark spark)  {
        this.port = port;
        this.spark = spark;
    }

    public int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; // returns default port if heroku-port isn't set(i.e. on localhost)
    }
    public void start(){
        try{
            port = getPort();
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + port);
                System.exit(1);
            }
            while(isRunning){
                try {
                    Socket clientSocket = null;
                    try {
                        System.out.println("Listo para recibir en puerto 36000 ...");
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }
                    processRequest(clientSocket);
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }
    }

    public void processRequest(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        boolean resourceInLine = true;
        String resource = "";
        while ((inputLine = in.readLine()) != null) {
            if(resourceInLine) {
                resource = inputLine.split(" ")[1];
                resourceInLine = false;
            }
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
        createResponse(resource,out,clientSocket);
    }

    private void createResponse(String resource, PrintWriter out, Socket clientSocket) throws IOException {
        if (resource.startsWith("/API")) {
            String appuri = resource.substring(4);
            spark.getAPIResource(appuri, out);
        } else {
            spark.getStaticResource(resource, out,clientSocket);
        }
        out.close();
    }


}
