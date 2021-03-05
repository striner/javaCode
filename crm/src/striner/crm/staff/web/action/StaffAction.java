package striner.crm.staff.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import striner.crm.staff.domain.CrmStaff;
import striner.crm.staff.service.StaffService;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff> {

	private static final long serialVersionUID = 1L;
	
	//封装数据 必须new
	private CrmStaff staff = new CrmStaff();
	@Override
	public CrmStaff getModel() {
		return staff;
	}
	
	//默认按照名称注入
	private StaffService staffService;
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	////////////////////////////////////////////////////////
	
	/**
	 * 员工登录
	 */
	public String login() {
		//查询员工
		CrmStaff findStaff = staffService.login(staff);
		//是否成功
		if (findStaff != null) {
			//成功 
			//信息保存到session作用域
			ActionContext.getContext().getSession().put("loginStaff", findStaff);
			//重定向到首页 --> xml配置
			return "success";
		}
		//未成功
		this.addFieldError("", "用户名与密码不匹配");
		//请求转发显示
		return "login";
	}
	
	/**
	 * 显示首页
	 */
	public String home() {
		return "home";
	}
	
	/**
	 * 查询左右
	 * @return
	 */
	public String findAll() {
		//1 查询所有
		List<CrmStaff> allStaff = staffService.fingAllStaff();
		//2 将结果存放到值栈,方便jsp获得数据
		/*
		 * *方式1:context(map),存放put(key, value),jsp页面获得"#key"
		 * 		ActionContext.getContext().put(key, value)
		 * *方式2:root(值栈),push(obj),一般数据为JavaBean 或map, jsp获得"属性名"或"key"
		 * 		ActionContext.getContext().getValueStack().push(o)		
		 * *方式3:root(值栈),set(key, value),一般数据类型为List,jsp页面获得"key"
		 * 		set() 底层new Map(key, value),将push(map)
		 */
		
		//使用contect存放数据
		ActionContext.getContext().put("allStaff", allStaff);
		
		return "findAll";
	}
	
	/**
	 * 编辑前操作
	 * @return
	 */
	public String editUI() {
		//1 通过id查询员工
		CrmStaff findStaff = this.staffService.findById(staff.getStaffId());
		ActionContext.getContext().getValueStack().push(findStaff);
		return "editUI";
	}
	
}
