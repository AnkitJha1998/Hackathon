package com.ankit.freeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.io.*;

/**
 * Created by Ankit on 3/23/2018.
 */

class ServerThread extends Thread
{
    File file;
    String fileName;
    TextView hostShow;
    public ServerThread(File f,String s,TextView t)
    {
        file=f;fileName=s;hostShow=t;

    }
    static void sendExtension(String fileName) throws IOException
    {
        ServerSocket server=new ServerSocket(55286);
        Socket socket=server.accept();
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(os));
        bw1.write(fileName,0,fileName.length());
        bw1.flush();
        socket.close();
        server.close();
    }
    public String getHostNames()
    {
        try {
            InetAddress in = InetAddress.getLocalHost();
            String host=in.getAddress().toString();
            Log.i("Hostname:","HostName");
            return host;

        }catch(UnknownHostException e)
        {
            Log.i("String::",e.toString());
            //Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
        }
        return "";
    }
    public void start()
    {

        hostShow.append(getHostNames());
        run();
    }
    @Override
    public void run()
    {   try{
        Log.i("Inside Thread","You are building server");
        sendExtension(fileName);
        ServerSocket server=new ServerSocket(55286);
        Socket socket=server.accept();
        OutputStream os=socket.getOutputStream();
        byte []byteArray = new byte[(int)file.length()];
        FileInputStream fin = new FileInputStream(file);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(byteArray,0,byteArray.length);
        os.write(byteArray,0,byteArray.length);
        os.flush();
        bin.close();
        socket.close();
    }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}

public class sendFileClass extends Activity {

    File file;
    String fileNames;
    boolean proceed=false;

    /*static void sendExtension(String hostName,String FileNames) throws IOException
    {
        ServerSocket server=new ServerSocket(55286);
        Socket socket=server.accept();
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(os));
        bw1.write(FileNames,0,FileNames.length());
        bw1.flush();
        socket.close();
        server.close();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_file_layout);

    }
    public void goBack(View view)
    {
        Intent goingBack=new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
    public void selection(View view)
    {
        EditText ed=(EditText)findViewById(R.id.fileName);
        file=new File((Environment.getExternalStorageDirectory())+ed.getText().toString());
        Toast.makeText(getApplicationContext(),file.getName().toString(),Toast.LENGTH_SHORT).show();
        proceed=true;
    }
    public String getHostNames()
    {
        try {
            InetAddress in = InetAddress.getLocalHost();
            String host=in.getHostName();
            return host;

        }catch(UnknownHostException e)
        {
            Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
        }
        return "";
    }
    public void sendData(View view)
    {
        if(proceed==false)
        {
            Toast.makeText(getApplicationContext(), "Select a file first", Toast.LENGTH_SHORT).show();

        }
        else
        {
            try {
                TextView hostView =(TextView)findViewById(R.id.HostShow);
                TextView portShow = (TextView) findViewById(R.id.portShow);
                Thread myThread=new Thread(new ServerThread(file,fileNames,hostView));

                portShow.append("55286");
                myThread.start();
                Toast.makeText(getApplicationContext(), "File Transferred", Toast.LENGTH_SHORT).show();
                //hostView.setText("Host Name to connect to : ");
                //portShow.setText("Port: ");
                //file = null;
                //fileNames = "";
                //proceed = false;
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }


        }
    }

}
