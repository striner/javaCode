package striner.crm.staff.domain;

import java.util.Date;

import striner.crm.post.domain.CrmPost;

/**
 * 员工表
 * @author striner
 *
 */
public class CrmStaff {

	private String staffId;
	private String loginName;
	private String loginPwd;   //登录密码
	private String staffName;
	private Date gender;   //员工性别
	private String onDutyDate;
	
	//员工对职务 --> 一对多
	private CrmPost post;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getGender() {
		return gender;
	}

	public void setGender(Date gender) {
		this.gender = gender;
	}

	public String getOnDutyDate() {
		return onDutyDate;
	}

	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public CrmPost getPost() {
		return post;
	}

	public void setPost(CrmPost post) {
		this.post = post;
	}
	
}
