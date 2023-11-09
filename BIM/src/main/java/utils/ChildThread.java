/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author jitor
 */
public class ChildThread {

    String respuesta;
    Thread thread;

    public ChildThread(String data) {
        threadToServeer(data);
    }

    public void threadToServeer(String data) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String param = String.valueOf(data);
                respuesta = RemoteConnection.connectToServer("POST", param);
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        });
        thread.start();
        waitThreadEnd();
    }

    public String obtenerRespuesta() {
        return respuesta;
    }

    public void waitThreadEnd() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
