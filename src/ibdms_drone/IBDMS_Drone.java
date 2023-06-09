package ibdms_drone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IBDMS_Drone {

    public static void main(String args[]) {

        Socket s = null;
        String hostName = "localhost";
        String message = "New Drone";
        try {
            int serverPort = 7896;

            s = new Socket(hostName, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message);
            out.flush();
            String data = in.readUTF();
            System.out.println("Message Received From Server: " + data);

            boolean keepRunning = true;
            while (keepRunning) {
                data = in.readUTF();
                if ("shutdown".equalsIgnoreCase(data)) {
                    
                    keepRunning = false;
                    
                    // to be exectuted is recall button is pressed
                } else if("return".equalsIgnoreCase(data)){
                    out.writeUTF("Returning Home");
                    out.flush();
                    keepRunning = false;
                    
                } else {
                    System.out.println("Message Received From Server: " + data);
                    String name = data.substring("Drone Name: ".length());
                    int ID = in.readInt();
                    int posX = in.readInt();
                    int posY = in.readInt();

                    // creates the drone using the drone class constructor
                    Drone drone = new Drone(ID, name, posX, posY);
                    // print out for debugging
                    System.out.println("Drone created with ID: " + ID + ", Name: " + name + ", Position: (" + posX + ", " + posY + ")");

                    // Change the drone's position every second, update it, and send the updated position to the server
                    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.scheduleAtFixedRate(() -> {
                        drone.updatePosition();
                        drone.sendUpdatedPosition(out);
                    }, 0, 1, TimeUnit.SECONDS);
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Sock: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF" + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO" + e.getMessage());
        } finally {
            if (s != null)
                try {
                s.close();
            } catch (IOException e) {
                System.out.println("close" + e.getMessage());
            }
        }

    }

}
