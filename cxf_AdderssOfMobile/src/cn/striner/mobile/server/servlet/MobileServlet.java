package cn.striner.mobile.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.striner.mobile.server.MobileInterface;
import cn.striner.mobile.server.MobileInterfaceImpl;
/**
 * 手机号归属地查询服务端
 * @author striner
 *
 */
public class MobileServlet extends HttpServlet {
	
	private MobileInterface mobileServer;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneNum = request.getParameter("phoneNum");   //根据页面request的name值获取数据
		if (!"".equals(phoneNum) && phoneNum != null) {
			//获取上下文 从而获取实例对象
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			mobileServer = (MobileInterface) context.getBean("mobileServer");
			
			String result = mobileServer.queryMobile(phoneNum);
			request.setAttribute("result", result);   //将result存至request域中
		}
		request.getRequestDispatcher("/WEB-INF/jsp/queryMobile.jsp").forward(request, response);  //跳转至前端显示页面
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
