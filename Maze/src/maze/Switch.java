package maze;

/**
 * Created by oskar on 2017-11-29.
 */
public class Switch extends Terrain{
    boolean toggled = false;

    @Override
    public boolean passable() {
        return true;
    }

    @Override
    public void print() {
        if (toggled)
            System.out.print("/");
        else
            System.out.print("\\");
    }

    @Override
    public void interact() {
        toggled = !toggled;
        //TODO toggle doors
    }
}
