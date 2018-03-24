/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientcombine;

/**
 *
 * @author Ankit
 */
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class Server
{
    static void sendExtension(String s) throws IOException
    {
        ServerSocket server=new ServerSocket(55286);
        Socket socket=server.accept();
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(os));
        bw1.write(s,0,s.length());
        bw1.flush();
        socket.close();
        server.close();
        
    }
    static void ServerBegin(Scanner sc) throws IOException
    {
        String s="";
        String path;
        //Scanner sc=new Scanner(System.in);
        System.out.println("While entering path make sure you enter \\ with \\\\");
        System.out.print("Enter the path of the file you want to send:");
        path=sc.next();
        for(int i=path.length()-1;i>=0;i--)
        {
            if(path.charAt(i)=='\\'){
                s=path.substring(i+1,path.length());
                break;
            }
        }
        s+="\n";
        //System.out.println(s);
        InetAddress addr=InetAddress.getLocalHost();
        String hostName=addr.getHostName();
        System.out.println("Server Started and listening to port 55286:");
        System.out.println("Host Name to connect: "+hostName);
        sendExtension(s);
        ServerSocket server=new ServerSocket(55286);
        //server.setHostName
        
        
        //System.out.println("Enter the path ");
        
        
        Socket socket = server.accept();
        //System.out.println(socket.getRemoteSocketAddress().toString());
        /*OutputStream os=socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(s,0,s.length());
        bw.flush();
        socket.close();
        server.close();*/
        OutputStream os=socket.getOutputStream();
        File transferFile = new File(path);
        byte []byteArray = new byte[(int)transferFile.length()];
        FileInputStream fin = new FileInputStream(transferFile);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(byteArray,0,byteArray.length);
        //OutputStream os=socket.getOutputStream();
        System.out.println("Sending Files!!");
        os.write(byteArray,0,byteArray.length);
        os.flush();
        System.out.println("File Transferred!!!!");
        System.out.println("Type exit to continue");
        String temp=sc.next();
        socket.close();
    }
}
class Client
{
    static String fileExtReceiver(String host) throws IOException
    {
        Socket socket=new Socket(host,55286);
        String FileExtension="";
        InputStream i1=socket.getInputStream();
        BufferedReader br1=new BufferedReader(new InputStreamReader(i1));
        FileExtension=br1.readLine();
        br1.close();
        socket.close();
        return FileExtension;
        
    }
    static void clientBegin(Scanner s) throws IOException
    {
        int filesize=1022386;
        int bytesRead;
        int currentTot=0;
        /*String host;
        Socket socket=new Socket("192.168.43.74",10000);
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("file.fil");
        System.out.println("Message Sent");
        System.out.println("Ending server");*/
        String host;//Scanner s=new Scanner(System.in);
        String fileExt="";
        System.out.print("Enter host name:");
        host=s.next();
        fileExt=fileExtReceiver(host);
        Socket socket=new Socket(host,55286);
        InputStream is=socket.getInputStream();
        BufferedReader br1=new BufferedReader(new InputStreamReader(is));
        //String fileExt=br1.readLine();
        //System.out.println(fileExt+"\n");
        //br1.close();
        String path="C:\\JavaFiles\\"+fileExt;
        byte [] bytearray = new byte[filesize];
        //InputStream is=socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(path);
        BufferedOutputStream bos =new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray,0,bytearray.length);
        currentTot=bytesRead;
        do{
            bytesRead=is.read(bytearray,currentTot,(bytearray.length-currentTot));
            if(bytesRead>=0)currentTot+=bytesRead;    
        } while(bytesRead>-1);
        bos.write(bytearray,0,currentTot-1);
        bos.flush();
        bos.close();
        socket.close();
        System.out.println("Type exit to continue");
        String temp=s.next();
    }
}
public class ServerClientCombine {

   
    public static void main(String[] args) throws IOException{
        int i=0;
        Scanner s=new Scanner(System.in);
        System.out.println("Before using this exe, be Sure to create a folder named JavaFiles in C drive. After that only, proceed with sending/receiving. Else there will be exceptions");
        while(i!=3)
        {
            System.out.print("1. Send\n2. Receive\n3. Exit\n\nChoose: ");
            i=s.nextInt();
            switch(i)
            {
                case 1: Server.ServerBegin(s);
                        break;
                case 2: Client.clientBegin(s);
                        break;
                case 3: System.out.print("Bye!!");
                        return;
            }
        }
    }
    
}
