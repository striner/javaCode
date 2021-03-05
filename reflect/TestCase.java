package reflect;

//执行一个类中全部以@Demo标注的方法
public class TestCase {

	@Demo
	public String fun1() {
		return "fun1()";
	}
	
	@Demo
	private String fun2() {
		return "fun2()";
	}
	
	public void fun3() {
		System.out.println("fun3()");
	}
}
