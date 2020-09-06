/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypkj;

/**
 *
 * @author Uzair
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connection1 {
           public static void main(String[] args) {
      
        
        try{
            ServerSocket server = new ServerSocket(12343);
            while(true){
                Socket socket = server.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader read1 = new BufferedReader(inputStreamReader);
                
                String query = read1.readLine();
                 
                //String connectionURL="jdbc:derby://localhost:1527/DB"; 

                
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/user12","user12","user12");
                
                PreparedStatement statement = con.prepareStatement(query);
                
                statement.executeUpdate();
                
                con.close();
                socket.close();
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}


