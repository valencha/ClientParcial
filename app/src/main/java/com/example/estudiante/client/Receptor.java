package com.example.estudiante.client;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receptor extends Thread {


    Socket s;
    onMessage observer;




    public Receptor(Socket s){
    this.s=s;


    }


    @Override
    public void run() {

        try {

           InputStream is = s.getInputStream() ;

            BufferedReader reader= new BufferedReader(new InputStreamReader(is));


            while (true) {
                String line = reader.readLine();
                Log.e("debut","Conecto");

                observer.onReceived(line);

            }





        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public interface onMessage{
        public void onReceived (String mensaje);


    }

    public void setObserver(onMessage observer){
        this.observer=observer;
    }



}
