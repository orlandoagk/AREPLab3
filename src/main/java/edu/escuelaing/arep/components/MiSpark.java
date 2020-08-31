package edu.escuelaing.arep.components;

import com.sun.media.jfxmedia.logging.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static com.sun.deploy.cache.Cache.copyStream;

public class MiSpark {
    private Map<String, ArrowFunction> endPoints = new HashMap<>();

    public void getStaticResource(String resource, PrintWriter out, Socket clientSocket) throws IOException {
        DirectoryReader directoryReader = new DirectoryReader("src/main/resources");

        String outputLine = "HTTP/1.1 200 OK\r\n";
        boolean image = false;
        if(resource.contains(".html") || resource.equals("/")){
            resource = "/index.html";
            outputLine+= ("Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n");
        } else if(resource.contains(".js")){
            outputLine+= ("Content-Type: text/javascript\r\n"
                    + "\r\n");
        } else if(resource.contains(".css")){
            outputLine+= ("Content-Type: text/css\r\n"
                    + "\r\n");
        } else if(resource.contains(".jpg")){
            outputLine+= ("Content-Type: image/jpg\r\n"
                    + "accept-ranges: bytes\r\n" + "\r\n");
            image = true;
        }
        try {
            if(!image) {
                outputLine += directoryReader.fileReader(resource);
                out.println(outputLine);
            } else {
                FileInputStream inst=new FileInputStream(directoryReader.imageReader(resource));
                copyStream(inst,clientSocket.getOutputStream());
            }
        } catch (FileNotFoundException ex){
            System.out.println("No se encontró el recurso "+resource);
            outputLine = "HTTP/1.1 404 NotFound\r\n\r\n"+ "<!DOCTYPE html>\n";
            outputLine+=outputLine += directoryReader.fileReader("/NotFound.html");
            out.println(outputLine);
        }
    }

    public void get(String resource,ArrowFunction arrowFunction) {
        if(endPoints.get(resource) == null) {
            endPoints.put(resource, arrowFunction);
        } else {
            Logger.logMsg(0,"Ya existe una API ofrecida con ese recurso");
        }
    }

    public void getAPIResource(String resource, PrintWriter out) throws IOException {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n";

        if(endPoints.get(resource)==null){
            System.out.println("No se encontró el recurso "+resource);
            outputLine = "HTTP/1.1 404 NotFound\r\n\r\n"+ "<!DOCTYPE html>\n";
            DirectoryReader directoryReader = new DirectoryReader("src/main/resources");
            outputLine+=outputLine += directoryReader.fileReader("/NotFound.html");
            out.println(outputLine);
        } else {
            outputLine += endPoints.get(resource).call();
            out.println(outputLine);
        }
    }
}
