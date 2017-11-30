/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 * @author Theo
 */

public class Map {
    
    Terrain[][] terrainMap;
    
    Player player;
    
    String[] charMap = {
        "XXXXXX XXXXXX",
        "X   X   XXXXX",
        "X / X   XXXXX",
        "X   X   XXXXX",
        "XX XXX[XXXXXX",
        "X   X   X   X",
        "X       _ / X",
        "X   X   X   X",
        "XXXXXXpXXXXXX"
    };
    
    public Map(){
        terrainMap = new Terrain[charMap[0].length()][charMap.length];
        
        for (int y = 0; y < terrainMap[0].length; y++){
            for (int x = 0; x<terrainMap.length;x++){
                switch(charMap[y].charAt(x)){
                    case 'X': terrainMap[x][y] = new Wall();
                        break;
                    case ' ': terrainMap[x][y] = new Floor();
                        break;
                    case '/': terrainMap[x][y] = new Switch();
                        break;
                    case '[': Door closedDoor = new Door(false);
                        terrainMap[x][y] = closedDoor;
                        Switch.addDoor(closedDoor);
                        break;
                    case '_': Door openDoor = new Door(true);
                        terrainMap[x][y] = openDoor;
                        Switch.addDoor(openDoor);
                        break;
                    case 'p': terrainMap[x][y] = new Floor();
                        player = new Player(x,y);
                        break;
                    default: System.out.println("[Map/constructor]: YOU DIED");
                }
            }
        }
        
    }
    
    public void draw(){
        for (int y = 0; y < terrainMap[0].length; y++){
            for (int x = 0; x<terrainMap.length;x++){
                if(x==player.getX()&&y==player.getY())
                    player.print();
                else
                    terrainMap[x][y].print();
            }
            System.out.println();
        }
    }
    
    public boolean passable(int x, int y){
        return terrainMap[x][y].passable();
    }


    public void moveUp(){
        if (terrainMap[player.getX()][player.getY() - 1].passable())
        {
            player.moveUp();
            terrainMap[player.getX()][player.getY()].interact();
        }
    }
    public void moveDown(){
        if (terrainMap[player.getX()][player.getY() + 1].passable())
        {
            player.moveDown();
            terrainMap[player.getX()][player.getY()].interact();
        }
    }
    public void moveLeft(){
        if (terrainMap[player.getX() - 1][player.getY()].passable())
        {
            player.moveLeft();
            terrainMap[player.getX()][player.getY()].interact();
        }
    }
    public void moveRight(){
        if (terrainMap[player.getX() + 1][player.getY()].passable())
        {
            player.moveRight();
            terrainMap[player.getX()][player.getY()].interact();
        }
    }
}
