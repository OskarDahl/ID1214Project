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
public class Door extends Terrain{

    boolean open;
    
    public Door(boolean o){
        open = o;
    }
    
    public void toggle(){
        open=!open;
    }
    
    @Override
    public boolean passable() {
        return open;
    }

    @Override
    public void print() {
        if(open)
            System.out.print("_");
        else
            System.out.print("[");
    }

    @Override
    public void interact() {
        // does nothing on purpose
    }
    
}
