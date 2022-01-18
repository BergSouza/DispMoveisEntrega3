package com.example.entrega3.network;

import android.util.Log;

import com.example.entrega3.Carro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddCarro extends Thread{
    Carro carro;

    public AddCarro(Carro carro){
        this.carro = carro;
    }

    public void run(){
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String stringURL = "http://192.168.0.114:9000/add?id="+this.carro.getId()+"&marca="+this.carro.getMarca()+"&modelo="+this.carro.getModelo()+"&ano="+this.carro.getAno()+"&cor="+this.carro.getCor();
        //
        Log.d("ALO", stringURL);
        try{
            URL url = new URL( stringURL );

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String response = "";

            String inputLine;
            while((inputLine = in.readLine()) != null){
                response += inputLine;
            }
        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
