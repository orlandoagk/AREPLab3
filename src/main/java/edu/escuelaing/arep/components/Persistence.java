package edu.escuelaing.arep.components;



import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

public class Persistence {
    Connection conn = null;

    public Persistence() {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-91-178-234.compute-1.amazonaws.com:5432/dccdn9raofkums?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","biwenoxfofpijk","a94bf529cc3e5a97b345ec1101f1d6591fb5078425b3152ef8adb4de96a48736");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        String CREATE_TABLE_SQL="CREATE TABLE Message ("
                + "UID INT NOT NULL,"
                + "NAME VARCHAR(45) NOT NULL,"
                + "MESSAGE VARCHAR(45) NOT NULL,"
                + "PRIMARY KEY (UID))";

        try {
            Statement stmnt = conn.createStatement();
            stmnt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> selectAllMessages(){
        String ALLMessages="SELECT * FROM Message;";
        ArrayList<String[]> arregloADevolver = new ArrayList<>();
        try {

            Statement stmnt = conn.createStatement();
            ResultSet rs =stmnt.executeQuery(ALLMessages);
            while(rs.next()){

                String name = rs.getString("name");
                String message = rs.getString("message");
                String[] tmp = {name,message};
                arregloADevolver.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arregloADevolver;
    }

    public void insertIntoMessage(String user, String messageTMP){
        int newIndex = selectAllMessages().size();
        String InsertMessage="INSERT INTO Message(uid,name,message) VALUES ("+newIndex+",'"+user+"','"+messageTMP+"')";

        try {
            Statement stmnt = conn.createStatement();
            stmnt.execute(InsertMessage);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
