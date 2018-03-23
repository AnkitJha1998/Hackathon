/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmessage;

/**
 *
 * @author Ankit
 */
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClientMessage{
    public static void main(String[] args) throws IOException{
        String host;
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the ip address of the sender:");
        host=s.next();
        boolean stat=false;
        InetAddress addr;
        while(true){
        try {
            addr=InetAddress.getByName(host);
            break;
        } catch (UnknownHostException ex) {
            System.out.print("Enter Valid database");
            stat=false;
        }}
        Socket socket=new Socket(addr,10000);
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        String message=br.readLine();
        System.out.println("Message from server:"+message);
        socket.close();
        System.out.println("Ending server");
    }
    
}
