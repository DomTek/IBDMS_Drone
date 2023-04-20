package ibdms_drone;

import java.io.*;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nighthawk
 */
public class Drone implements Serializable{
    
   private int ID;
   private String name;
   private int posX;
   private int posY;    

    public Drone(int ID, String name, int posX, int posY) {
        this.ID = ID;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

   
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    //Methode to use with a time to randomly update the X and Y positioning.
    public void updatePosition() {
        Random random = new Random();
        
        //This will generate randome moves of the drones and can be adjusted
        posX += random.nextInt(3) - 1;
        posY += random.nextInt(3) - 1;
        //Dprint out for debuging 
        System.out.println("Drone ID: " + ID + " Updated Position: (" + posX + ", " + posY + ")");
    }
    public void sendUpdatedPosition(DataOutputStream out) {
        try {
            out.writeUTF("Update Position");
            out.writeInt(ID);
            out.writeInt(posX);
            out.writeInt(posY);
            out.flush();
            // debiging code
            System.out.println("Drone ID: " + ID + " Sent updated position to server: (" + posX + ", " + posY + ")");
        } catch (IOException e) {
            System.err.println("Error sending updated position to server: " + e.getMessage());
        }
    }
}
