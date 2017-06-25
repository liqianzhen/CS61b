import java.util.*;
import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        Queue<Integer> fringe = new ArrayDeque<>();
        marked[s] = true;
        announce();
        fringe.add(s);

        while (fringe.size() != 0 && !targetFound) {
            int x = fringe.remove();
            for (int i : maze.adj(x)) {
                if (!marked[i]) {
                    marked[i] = true;
                    distTo[i] = distTo[x] + 1;
                    edgeTo[i] = x;
                    announce();
                    fringe.add(i);
                    if (i == t) {
                        targetFound = true;
                        break;
                    }
                }
            }
        }
    }



    @Override
    public void solve() {
         bfs(s);
    }
}

