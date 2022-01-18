package com.example.entrega3.network;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.entrega3.Carro;
import com.example.entrega3.MainActivity;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadCarros extends Thread{
    MainActivity activity;
    TextView txtView;
    ProgressBar progressBar;

    public DownloadCarros( MainActivity activity, TextView txtView, ProgressBar progressBar){
        this.activity = activity;
        this.txtView = txtView;
        this.progressBar = progressBar;
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void run(){
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String stringURL = "http://192.168.0.114:9000/get";
        //
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
            Gson gson = new Gson();
             new ArrayList<>();
            TypeToken<List<Carro>> token = new TypeToken<List<Carro>>() {};
            final ArrayList<Carro> arrayList = gson.fromJson(response, token.getType());

            Runnable runnable = new Runnable(){
                @Override
                public void run(){
                    activity.updateCarros(arrayList);
                }
            };
            this.progressBar.setVisibility(View.INVISIBLE);
            txtView.post( runnable );

        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
