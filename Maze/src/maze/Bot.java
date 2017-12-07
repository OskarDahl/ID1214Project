package maze;
import java.util.*;

/**
 * Created by Oskar on 2017-12-06.
 * Mostly written by Theo
 */
public class Bot {

    private Player avatar;
    private Map map;
    private Stack<Integer> path = new Stack<>();
    //0=up 1=right 2=down 3=left
    
    
    private int [][][] simplifiedMaps; 
    
    private LinkedList<PathfindingNode> pathfindingNodes;


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
        pathfindingNodes = new LinkedList();
        pathfindingNodes.add(new PathfindingNode
            (new Step(avatar.getX(),avatar.getY(),null),0));
        while(!pathfindingNodes.isEmpty()){
            PathfindingNode pn = pathfindingNodes.removeFirst();
            if (pathFind(pn.map,pn.step)){
                break;
            }
        }
        
    }

    private void search(){

    }

    private void search(boolean clear){

    }

    public boolean move(){
        if(path.empty())
            return false;
        int move = path.pop();
        if(move==0)
            map.moveUp();
        if(move==1)
            map.moveRight();
        if(move==2)
            map.moveDown();
        if(move==3)
            map.moveLeft();
        return true;
    }

    private boolean pathFind(int map, Step origin) {
        System.out.println("[BOT/pathFind]: searching for paths from "+origin.x+","+origin.y);
        boolean[][] history = new boolean[simplifiedMaps[0].length][simplifiedMaps[0][0].length];
        for(int i = 0;i < history.length; i++){
            Arrays.fill(history[i],true);
        }
        history[origin.x][origin.y]=false;
        LinkedList<Step> frontier = new LinkedList<>();
        LinkedList<Step> switches = new LinkedList<>();
        
        frontier.add(origin);
        
        while(!frontier.isEmpty()){
            
            Step current = frontier.removeFirst();
            if(simplifiedMaps[map][current.x][current.y]==3&&
                    !(current.x==origin.x&&current.y==origin.y)){
                System.out.println("[BOT/pathFind]: found switch at "+current.x+","+current.y);
                pathfindingNodes.add(new PathfindingNode(current,((map+1)%2)));
            }
            else if(simplifiedMaps[map][current.x][current.y]==4){
                System.out.println("[BOT/pathFind]: DID THE THING!");
                setPath(current);
                return true;
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
        return false;
    }
    
    
    private void setPath(Step step){
        if(step.x-1==step.came_from.x){
            path.push(1);
        }
        if(step.x+1==step.came_from.x){
            path.push(3);
        }
        if(step.y-1==step.came_from.y){
            path.push(2);
        }
        if(step.y+1==step.came_from.y){
            path.push(0);
        }
        if(step.came_from.came_from!=null){
            setPath(step.came_from);
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


class PathfindingNode{
    public final Step step;
    public final int map;
    
    public PathfindingNode(Step s, int m){
        step = s;
        map = m;
    }
}