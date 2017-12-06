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

        Map m = new Map();
        m.draw();
        Bot anna = new Bot(m);
        anna.pathFind();
        /*m.draw();
        m.moveUp();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveUp();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveRight();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveRight();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveRight();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveRight();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveLeft();
        System.out.println("\n\n\n\n\n");
        m.draw();
        m.moveLeft();
        System.out.println("\n\n\n\n\n");
        m.draw();*/
    }
    
}
