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
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Receiver_client {
     public static String filelocation= "E:\\bbb\\" ;
    public static int chunkSize = 256000 ;
    
    public static void main (String[]  args) throws Exception{
        
        try{
            Socket sr = new Socket ("localHost", 44552);
            InputStream is = sr.getInputStream();
            BufferedReader inFromSender = new BufferedReader(new InputStreamReader(is));
            
            Scanner input = new Scanner(System.in);
            
          //  System.out.println("Write address where you want to save the received file: ");
           // String address = input.next();
           // System.out.println("Write the name with which you want to save file: ");
           // String name = input.next();
           // System.out.println("Write the extension of the file: ");
          //  String extension = input.next();
        //    System.out.println("Write size of chunks in KB: ");
         //   int chunkSizeKB = input.nextInt();
         //   int chunkSize = chunkSizeKB * 1024; 
            
         
         String FileName = inFromSender.readLine();
         
          String fileAddress = filelocation + FileName;
            
            
            
            //filelocation = filelocation + FileName;
            
            String fileSizeS = inFromSender.readLine();
            
            int fileSize;
            fileSize = Integer.parseInt(fileSizeS);
            System.out.println("file size is: "+ fileSize);
            
            int totalChunks = fileSize/chunkSize;
            totalChunks = totalChunks + 1;
            
            FileOutputStream compFile = new FileOutputStream(fileAddress);
            byte fileF [] = new byte[fileSize];
            
                int c = 0;
            for(int i = 1; i<=totalChunks; i++){
                byte chunk[] = new byte[chunkSize];
                String chunkAddress = filelocation + FileName + "chunk " + i;
        
                FileOutputStream fr = new FileOutputStream(chunkAddress);
                is.read(chunk, 0, chunk.length);
                fr.write(chunk, 0, chunk.length);
                System.out.println("chunk "+i+" received");
                int a = 0, b = chunkSize;
               /* while(a < b){
                    fileF[c] = chunk[a];
                    a++;
                    c++;
                }*/
                
            }
            compFile.write(fileF, 0, fileF.length);
            
        
        }
        
        catch(IOException e){
            System.out.println(e);
        }
    
    
    }
    
    
}



