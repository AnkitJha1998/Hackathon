/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermessage;

/**
 *
 * @author Ankit
 */
import java.util.Scanner;
import java.net.*;
import java.io.*;
public class ServerMessage {
    
        
    public static void main(String[] args) throws IOException{
        String s="abcd.fil";
        ServerSocket server=new ServerSocket(55286);
        //server.setHostName
        System.out.println("Server Started and listening to port 55286");
        InetAddress address=InetAddress.getByName("localhost");
        System.out.println("Address is:- "+address.toString());
        Socket socket = server.accept();
        InputStream is=socket.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(s);
        System.out.println("Message Sent");
        bw.flush();
        socket.close();
        server.close();
    }
    
}
