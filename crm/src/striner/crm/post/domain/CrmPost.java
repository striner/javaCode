package striner.crm.post.domain;

import java.util.HashSet;
import java.util.Set;

import striner.crm.department.domain.CreDepartment;
import striner.crm.staff.domain.CrmStaff;

/**
 * 职务信息
 * @author striner
 *
 */
public class CrmPost {

	private String postId;
	private String postName;
	
	//职务对部门 --> 多对一
	private CreDepartment department;
	
	//职务对员工 --> 一对多
	private Set<CrmStaff> staffSet = new HashSet<>();

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public CreDepartment getDepartment() {
		return department;
	}

	public void setDepartment(CreDepartment department) {
		this.department = department;
	}

	public Set<CrmStaff> getStaffSet() {
		return staffSet;
	}

	public void setStaffSet(Set<CrmStaff> staffSet) {
		this.staffSet = staffSet;
	}

	
	
}
