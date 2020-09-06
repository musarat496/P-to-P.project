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

public class Sender_Server {
        public static String filelocation= "F:\\aaa\\" ;
        public static int chunkSize = 256000 ;

    public static void main (String[] args) throws Exception{
        Sender_Server sc = new Sender_Server();
        sc.change();
        String info;
        try{
            ServerSocket s = new ServerSocket(44552);
            Socket sr = s.accept();
            System.out.println("*CONNECTED* ");
            
            Scanner input = new Scanner(System.in);
           /* System.out.println("Enter the address from where you want to get file: ");
            String filePath = input.next();
            System.out.println("Enter the name of file you want to send: ");
            String fileName = input.next();
            System.out.println("Enter the extension of the file you are using: ");
            String extension = input.next();
            
           String fileAddress = filePath + fileName + "." + extension;
            System.out.println("File Address is: "+ fileAddress); */
            System.out.println("Enter the name and extension of the file you are using: ");
            String extension = input.nextLine();
            
            filelocation = filelocation + extension;
            
            File f = new File(filelocation);
            double byt = f.length();
            int fileSize = (int) byt;
        if(f.exists()){
            
            System.out.println("Size of file is: "+ fileSize);
            
        }
        else{
            System.out.println("File does not exists");
        }
        
            /*System.out.println("Enter the chunk size in KB: ");
            int chunkSizeKB = input.nextInt(); */
            
            
            FileInputStream fis = new FileInputStream(filelocation);
            OutputStream os = sr.getOutputStream();
            DataOutputStream outToServer = new DataOutputStream(sr.getOutputStream());
            
            int totalChunks = fileSize/chunkSize;
            totalChunks = totalChunks + 1;
            System.out.println("Total chhunks made: " + totalChunks);
            
            info = f.getName();
            
            outToServer.writeBytes(info + "\n");
            
            
            
            String fileSizeS = Integer.toString(fileSize);
            outToServer.writeBytes(fileSizeS + "\n");
            
            byte compFile[] = new byte[fileSize];
            fis.read(compFile, 0, compFile.length);
            
                int c = 0;
            for(int i = 1; i<= totalChunks; i++){
                byte chunkArray[] = new byte[chunkSize];
                int a = 0, b = chunkSize;
                while(a<b){
                    chunkArray[a] = compFile[c];
                    a++;
                    c++;
                }
                
                os.write(chunkArray, 0, chunkArray.length);
                System.out.println("Chunk " + i + " sent");
                //chunkArray = null;
            }
            
            
    }
        catch(IOException e){
            System.out.println (e);
        }
}
    public void change (){
        try{
            System.out.println(filelocation);
            File fl = new File(filelocation);
            File [] filelists = fl.listFiles();
            for (int i=0; i< filelists.length; i++){
                System.out.println(i);
                Socket s = new Socket("localhost",12343);
               //Socket sr = s.accept();
                DataOutputStream outtodb = new  DataOutputStream(s.getOutputStream());
                int filesize = (int)filelists[i].length();
                int chunks = filesize/chunkSize;
                if(filesize%chunkSize>0){
                    chunks++;
                    
                }
        
                 String query="insert into DB(IP_ADDRESS,FILE_NAME,PARTS,CHUNKS) values (\'"
                +InetAddress.getLocalHost().getHostAddress()+"\',\'"+filelists[i].getName()+"\',"+filesize+","+chunks+")";

                 outtodb.writeBytes(query + "\n");
                
                s.close();
         
                
             
            }
            
        } catch ( IOException e){
            e.printStackTrace();
        }
    }
}

