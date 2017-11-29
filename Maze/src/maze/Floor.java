package maze;

/**
 * Created by oskar on 2017-11-29.
 */
public class Floor extends Terrain {
    @Override
    public boolean passable() {
        return true;
    }

    @Override
    public void print() {
        System.out.print(" ");
    }

    @Override
    public void interact() {
        //does something on purpose Mjao
    }
}
