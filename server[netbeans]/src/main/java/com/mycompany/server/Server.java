package com.mycompany.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Server {
    
    public static ArrayList<Carro> arr = new ArrayList<>();
    HttpServer server;
    
    public static void main(String[] args) {
       HttpServer server;
       try{
           server = HttpServer.create( new InetSocketAddress(9000), 0);
           server.createContext("/info", new InfoHandler() );
           server.createContext("/get", new GetHandler() );
           server.createContext("/delete", new DeleteHandler() );
           server.createContext("/add", new AddHandler() );
           server.createContext("/edit", new EditHandler() );
           //server.createContext("/", new Handler() );
           
           server.setExecutor(null);
           
           server.start();
           
           System.out.println("SERVIDOR ON");
           
           Carro car1 = new Carro("Toyota","Supra",2004,"Amarelo");
           car1.setId(1);
           Carro car2 = new Carro("Nissan","Skyline",2003,"Azul");
           car2.setId(2);
           Carro car3 = new Carro("Ford","Mustang",2013,"Vermelho");
           car3.setId(3);
           Carro car4 = new Carro("Audi","TT",2004,"Branco");
           car4.setId(4);
           Carro car5 = new Carro("Chevrolet","Corvette",2021,"Laranja");
           car5.setId(5);
           Carro car6 = new Carro("Mitsubishi","Lancer Evolution XIII",2009,"Preto");
           car6.setId(6);
           
           arr.add(car1);
           arr.add(car2);
           arr.add(car3);
           arr.add(car4);
           arr.add(car5);
           arr.add(car6);
       }catch(Exception ex){
           
       } 
       
    }
    
    
    static class InfoHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            //throw new UnsupportedOperationException("Not supported yet.");
            String response = "asodmaosdmoasmd";
            SimpleHttpServer.writeResponse(httpExchange, response);
        }
    }
    static class GetHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            String response = get();
            SimpleHttpServer.writeResponse(httpExchange, response);
        }
    }
    
    static class AddHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            String query = httpExchange.getRequestURI().getQuery();
            Map <String,String> parms = SimpleHttpServer.queryToMap( query );
            Carro carro = new Carro(parms.get("marca"),parms.get("modelo"),Integer.parseInt(parms.get("ano")),parms.get("cor"));
            carro.setId(Integer.parseInt(parms.get("id")));
            arr.add(carro);
            System.out.println(arr);
        }
    }
    
    static class GetByIdHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            String query = httpExchange.getRequestURI().getQuery();
            Map <String,String> parms = SimpleHttpServer.queryToMap( query );
            ArrayList<Carro> arrAux = new ArrayList<>();
            for(int o = 0; o < arr.size();o++){
                if(parms.get("id").equals(Integer.toString(arr.get(o).getId()))){
                    arrAux.add(arr.get(o));
                }
            }
            StringBuilder response = new StringBuilder();
            Gson gson = new Gson();
            response.append(gson.toJson(arrAux));
            System.out.println(arrAux);
        }
    }
    
    static class EditHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            String query = httpExchange.getRequestURI().getQuery();
            Map <String,String> parms = SimpleHttpServer.queryToMap( query );
            ArrayList<Carro> arrAux = new ArrayList<>();
            for(int o = 0; o < arr.size();o++){
                if(parms.get("id").equals(Integer.toString(arr.get(o).getId()))){
                    arr.get(o).setMarca(parms.get("marca"));
                    arr.get(o).setModelo(parms.get("modelo"));
                    arr.get(o).setAno(Integer.parseInt(parms.get("ano")));
                    arr.get(o).setCor(parms.get("cor"));
                }
            }
        }
    }
    
    static class DeleteHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException{
            String query = httpExchange.getRequestURI().getQuery();
            Map <String,String> parms = SimpleHttpServer.queryToMap( query );
            ArrayList<Carro> arrAux = new ArrayList<>();
            for(int o = 0; o < arr.size();o++){
                if(parms.get("id").equals(Integer.toString(arr.get(o).getId()))){
                    arr.remove(o);
                }
            }
        }
    }
    
    private static ArrayList<Carro> banco(){
        ArrayList<Carro> arr = new ArrayList<>();
        return arr;
    }
    private static String get(){
        StringBuilder response = new StringBuilder();
        Gson gson = new Gson();
        response.append(gson.toJson(arr));
        System.out.println(arr);
        return response.toString();
    }
}
