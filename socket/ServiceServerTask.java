package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceServerTask implements Runnable {

	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private BufferedReader br;
	
	public ServiceServerTask(Socket socket) {
		this.socket = socket;
	}

	//业务逻辑 与客户端进行数据交互
	@Override
	public void run() {
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(in));
			
			GetDataServiceImpl data = new GetDataServiceImpl();  //最好用反射实现
			PrintWriter pw = new PrintWriter(out);
			
			//从网络通信输入流中读取客户端发送过来的数据
			//注意:socketInputStream的读数据的方法都是阻塞的
			String next = "";
//			next = br.readLine();
			while((next = br.readLine()) != null) {
				String serviceData = data.getData(next);
				
				//将调用结果写到socket输出流中，以发送给客户端
				pw.println(serviceData);
				pw.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
