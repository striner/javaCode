package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServletClient {

	public static void main(String[] args) throws Exception, IOException {
		//向服务器发送请求建立连接
		Socket socket = new Socket("localhost", 8899);
		
		//从socket中获取输入输出流
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		
		PrintWriter pw = new PrintWriter(outputStream);
		pw.println("模拟输入");
		pw.flush();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
		String rusult = bf.readLine();
		System.out.println(rusult);
		
		inputStream.close();
		outputStream.close();
		socket.close();
	}

}
