/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author Nikhil Sharma
 */
   import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JavaApplication2 {
    public static void main(String[] args) throws IOException{
        String host;Scanner s=new Scanner(System.in);
        System.out.print("Enter host name:");
        host=s.next();
        Socket socket=new Socket(host,55286);
        InputStream is=socket.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String message = br.readLine();
        System.out.println("Message Received:"+message);
        socket.close();
        System.out.println("Ending server");
    }
    
}
