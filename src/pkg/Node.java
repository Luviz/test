package pkg;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node> child;
	private Node parent;
	private String name;
	private char type;
	private String data;
	
	/*public Node (){
		child = new ArrayList<>();
	}*/
	/**
	 * cTor
	 * @param name
	 * @param parent
	 */
	public Node (String name, Node parent, char type){
		child = new ArrayList<>();
		//System.out.println("node_ctor:> "+child.size()); //debug
		this.parent = parent;
		this.type = type;
		this.name = new String(name);
		//System.out.println(this.name+this.type+this.parent);//debug
	}
	
	public ArrayList<Node> getChild() {
		return child;
	}
	public void setChild(ArrayList<Node> child) {
		this.child = child;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	/**
	 * uses a string and finds child w/ name == name
	 * @param name
	 * @return null if failed
	 */
	public Node getChildNode(String name){
		for (int i = 0 ; i < child.size() ; i++){
			if (child.get(i).getName().equals(name)){
				//System.out.println("Node_getCN:> CN Found");	//debug
				return child.get(i);
			}
		}
		return null;
	}
	/**
	 * adding a new child
	 * @param name
	 * @return
	 */
	public boolean addChild(String name, char type){
		if (getChildNode(name)== null){
			child.add(new Node(name, this, type));
			//System.out.println("Node_addChild:> Size: "+child.size()+" name: "+name);	//debug;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (int i =0; i < child.size();i++)
			ret += child.get(i).getName() + '\n';
		return ret;
	}
	
}
