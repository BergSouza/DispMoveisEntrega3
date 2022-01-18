package com.example.entrega3.network;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.entrega3.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DeleteCarro extends Thread {
    MainActivity activity;
    DownloadCarros downloadCarros;
    TextView txtView;
    int idCarro;
    ProgressBar progressBar;

    public DeleteCarro(DownloadCarros downloadCarros, TextView txtView, MainActivity activity, int idCarro, ProgressBar progressBar){
        this.idCarro = idCarro;
    }

    public void run(){
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String stringURL = "http://192.168.0.114:9000/delete?id="+idCarro;

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
