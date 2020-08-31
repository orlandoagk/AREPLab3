package edu.escuelaing.arep.components;


import java.io.*;


public class DirectoryReader {

    String path;

    public DirectoryReader(String path){
        this.path = path;
    }

    public File imageReader(String file){

        return new File(this.path+file);


    }

    public String fileReader(String file) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path+file));
        String c;
        String objectString = "";

        while((c=buffer.readLine())!=null){

            objectString += (c + "\n");

        }
        return objectString;
    }

}
