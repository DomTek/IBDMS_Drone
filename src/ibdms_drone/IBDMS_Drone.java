/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ibdms_drone;

import java.util.Scanner;

/**
 *
 * @author H529780
 */
public class IBDMS_Drone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Creates scanner to take input of the System by the user and accept an ID and name for the drone
        Scanner inScan = new Scanner(System.in);
        System.out.println("please enter the Drone ID: ");
        int droneId = inScan.nextInt();
        System.out.println("Please enter a name for the Drone: ");
        String droneName = inScan.nextLine();
        
        
        Drone[] drone = new Drone[1];
        Drone drones1 = new Drone(droneId, droneName, 0,0 );
        drone[0] = drones1;
        
    }
    
}
