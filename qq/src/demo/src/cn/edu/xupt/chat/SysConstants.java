package cn.edu.xupt.chat;

public class SysConstants {

	public static String SYS_ROOT_PATH;
	
	public static String SYS_IMG_PATH;
	
	static {
		SYS_ROOT_PATH = SysConstants.class.getResource("/").getPath();
		SYS_IMG_PATH = SYS_ROOT_PATH + "/img";
	}
}
