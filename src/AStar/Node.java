package AStar;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private int posX, posY;
    private double disFromGoal, disFromStart, cost;
    private Node parent;
    private ArrayList<Node> children;
	
	public Node(int x, int y){
        posX = x;
        posY = y;
    }

    public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public double getDisFromGoal() {
		return disFromGoal;
	}

	public void setDisFromGoal(double disFromGoal) {
		this.disFromGoal = disFromGoal;
	}

	public double getDisFromStart() {
		return disFromStart;
	}

	public void setDisFromStart(double disFromStart) {
		this.disFromStart = disFromStart;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
    
    public void addChild(Node child)
    {
    	children.add(child);
    }


	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public ArrayList<Node> getChildren(){
    	return children;
    }
	
//    An auxiliary function which allows
//    us to remove any child nodes from
//    our list of child nodes.
    public void removeChild(Node n){
        children.remove(n);
    }
    
    public String toString()
    {
    	return "(" + posX + " , " + posY + ") Cost: " + getCost();
    }

	public double getCost() {
		// TODO Auto-generated method stub
		return cost;
	}
	
	public double distanceFrom(Node n)
	{
		return Math.sqrt((n.getPosX() - posX)*(n.getPosX() - posX) + (n.getPosY() - posY)*(n.getPosY() - posY));
	}
	
	public void setCost() {
		// TODO Auto-generated method stub
		cost = disFromStart + disFromGoal;
	}

	@Override
	public int compareTo(Node arg0) {
		// TODO Auto-generated method stub
		this.setCost();
		arg0.setCost();
		return (int)Double.compare(this.getCost() , arg0.getCost());
	}

}
