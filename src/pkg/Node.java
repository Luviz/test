package pkg;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Node> child;
	private Node parent;
	private String name;
	private char type;
	private int dataIx;
	
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
		this.dataIx = -1;
		//System.out.println(this.name+this.type+this.parent);//debug
	}
	public Node (Node n){
		child = new ArrayList<>(this.child);
		this.parent=n.parent;
		this.name = new String(n.name);
		this.type = n.type;
		this.dataIx = (n.dataIx);
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
		//System.out.println("-?-");
		this.parent=parent;
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
	public int getDataIx() {
		return dataIx;
	}
	public void setDataIx(int dataIx){
		this.dataIx = dataIx;
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
	public boolean addChild(String name, char type, int dataIx){
		if (getChildNode(name)== null){
			Node c = new Node(name, this, type);
			c.setDataIx(dataIx);
			child.add(c);
			//System.out.println("Node_addChild:> Size: "+child.size()+" name: "+name);	//debug;
			return true;
		}else{
			return false;
		}
	}
	public boolean addChild(Node newChild){
		if (getChildNode(name)== null){
			child.add(newChild);
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
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
