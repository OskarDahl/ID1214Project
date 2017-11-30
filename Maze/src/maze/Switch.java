package maze;

import java.util.LinkedList;

/**
 * Created by oskar on 2017-11-29.
 */
public class Switch extends Terrain{

    static LinkedList<Door> doors=new LinkedList<Door>();
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
        for (Door door : doors
             ) {
            door.toggle();
        }
    }

    public static void addDoor(Door door){
        doors.add(door);
    }
}
