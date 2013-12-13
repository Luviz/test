package pkg;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node> child;
	private Node parent;
	private String data;
	
	/*public Node (){
		child = new ArrayList<>();
	}*/
	public Node (String name, Node parent){
		child = new ArrayList<>();
		//System.out.println("node_ctor:> "+child.size()); //debug
		this.parent = parent;
		data = new String(name);
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * uses a string and finds child w/ data == name
	 * @param name
	 * @return null if failed
	 */
	public Node getChildNode(String name){
		for (int i = 0 ; i < child.size() ; i++){
			if (child.get(i).getData().equals(name)){
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
	public boolean addChild(String name){
		if (getChildNode(name)== null){
			child.add(new Node(name, this));
			//System.out.println("Node_addChild:> Size: "+child.size()+" data: "+data);	//debug;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (int i =0; i < child.size();i++)
			ret += child.get(i).getData() + '\n';
		return ret;
	}
	
}
