/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;


/**
 *
 * @author Theo och Oskar
 */
public class Maze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Map m = new Map(3);
        Bot anna = new Bot(m);
        anna.pathFind();
        m.draw();
        if (anna.pathExist()) {
            while (anna.move()) {
                long drawtime = System.currentTimeMillis() + 800;
                while (System.currentTimeMillis() < drawtime) {
                }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                m.draw();
            }
            System.out.println("Winning!");
            return;
        }
        System.out.println("No path exists :(");
    }
    
}
