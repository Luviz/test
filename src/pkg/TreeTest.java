package pkg;

public class TreeTest {
	public static void main(String []args) {
		Tree t = new Tree();
		char []c = "lol".toCharArray();
		byte []b = {1,2,3,4,5,6,7,8,9};
		t.mkdir("hello");
		System.out.println(t.ls());
		t.mkdir("test");
		t.mkdir("home");
		System.out.println(t.ls());
		System.out.println(t.getC().getChild().size());
		System.out.println("if out is hello test home == all ok mkdir(String)\n---Test02---");
		System.out.println("!\t cd "+t.cd(".."));
		System.out.println("if out is hello test home == all ok cd /.. \n---Test03---");
		System.out.println("!\t cd "+t.cd("hTest/"));
		System.out.println("!\t cd "+t.cd("home/"));
		t.mkdir("hbardia");
		t.mkdir("hTest");
		System.out.println(t.ls());
		System.out.println("-------------");
		System.out.println("!\t getc: "+t.getC().getName());
		System.out.println(t.ls());
		System.out.println("-------------");
		System.out.println("!\t cd "+t.cd("hTest/"));
		System.out.println(t.ls());
		System.out.println("if ls ......... is empty cd, ls, addChild good\n---test4---");
		System.out.println("cd .. test");
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println("!\t cd "+t.cd(".."));
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println(t.ls());
		System.out.println("cd hbardia/");
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println("!\t cd "+t.cd("hbardia/"));
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println("cd /test/");
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println("!\t cd "+t.cd("/test/"));
		System.out.println("!\t getC pwd: "+t.pwd());
		System.out.println(t.ls());
		System.out.println(t.pwd());
		System.out.println('.');
		Node a = t.getNode("/home/hbardia");
		System.out.println("name: "+a.getName()+
				"\ntype: "+a.getType()+
				"\ndata: "+a.getData());
		System.out.println("...");
		System.out.println(t.pwd());
		System.out.println("crateing!");
		t.create("test", b);
		t.cat("test");
		System.out.println("--");
		System.out.println(t.ls());
		System.out.println(t.ls());
		System.out.println("mv /test /test1");
		t.mv("test", "/test/test1");
		System.out.println(t.pwd());
		System.out.println(t.ls());
		t.cd("..");
		System.out.println(t.ls());
		t.cd("/");
		System.out.println(t.cat("test1"));
		System.out.println(t.ls());
		//t.mv(select, moveTo)
		//other tests 
		System.out.println("----------------------");
		String s = new String ("/bardia/Jedi/test/abc");
		System.out.println(s);
		System.out.println(s.substring(s.lastIndexOf('/')+1));
		System.out.println("hello".contains("/"));
		System.out.println("end");
	}
}
