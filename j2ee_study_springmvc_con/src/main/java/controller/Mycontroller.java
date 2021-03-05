package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 如何写一个处理器:
 * 1.不用实现Controller接口.
 * 2.可以在处理器类中,添加多个方法,每一个方法处理一种类型的请求.
 * 3.方法名不做要求,返回类型可以是ModelAndView,也可以是String(只有视图名).
 * 4.使用@Controller,将处理器交给容器管理,在spring的配置文件中不需要再配置该处理器.
 * 5.
 * @author striner
 *
 */
@Controller
public class Mycontroller {
	
	@RequestMapping("/show.do")
	public String show() {
		System.out.println("show()");
		return "show";
	}
	
	@RequestMapping("/login.do")   
	public String index() {
		System.out.println("login --> index.jsp");
		return "index";
	}
	
	//从页面获取值的第一种方式
	@RequestMapping("/toLogin.do")   //告诉前端控制器请求路径与处理器方法的对应关系,不需要配置HandlerMapping
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println("username: " + username);
		String psd = request.getParameter("password");
		System.out.println("password: " + psd);
		return "login";
	}
	
	//从页面获取值的第二种方式
	@RequestMapping("/logout")
	public String logout(String username, @RequestParam("password") String psd) {
		System.out.println("username: " + username + " password: " + psd);
		return "login";
	}
	
	//从页面获取值的第三种方式
	@RequestMapping("/login.do")
	public String login(User user) {
		System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
		return "index";
	}
	
	//向页面传值的第一种方式 使用request
	@RequestMapping("/login1.do")
	public String login1(User user, HttpServletRequest request) {
		System.out.println("login1");
		String username = user.getUsername();
		String password = user.getPassword();
		//将数据绑定到request
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		//spring默认使用转发,会自动转发到以return值为jsp名的页面.
		return "index";
	}
	
	//向页面传值的第二种方式 使用ModelAndView
	@RequestMapping("/login2.do")
	public ModelAndView login2(User user) {
		System.out.println(user.getUsername() + " " + user.getPassword());
		Map<String,Object> map = new HashMap<>();
		map.put("username", user.getUsername()); //相当于request.setAttribute("username", username);
		map.put("password", user.getPassword());
		//构造ModelAndView对象
		ModelAndView mav = new ModelAndView("index", map);
		return mav;
	}
	
	//向页面传值的第三种方式  使用ModelMap
	@RequestMapping("/login3")
	public String login3(User user, ModelMap mm) {
		System.out.println("login3()");
		System.out.println(user.getUsername() + " " + user.getPassword());
		mm.addAttribute("username", user.getUsername()); //相当于request.setAttribute..
		return "index";
	}
	
	//向页面传值的第四种方式  使用Session
	@RequestMapping("/login4")
	public String login4(User user, HttpSession session) {
		System.out.println(user.getUsername() + " " + user.getPassword());
		session.setAttribute("username", user.getUsername());
		return "index";
	}
}
