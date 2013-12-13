package pkg;

import java.util.ArrayList;

public class Tree {
	private Node head, c;
	
	public Tree(){
		head = new Node("/", null);
		c = head;
	}
	
	public boolean mkdir(String name){
		return mkdir(name, ".");
	}
	/**
	 * DISABLE!
	 * @param name
	 * @param path
	 */
	public boolean mkdir(String name, String path){
		if (c.getChildNode(name) == null){
			c.addChild(name);
			return true;
		}
		return false;
		
	}
	public boolean cd (String path){
		//System.out.println("tree_cd:>Strat");	//debug
		if (path.equals(".")){
			// . point to self so c = c
			return true;
		}else if (path.equalsIgnoreCase("..")){
			//System.out.println("tree_cd:>..");	//debug
			if (!c.equals(head)){ 	// go up by one!
				c = c.getParent();
				return true;
			}
			return false;
		}else{
			//System.out.println("tree_cd:> else path: "+path);	//debug
			if (path.charAt(0) == '/'){
				//System.out.println("tree_cd:>else -> if");	//debug
				//System.out.println("tree_cd:>else -> if head.data: "+ head.getData());	//debug
				c = head;
				if (path.length() == 1){
					return true;
				}
				//System.out.println("Tree_cd:> goto /");	//debug
				cd(path.substring(1));
			}else if (!path.isEmpty()){
				//System.out.println("tree_cd:>else -> else if");	//debug
				String p_to = path.substring(0, path.indexOf('/'));
				Node nTest = c.getChildNode(p_to);
				if (nTest == null)
					return false;
				c = nTest;
				cd(path.substring(1));
			}
			return true;
		}
		//return false;
	}
	public Node getC() {
		return c;
	}
	public String ls() {
		return "ls...........\n"+c.toString();
	}
	public String pwd(){
		String ret = "";
		Node w = c;
		ArrayList<String> tmp = new ArrayList<>();
		while (w != null){
			//System.out.println("tree pwd():> w.data: "+w.getData());	//debug
			tmp.add(w.getData());
			w = w.getParent();
		}
		for (int i =tmp.size()-1; i>=0;i -- ){
			ret += tmp.get(i)+'/';
			//System.out.print(tmp.get(i));	//debug
		}
		
		return ret.substring(1);
	}
}
