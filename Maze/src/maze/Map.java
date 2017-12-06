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
    
    String[][] charMap = {
        {
            "XXXXXXOXXXXXX",
            "X   X   XXXXX",
            "X / X   XXXXX",
            "X   X   XXXXX",
            "XX XXX[XXXXXX",
            "X   X   X   X",
            "X       _ / X",
            "X   X   X   X",
            "XXXXXXpXXXXXX"
        },
        {
            "XXOXX",
            "XX XX",
            "XX_XX",
            "X   X",
            "X / X",
            "X   X",
            "XXpXX"
        },
        {
            "XXXXXXXXXXOXX",
            "X   X   X   X",
            "X / X   _   X",
            "X   X   X   X",
            "XX XXX XXX_XX",
            "X   X   X   X",
            "X   [       X",
            "X   X   X   X",
            "XX_XXXXXXX XX",
            "X   X   X   X",
            "X /     X / X",
            "X   X   X   X",
            "XXXXXXpXXXXXX"
        }
        
    };
    
    public Map(int mapSelect){
        terrainMap = new Terrain[charMap[mapSelect][0].length()][charMap[mapSelect].length];
        
        for (int y = 0; y < terrainMap[0].length; y++){
            for (int x = 0; x<terrainMap.length;x++){
                switch(charMap[mapSelect][y].charAt(x)){
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
                    case 'O': terrainMap[x][y] = new Exit();
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

    public int getWidth() {
        return terrainMap.length;
    }

    public int getHeight() {
        return terrainMap[0].length;
    }
    
    public Terrain getTerrain(int x, int y){
        return terrainMap[x][y];
    }
}
