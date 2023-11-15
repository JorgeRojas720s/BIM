/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author jitor
 */
public class ChildThread {

    String respuesta;
    Thread thread;

<<<<<<< HEAD
    public ChildThread(String table, String queryType,String data) {
        threadToServer(table,queryType,data);
    }

    public void threadToServer(String table, String queryType,String data) {
=======
    public ChildThread(String table, String queryType, String data) {
        threadToServeer(table, queryType, data);
    }

    public void threadToServeer(String table, String queryType, String data) {
>>>>>>> reds
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String param = table + "|" + queryType + "|" + data;
                respuesta = RemoteConnection.connectToServer("POST", param);
                System.out.println("Respuesta del servidor: " + respuesta);
                showAlert("There has been a problem", respuesta);
            }
        });
        thread.start();
//          waitThreadEnd();
    }

    public String getResponse() {
        return respuesta;
    }

    public void waitThreadEnd() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message, String response) {
        if ("nada".equals(response)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

}
