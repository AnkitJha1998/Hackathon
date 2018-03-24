package com.ankit.freeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
/*class ServerSend extends Activity
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
    static void ServerBegin(File file,String fileNames) throws IOException
    {
        InetAddress addr=InetAddress.getLocalHost();
        String hostName=addr.getHostName();
        sendFileClass.setEditText(hostName);
        sendExtension(fileNames);
        ServerSocket serverSocket=new ServerSocket(55286);

    }
}*/
public class sendFileClass extends Activity {

    File file;
    String fileNames;
    boolean proceed=false;

    static void sendExtension(String hostName,String FileNames) throws IOException
    {
        ServerSocket server=new ServerSocket(55286);
        Socket socket=server.accept();
        OutputStream os=socket.getOutputStream();
        BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(os));
        bw1.write(FileNames,0,FileNames.length());
        bw1.flush();
        socket.close();
        server.close();
    }
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
        EditText fileName=(EditText)findViewById(R.id.fileName);
        file=new File(Environment.getExternalStorageDirectory(),fileName.toString());

        fileNames=fileName.toString();
        Toast.makeText(getApplicationContext(),file.getName(),Toast.LENGTH_SHORT).show();
        proceed=true;
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
                InetAddress addr = InetAddress.getLocalHost();
                String HostName=addr.getHostName();
                TextView hostShow=(TextView)findViewById(R.id.HostShow);
                hostShow.append(HostName);
                TextView portShow = (TextView)findViewById(R.id.portShow);
                portShow.append("55286");
                sendExtension(HostName,fileNames);
                ServerSocket server=new ServerSocket(55286);
                Socket socket=server.accept();
                OutputStream os=socket.getOutputStream();
                byte []byteArray = new byte[(int)file.length()];
                FileInputStream fin = new FileInputStream(file);
                BufferedInputStream bin = new BufferedInputStream(fin);
                bin.read(byteArray,0,byteArray.length);
                os.write(byteArray,0,byteArray.length);
                os.flush();
                Toast.makeText(getApplicationContext(),"File Transferred",Toast.LENGTH_SHORT).show();
                hostShow.setText("Host Name to connect to : ");
                portShow.setText("Port: ");
                file=null;
                fileNames="";
                proceed=false;
            }
            catch(Exception e)
            {
                Toast.makeText(this, "Error Occured:"+e, Toast.LENGTH_SHORT).show();
            }


        }
    }

}
