/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author jitor
 */
public class RemoteConnection {

//    private static RemoteConnection instance = null;
//
//    public static synchronized RemoteConnection getInstance(){
//        if(instance == null){
//            instance = new RemoteConnection();
//        }
//        return instance;
//    }
    public static String connectToServer(String metodo, String param) {

        String response = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("http://172.17.44.113:8000");
            System.out.println("URL: " + url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(metodo);

            if (metodo.equals("POST")) {

                urlConnection.setDoOutput(true);
                if (!param.equals("")) {
                    System.out.println("Entre a post");
                    urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    OutputStream out = urlConnection.getOutputStream();
                    writeStream(out, param);
                }
            }

            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(urlConnection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Se hace la desconexión del enlace
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
        // Se retorna el cuerpo del mensaje enviado por el webservice

        return response;
    }

    // Esta función se utiliza con el método POST y permite una vez abierta la conexión
    // enviar los parámetros en el cuerpo del mensaje
    // Recibe como parámetros la conexión y los parámetros que se van a enviar en formato String
    private static void writeStream(OutputStream out, String param) throws IOException {
        try (out) {
            // Se transmiten los datos por el enlace establecido
            System.out.println(param);
            out.write(param.getBytes());
            out.flush();
        }

    }

    // Esta función tiene como propósito recibir el flujo de datos
    // mensaje retornado por el webservice y convertirlo en un String
    // Recibe como parámetros la conexión y retorna el String con los datos
    private static String readStream(InputStream in) {
        System.out.println("ReadStrem" + in);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
