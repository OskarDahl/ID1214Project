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
public abstract class Terrain {
    
    public abstract boolean passable();
    
    public abstract void print();
    
    public abstract void interact();
}
