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
public class Wall extends Terrain {

    @Override
    public boolean passable() {
        return false;
    }

    @Override
    public void print() {
        System.out.print("X");
    }

    @Override
    public void interact() {
        // Does nothing. On purpose
    }
    
}
