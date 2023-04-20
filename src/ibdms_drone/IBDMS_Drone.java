package ibdms_drone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class IBDMS_Drone {

    public static void main(String args[]) {

        int ID;
        String name;
        int posX;
        int posY;

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
                } else {
                    System.out.println("Message Received From Server: " + data);
                    if (data.startsWith("Drone Name: ")) {
                        name = data.substring("Drone Name: ".length());
                        ID = in.readInt();
                        posX = in.readInt();
                        posY = in.readInt();

                        // creates the drone using the drone class constructer
                        Drone drone = new Drone(ID, name, posX, posY);

                    }
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
