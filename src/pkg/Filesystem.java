package pkg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Filesystem {
	private BlockDevice m_BlockDevice;
	private Tree t;

	public Filesystem(BlockDevice p_BlockDevice) {
		m_BlockDevice = p_BlockDevice;
		t = new Tree();
	}
	
	public byte[][] ArrayToMatrix(byte [] array){
		byte [][] ret = new byte [250][512];
		int row=1;
		int slot=0;
		if(!(array.length>512*250)){
			for(int i=0;i<array.length;i++){
				if(i%512 == 0 && i !=0){
					row++;
					slot=0;
				}
				slot++;
				ret [row][slot-1] = array[i];
			}
			return ret;
		}else{
			return ret;
		}
	}
	
	//[x]
	public String format() {
		byte[] allNull = new byte[512];
		for (int i =0; i < 512 ;i++){
			allNull[i] = 0;
		}
		for (int i =0; i < 250; i++){
			m_BlockDevice.writeBlock(i, allNull);
		}
		t = new Tree();
		return new String("Diskformat sucessfull");
	}
	//[x]
	public String ls(String p_asPath) {
		//System.out.print("Listing directory ");
		//dumpArray(p_asPath);
		
		System.out.print(t.ls());
		for (int i = 0 ; i < 250 ; i += 2){
			//System.out.println(m_BlockDevice.readBlock(i).toString());		//debug
			String test = new String(m_BlockDevice.readBlock(i));
			//
			if (test.charAt(0) == '/') {
				System.out.println(test); //debug;
				if (test.substring(0, test.lastIndexOf('/')+1).equals(t.pwd())) {
					
					System.out.println(m_BlockDevice.readBlock(i));
				}
			}
		}
		return new String("");
	}
	//[x]
	public String create(String p_asPath, byte[] p_abContents) {
		//System.out.println("start");
		String fName = null , pathTo = null;
		int i =0;
		boolean found = false ,ret = false;
		//find pos to store data!!!
		while (!found && i < 250 ){
			byte [] b = m_BlockDevice.readBlock(i);
			//System.out.println(b[0]);
			if (b[0] == 0){		// if the 1:st item is 0 the array is empty!
				System.out.println(m_BlockDevice.writeBlock(i, p_abContents));
				///*
				//debug
				byte []tmp = m_BlockDevice.readBlock(i);
				//*/
				found = true;
				ret = true;
			}else{
				i++;
			}
		}
		//System.out.println(ret);
		if (p_asPath.contains("/") && found == true) {			//tree creation!
			fName = p_asPath.substring(p_asPath.lastIndexOf('/')+1);
			pathTo = p_asPath.substring(0, p_asPath.lastIndexOf('/'));
			ret = t.create(fName, i);
		}else{
			System.out.println(i);
			ret = t.create(p_asPath, i);  
		}
		//System.out.println(ret);
		if (ret){
			return "All ok! Created: " + fName;
		}else{
			return "epic Failur!!";
		}
		//return "";
	}
	
	//[x]
	public String save(String p_sPath) {
		System.out.print("Saving blockdevice to file " + p_sPath);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p_sPath+".dat"));
			out.writeObject(m_BlockDevice);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String("");
	}
	//[x]
	public String read(String p_sPath) {
		System.out.print("Reading file " + p_sPath + " to blockdevice");
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(p_sPath));
			m_BlockDevice=(BlockDevice) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new String("");
	}
	//[x]
	public String rm(String p_asPath) {
		System.out.print("Removing file ");
		System.out.print(t.rm(p_asPath));
		return new String("");
	}
	//[]
	public String copy(String[] p_asSource, String[] p_asDestination) {
		System.out.print("Copying file from ");
		dumpArray(p_asSource);
		System.out.print(" to ");
		dumpArray(p_asDestination);
		System.out.print("");
		return new String("");
	}
	//[]
	public String append(String[] p_asSource, String[] p_asDestination) {
		System.out.print("Appending file ");
		dumpArray(p_asSource);
		System.out.print(" to ");
		dumpArray(p_asDestination);
		System.out.print("");
		return new String("");
	}
	//[]
	// rename -> move
	public String rename(String p_asSource, String p_asDestination) {
		System.out.println("Renaming file ");
		t.mv(p_asSource, p_asDestination);

		return new String("");
	}
	//[x]
	public String mkdir(String p_asPath) {
		System.out.println("Creating directory "+ p_asPath);
		
		if (t.mkdir(p_asPath)){
			//System.out.println("Created! "+ p_asPath);	//debug
			return new String (p_asPath);
		}
		//dumpArray(p_asPath);
		System.out.print("Failed!");
		return new String("");
	}
	//[x]
	public String cd(String p_asPath) {
		System.out.print("Changing directory to ");
		if (!t.cd(p_asPath)){
			System.out.println("cd Fail!");
		}
		System.out.print("");
		return new String("");
	}
	//[x]
	public String pwd() {
		return new String(t.pwd());
	}
	//[x]
	public String cat(String path) {
		// TODO Auto-generated method stub
		//System.out.println(t.getC().getChildNode(path).getDataIx());
		//System.out.println(t.getC().getChildNode(path).getName());
		int dataIx = t.getC().getChildNode(path).getDataIx();
		return new String(m_BlockDevice.readBlock(dataIx));
	}

	public void debug(){
		int i =0;
		boolean done = false;
		while (!done ||i > 250) {
			byte[] get = m_BlockDevice.readBlock(i);
			if (get[0] == 0)
				done = true;
			else{
				i+=2;
				System.out.println(get.toString());
			}
			
		}
	}
	
	private void dumpArray(String[] p_asArray) {
		for (int nIndex = 0; nIndex < p_asArray.length; nIndex++) {
			System.out.print(p_asArray[nIndex] + "=>");
		}
	}


}
