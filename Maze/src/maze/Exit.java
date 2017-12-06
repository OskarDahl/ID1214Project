/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author Theo
 */
public class Exit extends Terrain {

    @Override
    public boolean passable() {
        return true;
    }

    @Override
    public void print() {
        System.out.print("O");
    }

    @Override
    public void interact() {
        System.out.println("A winner is you!");
    }
    
}
