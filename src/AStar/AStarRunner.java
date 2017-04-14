package AStar;

public class AStarRunner {

	public static void main(String[] args) {
        int[][] graph = new int[][] {
        	{1,1,1,1,0,1,1,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,0,1,1,1,0,1},
        	{1,1,0,1,0,0,0,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,0,1,1,1,0,1},
        	{1,1,0,1,0,1,0,1},
        	{1,1,1,1,0,1,1,1},
        };
        Node start = new Node(0,0);
        Node goal = new Node(7,7);
        AStar aStar = new AStar(start, goal, graph);
        aStar.search();
        //Expected Output:
        //(7,7) <-- (7,6) <-- (7,5) <-- (6,5) <-- (5,5) <-- (5,4) <-- (5,3) <-- (4,3) <-- (3,3) <-- (2,3) <-- (1,3) <-- (0,3) <-- (0,2) <-- (0,1) <-- (0,0)
        //14 Nodes total
	}
}
