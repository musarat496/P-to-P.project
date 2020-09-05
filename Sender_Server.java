
package myPakage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Sender_Server {
    public static void main (String[] args) throws Exception{
        try{
            ServerSocket s = new ServerSocket(4455);
            Socket sr = s.accept();
            System.out.println("*CONNECTED* ");
            
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the address from where you want to get file: ");
            String filePath = input.next();
            System.out.println("Enter the name of file you want to send: ");
            String fileName = input.next();
            System.out.println("Enter the extension of the file you are using: ");
            String extension = input.next();
            
            String fileAddress = filePath + fileName + "." + extension;
            System.out.println("File Address is: "+ fileAddress);
            
            File f = new File(fileAddress);
            double byt = f.length();
            int fileSize = (int) byt;
        if(f.exists()){
            
            System.out.println("Size of file is: "+ fileSize);
            
        }
        else{
            System.out.println("File does not exists");
        }
        
            System.out.println("Enter the chunk size in KB: ");
            int chunkSizeKB = input.nextInt();
            int chunkSize = chunkSizeKB * 1024;
            
            FileInputStream fis = new FileInputStream(fileAddress);
            OutputStream os = sr.getOutputStream();
            DataOutputStream outToServer = new DataOutputStream(sr.getOutputStream());
            
            int totalChunks = fileSize/chunkSize;
            totalChunks = totalChunks + 1;
            System.out.println("Total chhunks made: " + totalChunks);
            
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
}
