package pkg;

import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable{
	private static final long serialVersionUID = 1L;
	private Node head, c, walker;
	
	public Tree(){
		head = new Node("/", null, 'd');
		c = head;
		walker = head;
	}
	
	public boolean mkdir(String name){
		//System.out.println("mkdir ()");
		return mkdir(name, ".");
	}
	/**
	 * DISABLE!
	 * use the WALKER
	 * @param name
	 * @param path
	 */
	public boolean mkdir(String name, String path){
		if (c.getChildNode(name) == null){
			//System.out.println("mkdir (,)");
			c.addChild(name, 'd');
			return true;
		}
		return false;
		
	}
	
	public boolean create(String name, int dataIx){
		if (c.getChildNode(name) == null){
			c.addChild(name, 'f', dataIx);
			//System.out.println(dataIx);
			c.setDataIx(dataIx);
			return true;
		}
		return false;
	}
	/**
	 * remove me
	 * @param name
	 * @return
	 */
	/*public String cat(String name) {
		return c.getDataIx()+"\neof";
	}*/
	
	public boolean rm (String name){
		if (c.getChildNode(name) != null){
			c.getChild().remove(c.getChildNode(name));
			return true;
		}
		return false;
	}
	
	public Node getNode (String path){
		//where should walker start
		//System.out.println(path);							//debug
		if (path.startsWith("/")){
			walker = head;
		}else if (path.startsWith("..")){
			walker = c.getParent();
		}else if (path.startsWith(".")){
			path = path.substring(path.indexOf('/')+1);
			getNode (path);
		}else{
			walker = c;
		}
		if (!path.contains("/")){
			Node wtmp = getC().getChildNode(path);
			if (wtmp != null)
				walker = wtmp;
			//else
				//walker = walker.getParent();
		}else{
			cd_walker(path);
			//getNode(path);
		}
		return walker;
	}
	
	/**
	 * Changes the this c to the path
	 * @param path
	 * @return
	 */
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
				//System.out.println("Tree_cd:> goto /");		//debug
				cd(path.substring(1));
			}else if (!path.isEmpty()){
				//System.out.println("tree_cd:>else -> else if");	//debug
				String p_to;
				try {
					p_to = path.substring(0, path.indexOf('/'));
				} catch (Exception e) {
					path += "/"; 
					p_to = path.substring(0, path.indexOf('/'));
					//System.out.println(p_to); //debug
					//e.printStackTrace();
				}
				Node nTest = c.getChildNode(p_to);
				if (nTest == null || nTest.getType() != 'd')
					return false;
				c = nTest;
				cd(path.substring(1));
			}
			return true;
		}
		//return false;
	}
	
	/**
	 * uses w instead of this.c
	 * @param path
	 * @param w
	 * @return
	 */
	public boolean cd_walker (String path){
		//System.out.println("tree_cd:>Strat");	//debug
		//Node f = walker;
		if (path.equals(".")){
			// . point to self so walker = walker
			return true;
		}else if (path.equalsIgnoreCase("..")){
			//System.out.println("tree_cd:>..");	//debug
			if (!walker.equals(head)){ 	// go up by one!
				walker = walker.getParent();
				return true;
			}
			return false;
		}else if (path.equalsIgnoreCase("/")){
			walker = head;
			return true;
		}else{
			//System.out.println("tree_cd:> else path: "+path);	//debug
			if (!path.contains("/")){
				//file or dir
				walker = getNode(path);
			}
			if (path.charAt(0) == '/'){
				//System.out.println("tree_cd:>else -> if");	//debug
				//System.out.println("tree_cd:>else -> if head.data: "+ head.getData());	//debug
				walker = head;
				if (path.length() == 1){
					return true;
				}
				//System.out.println("Tree_cd:> goto /");	//debug
				cd_walker(path.substring(1));
			}else if (!path.isEmpty()){
				//System.out.println("tree_cd:>else -> else if");	//debug
				String p_to;
				try {
					p_to = path.substring(0, path.indexOf('/'));
				} catch (Exception e) {
					path += "/"; 
					p_to = path.substring(0, path.indexOf('/'));
					//System.out.println(p_to); //debug
					//e.printStackTrace();
				}
				Node nTest = walker.getChildNode(p_to);
				if (nTest == null || nTest.getType() != 'd')
					return false;
				walker = nTest;
				cd_walker(path.substring(1));
			}
			return true;
		}
	}
	
	/**
	 * move node to moveTo (item new name should be part of the path)
	 * <br>can be use to rename as well;
	 * <br>e.g. rename
	 * <br>mv a b;
	 * <br>e.g move 
	 * <br>mv a /new/path/a
	 * @param select
	 * @param moveTo
	 * @return
	 */
	public boolean mv(String select , String moveTo){
		Node selected = getNode(select);
//		System.out.println(moveTo.substring(0,moveTo.lastIndexOf('/')+1));			//debug
		Node dirTo = getNode(moveTo.substring(0,moveTo.lastIndexOf('/')+1));
//		System.out.println("mv:> "+selected.getName()+"\t"+selected.getType());		//debug
//		System.out.println("mv:> "+dirTo.getName()+"\t"+dirTo.getType());			//debug
		selected.setName(moveTo.substring(moveTo.lastIndexOf('/')+1));		//rename
		dirTo.addChild(selected);
		selected.getParent().getChild().remove(selected);	//remove the previsoverson
		if (dirTo.getType() == 'd'){
//			System.out.println("gettyp:");											//debug
			selected.setParent(dirTo);
			return true;
		}else{
			return false;
		}
	}
	public boolean cp(String select , String moveTo){
		return false;
	}
	public Node getC() {
		return c;
	}
	public String ls() {
//		System.out.println("ls");					//debug
		return "-----------\n"+pwd()+"\n"+c.toString();
		
	}
	public String pwd(){
		String ret = "";
		Node w = c;
		ArrayList<String> tmp = new ArrayList<>();
		while (w != null){
			//System.out.println("tree pwd():> w.data: "+w.getData());	//debug
			tmp.add(w.getName());
			w = w.getParent();
		}
		for (int i =tmp.size()-1; i>=0;i -- ){
			ret += tmp.get(i)+'/';
			//System.out.print(tmp.get(i));	//debug
		}
		
		return ret.substring(1);
	}
	/**
	 * check is path is in exstece
	 * @param path
	 * @return
	 */
	public boolean isDir(String path){
		return cd_walker(path);
	}
}
