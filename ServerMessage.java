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
        InetAddress addr=InetAddress.getLocalHost();
        String hostName=addr.getHostName();
        System.out.println("Server Started and listening to port 55286:"+hostName+":");
        InetAddress addr2[]=InetAddress.getAllByName(hostName);
        
        System.out.println("Address is:- "+addr2[0].getHostAddress());
        Socket socket = server.accept();
        System.out.println(socket.getRemoteSocketAddress().toString());
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(s,0,s.length());
        bw.flush();
        socket.close();
        server.close();
    }
    
}
