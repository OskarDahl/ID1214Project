package maze;
import java.util.*;

/**
 * Created by Oskar on 2017-12-06.
 * Mostly written by Theo
 */
public class Bot {

    private Player avatar;
    private Map map;
    private Stack<Integer> path = new Stack<Integer>();
    
    private int [][][] simplifiedMaps; 


    public Bot(Map m) {
        avatar = m.player;
        map = m;
        simplifiedMaps = new int[2][m.getWidth()][m.getHeight()];
        for (int y = 0; y < simplifiedMaps[0][0].length; y++){
            for (int x = 0; x<simplifiedMaps[0].length;x++){
                Terrain t = map.getTerrain(x, y);
                if(t instanceof Floor){
                    simplifiedMaps[0][x][y]=0;
                    simplifiedMaps[1][x][y]=0;
                }
                else if(t instanceof Wall){
                    simplifiedMaps[0][x][y]=1;
                    simplifiedMaps[1][x][y]=1;
                }
                else if(t instanceof Door && t.passable()){
                    simplifiedMaps[0][x][y]=0;
                    simplifiedMaps[1][x][y]=1;
                }
                else if(t instanceof Door && !t.passable()){
                    simplifiedMaps[0][x][y]=1;
                    simplifiedMaps[1][x][y]=0;
                }
                else if(t instanceof Switch){
                    simplifiedMaps[0][x][y]=3;
                    simplifiedMaps[1][x][y]=3;
                }
                else if(t instanceof Exit){
                    simplifiedMaps[0][x][y]=4;
                    simplifiedMaps[1][x][y]=4;
                }
            }
        }
    }

    public void pathFind(){
        pathFind(0,avatar.getX(),avatar.getY());
    }

    private void search(){

    }

    private void search(boolean clear){

    }

    public void move(){

    }

    private void pathFind(int map, int startX, int startY) {
        System.out.println("[BOT/pathFind]: searching for paths from "+startX+","+startY);
        boolean[][] history = new boolean[simplifiedMaps[0].length][simplifiedMaps[0][0].length];
        for(int i = 0;i < history.length; i++){
            Arrays.fill(history[i],true);
        }
        history[startX][startY]=false;
        LinkedList<Step> frontier = new LinkedList<>();
        LinkedList<Step> switches = new LinkedList<>();
        
        frontier.add(new Step(startX,startY,null));
        
        while(!frontier.isEmpty()){
            
            Step current = frontier.removeFirst();
            
            System.out.println("[BOT/pathFind]: Step at "+current.x+","+current.y);
            
            if(simplifiedMaps[map][current.x][current.y]==3&&
                    !(current.x==startX&&current.y==startY)){
                System.out.println("[BOT/pathFind]: found switch at "+current.x+","+current.y);
                switches.add(current);
            }
            else if(simplifiedMaps[map][current.x][current.y]==4){
                System.out.println("[BOT/pathFind]: DID THE THING!");
                //TODO if exit
            }
            else{
                for(int mod=0;mod<4;mod++){
                    int nextX = current.x;
                    int nextY = current.y;
                    if(mod == 0)
                        nextX++;
                    if(mod == 1)
                        nextX--;
                    if(mod == 2)
                        nextY++;
                    if(mod == 3)
                        nextY--;
                    if(nextX<history.length&&nextX>=0&&
                       nextY<history[0].length&&nextY>=0){
                        if(history[nextX][nextY]&&simplifiedMaps[map][nextX][nextY]!=1){
                            Step newStep = new Step(nextX,nextY,current);
                            frontier.add(newStep);
                            history[nextX][nextY]=false;
                        }
                    }
                        
                }
            }
        }
        while(!switches.isEmpty()){
            Step next = switches.removeFirst();
            pathFind((map+1%2),next.x,next.y);
        }
        
    }
}


class Step{
    public final int x;
    public final int y;
    public final Step came_from;
    
    public Step(int x,int y, Step past){
        this.x=x;
        this.y=y;
        came_from=past;
    }
    
}