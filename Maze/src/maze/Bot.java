package maze;

import java.util.*;

/**
 * Created by oskar on 2017-12-06.
 */
public class Bot {
    private class coordinates { //Node for a specific part of the map
        int x;
        int y;
        int toggled;
        coordinates cameFrom; //saves where it came from


        coordinates(int x, int y, int toggled){
            this.x = x;
            this.y = y;
            this.toggled = toggled;
            cameFrom = null;
        }

        coordinates(int x, int y, int toggled, coordinates from){
            this.x = x;
            this.y = y;
            this.toggled = toggled;
            cameFrom = from;
        }
    }

    private Player avatar;
    private Map map;
    private Stack<Integer> path = new Stack<Integer>();
    private boolean[][][] visited;      //A representation of the map for the bot which has to stats for the doors
    private int x, y;
    private LinkedList<coordinates> queue = new LinkedList<>();


    public Bot(Map m) {
        avatar = m.player;
        map = m;
        x = m.terrainMap.length;
        y = m.terrainMap[0].length;
        visited = new boolean[x][y][2];
    }

    public void pathFind(){     //Uses a BFS to find the shortest path
        visited[avatar.getX()][avatar.getY()][0] = true;
        search(new coordinates(avatar.getX(), avatar.getY(), 0)); //Adds the first 1-4 possible moves to the queue

        while (!queue.isEmpty()) {      //Takes the next coordinate and checks for possible new moves and add those to the queue
            coordinates koord = queue.removeFirst();
            search(koord) ;
        }
    }

    private void search(coordinates koord){     //Checks all possible new moves and adds them to the queue
        if(map.terrainMap[koord.x][koord.y] instanceof Exit){       //Have reached the exit, prints the coordinates, clears the queue and adds the path to a stack
            System.out.println("exit found at " + koord.x + " " + koord.y);
            createPath(koord);
            queue.clear();
            System.out.println("Path created");
            return;
        }
        if (koord.x + 1 < x) {
            if (!visited[koord.x + 1][koord.y][koord.toggled] && map.canMoveTo(koord.x + 1, koord.y, koord.toggled)) {
                if (map.isSwitch(koord.x + 1, koord.y)){
                    queue.addLast(new coordinates(koord.x + 1, koord.y, (koord.toggled + 1) % 2, koord));
                    visited[koord.x + 1][koord.y][koord.toggled] = true;
                }
                else {
                    queue.addLast(new coordinates(koord.x + 1, koord.y, koord.toggled, koord));
                    visited[koord.x + 1][koord.y][koord.toggled] = true;
                }
            }
        }
        if (koord.x - 1 >= 0) {
            if (!visited[koord.x - 1][koord.y][koord.toggled] && map.canMoveTo(koord.x - 1, koord.y, koord.toggled)) {
                if (map.isSwitch(koord.x - 1, koord.y)){
                    queue.addLast(new coordinates(koord.x - 1, koord.y, (koord.toggled + 1) % 2, koord));
                    visited[koord.x - 1][koord.y][koord.toggled] = true;
                }
                else {
                    queue.addLast(new coordinates(koord.x - 1, koord.y, koord.toggled, koord));
                    visited[koord.x - 1][koord.y][koord.toggled] = true;
                }
            }
        }
        if (koord.y + 1 < y) {
            if (!visited[koord.x][koord.y + 1][koord.toggled] && map.canMoveTo(koord.x, koord.y + 1, koord.toggled)) {
                if (map.isSwitch(koord.x, koord.y + 1)){
                    queue.addLast(new coordinates(koord.x, koord.y + 1, (koord.toggled +1) % 2, koord));
                    visited[koord.x][koord.y + 1][koord.toggled] = true;
                }
                else {
                    queue.addLast(new coordinates(koord.x, koord.y + 1, koord.toggled, koord));
                    visited[koord.x][koord.y + 1][koord.toggled] = true;
                }
            }
        }
        if (koord.y - 1 >= 0) {
            if (!visited[koord.x][koord.y - 1][koord.toggled] && map.canMoveTo(koord.x, koord.y - 1, koord.toggled)) {
                if (map.isSwitch(koord.x, koord.y - 1)){
                    queue.addLast(new coordinates(koord.x, koord.y - 1, (koord.toggled +1) % 2, koord));
                    visited[koord.x][koord.y - 1][koord.toggled] = true;
                }
                else {
                    queue.addLast(new coordinates(koord.x, koord.y - 1, koord.toggled, koord));
                    visited[koord.x][koord.y - 1][koord.toggled] = true;
                }
            }
        }
    }

    public void createPath(coordinates koord){ //goes through the nodes via cameFrom to get the path. Pushes the node to the stack called path
        while (koord.cameFrom != null){
            if(koord.x-1==koord.cameFrom.x){
                path.push(1);
            }
            if(koord.x+1==koord.cameFrom.x){
                path.push(3);
            }
            if(koord.y-1==koord.cameFrom.y){
                path.push(2);
            }
            if(koord.y+1==koord.cameFrom.y){
                path.push(0);
            }
            koord = koord.cameFrom;
        }
    }

    public boolean move(){ //Takes the next move from the stack and makes the avatar move on the map.
        if(path.isEmpty())
            return false;
        int direction = path.pop();
        if(direction == 0)
            map.moveUp();
        if (direction == 1)
            map.moveRight();
        if (direction == 2)
            map.moveDown();
        if (direction == 3)
            map.moveLeft();
        return true;
    }

    public boolean pathExist(){
        return !path.isEmpty();
    }
}
