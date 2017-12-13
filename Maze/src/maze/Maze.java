/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.Scanner;


/**
 *
 * @author Theo och Oskar
 */
public class Maze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while (1 == 1) {
            int mapNr = 10;
            Scanner sc = new Scanner(System.in);
            while (mapNr < 0 || mapNr > 6) {
                System.out.println("Select a map 1-7: ");
                mapNr = sc.nextInt() - 1;
            }

            Map m = new Map(mapNr);     //Creates the world
            Bot anna = new Bot(m);  //Creates the bot and passes a reference to the bot
            anna.pathFind();        //Finds the closest path to the exits
            m.draw();
            if (anna.pathExist()) {
                while (anna.move()) { //Prints the path if it exists
                    long drawtime = System.currentTimeMillis() + 400;
                    while (System.currentTimeMillis() < drawtime) {
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    m.draw();
                }
                System.out.println("Winning!");

            }else
                System.out.println("No path exists :("); //No path exists
        }
    }
    
}
