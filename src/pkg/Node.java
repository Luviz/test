package pkg;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node> child;
	private Node parent;
	private String data;
	
	public Node (){
		child = new ArrayList<>();
	}
	public Node (String name, Node parent){
		child = new ArrayList<>();
		parent = 
		data = new String(name);
	}
	
}
