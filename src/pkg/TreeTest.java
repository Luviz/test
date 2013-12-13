package pkg;

public class TreeTest {
	public static void main(String []args) {
		Tree t = new Tree();
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
		
		System.out.println("end");
	}
}
