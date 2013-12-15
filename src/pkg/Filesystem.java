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
		return new String("");
	}
	//[x]
	public String create(String p_asPath, byte[] p_abContents) {
		System.out.print("Creating file ");
		String test = new String(p_abContents);
		t.create(p_asPath, p_abContents);
		System.out.println(test);
		System.out.print("");
		return new String("");
	}
	//[x]
	public String cat(String p_asPath) {
		System.out.print("Dumping contents of file ");
		
		//dumpArray(p_asPath);
		System.out.print(t.cat(p_asPath));
		return new String("");
	}
	//[?]
	public String save(String p_sPath) {
		System.out.print("Saving blockdevice to file " + p_sPath);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p_sPath+".dat"));
			out.writeObject(m_BlockDevice);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String("");
	}
	//[?]
	public String read(String p_sPath) {
		System.out.print("Reading file " + p_sPath + " to blockdevice");
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(p_sPath));
			m_BlockDevice=(BlockDevice) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
	//[x]
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
	
	private void dumpArray(String[] p_asArray) {
		for (int nIndex = 0; nIndex < p_asArray.length; nIndex++) {
			System.out.print(p_asArray[nIndex] + "=>");
		}
	}

}
