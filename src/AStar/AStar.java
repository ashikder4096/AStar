package AStar;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
	
	PriorityQueue<Node> queue = new PriorityQueue<>();
	ArrayList<Node> explored = new ArrayList<>();
	Node[][] grid; //flip x and y coordinate
	Node startNode;
	Node goalNode;
	
	public AStar(Node startNode, Node goalNode, int[][] map) {
		this.startNode = startNode;
		this.goalNode = goalNode;
		grid = new Node[map.length][map.length];
		
		//sets up start and goal nodes
		grid[startNode.getPosX()][startNode.getPosY()] = startNode;
		grid[goalNode.getPosX()][goalNode.getPosY()] = goalNode;
		startNode.setDisFromStart(0);
		startNode.setDisFromGoal(startNode.distanceFrom(goalNode));
		startNode.setCost();
		
		/**
		 * Creating basic map structure
		 */
		//populating grid
		for(int i = 0 ; i < map.length ; i++)
		{
			for(int j = 0 ; j < map[i].length ; j ++)
			{
				if(grid[i][j] !=startNode && grid[i][j] != goalNode && map[i][j] == 1) //populates the grid with nodes if the map is indicated with a 1 
				{
					grid[i][j] = new Node(i,j);				
				}
			}
		}
		
		queue.add(startNode); //will initialize with startNode being added to the queue
		startNode.setParent(null);
		printList(grid); //displays the map
	}
	
	public PriorityQueue<Node> getQueue() {
		return queue;
	}

	public void setQueue(PriorityQueue<Node> queue) {
		this.queue = queue;
	}

	public ArrayList<Node> getExplored() {
		return explored;
	}

	public void setExplored(ArrayList<Node> explored) {
		this.explored = explored;
	}

	public Node[][] getGrid() {
		return grid;
	}

	public void setGrid(Node[][] grid) {
		this.grid = grid;
	}
	
	public boolean search(){
		while(!queue.isEmpty()){
			Node parent = queue.remove(); //takes out the node
			explored.add(parent); //adds the current node to explored
			
			if(parent.equals(goalNode)) //if the goal is found
			{
				printPath(goalNode); //displays the path
				System.out.println("AStar Search Path Found!");
//				printExplored(); //for test purposes
				return true;
			}
			else
			{
				setNodeChildren(parent);
				for(int i = 0 ; i < parent.getChildren().size() ; i++)
				{
					Node child = parent.getChildren().get(i);
					if(child != null)
					{
						if(!queue.contains(child) && !explored.contains(child))
						{
							child.setParent(parent); //sets node's parent
							child.setDisFromStart(parent.getDisFromStart() + 1);
							child.setDisFromGoal(child.distanceFrom(goalNode));
							child.setCost();
//							if(child == grid[7][5]) //for testing certain node when they are being added to the queue
//							{
//								System.out.println("tested node: " + child);
//							}
							queue.add(child); //adds to queue
						}
					}
				}
			}
		}
		System.out.println("Path not found");
		return false; 
	}

	private void printPath(Node goal) {
		int count = 0;
		while(goal.getParent() != null)
		{
			count++;
			System.out.print(goal + " <--- ");
			goal = goal.getParent();
		}
		System.out.print(goal);
		System.out.println();
		System.out.println("Total of " + count + " Nodes");
	}
	
	private void setNodeChildren(Node n) //probably made this more complicated than it has to
	{
		int x = n.getPosX(), y=n.getPosY();
		//edges and their horizontals
		if(n.getPosX() == 0) //Deals with (0,x)
		{
			n.addChild(grid[x + 1][y]); //if(0,0)
			if(n.getPosY() == 0)
			{
				n.addChild(grid[x][y+1]);
			}
			else if(n.getPosY() == grid[x].length-1) //if(0,last)
			{
				n.addChild(grid[x][y - 1]);
			}
			else
			{
				n.addChild(grid[x][y - 1]);
				n.addChild(grid[x][y+1]);
			}
		}
		else if(n.getPosX() == grid.length - 1) //Node [last,x]
		{
			n.addChild(grid[x - 1][y]);
			if(n.getPosY() == 0)
			{
				n.addChild(grid[x][y + 1]);
			}
			else if(n.getPosY() == grid[y].length-1) //if(0,last)
			{
				n.addChild(grid[x][y - 1]);
			}
			else
			{
				n.addChild(grid[x][y-1]);
				n.addChild(grid[x][y+1]);
			}
		}
		else if(n.getPosY() == 0)
		{
			n.addChild(grid[x][y + 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
		else if(n.getPosY() == grid.length - 1)
		{
			n.addChild(grid[x][y - 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
		else
		{
			n.addChild(grid[x][y + 1]);
			n.addChild(grid[x][y - 1]);
			n.addChild(grid[x - 1][y]);
			n.addChild(grid[x + 1][y]);
		}
	}
	
	private void printExplored()
	{
		for(Node n : explored)
		{
			System.out.println(n);
		}
	}
	
	private void printList(Node[][] arr)
	{
		for(Node[] a : arr)
		{
			for(Node n : a)
			{
				if(n != null)
				{
					System.out.print("1 ");
				}
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
}
