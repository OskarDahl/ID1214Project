package maze;

/**
 * Created by oskar on 2017-11-29.
 */
public class Player {
    private int x, y;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight(){
        this.x += 1;
    }

    public void moveDown(){
        this.y -= 1;
    }

    public void moveUp(){
        this.y += 1;
    }

    public void print(){
        System.out.print("P");
    }
}
