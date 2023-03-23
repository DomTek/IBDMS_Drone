/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ibdms_drone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import javax.net.ssl.HostnameVerifier;

/**
 *
 * @author H529780
 */
public class IBDMS_Drone {

    public static void main(String args[]) {

        Socket s = null;
        String hostName = "localhost";
        String message = "Hello from the Drone client";
        try {
            int serverPort = 8888;

            s = new Socket(hostName, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message);
            String data = in.readUTF();
            System.out.println("Message Recieved From Server: " + data);
        } catch (UnknownHostException e) {
            System.err.println("Sock: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF" + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO" + e.getMessage());
        }
        finally {
            if (s != null)
                try {
                s.close();
            } catch (IOException e) {
                System.out.println("close" + e.getMessage());
            }

        }
    }
}
