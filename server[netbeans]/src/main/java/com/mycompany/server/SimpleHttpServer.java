/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bergson
 */
class SimpleHttpServer {
    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException{
        httpExchange.sendResponseHeaders( 200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        System.out.println("Pré: "+query);
        for(String param : query.split("&")){
            String pair[] = param.split("=");
            if(pair.length>1){
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        System.out.println("Pós: "+result);
        return result;
    }
}
