package striner.crm.department.domain;

import java.util.HashSet;
import java.util.Set;

import striner.crm.post.domain.CrmPost;

/**
 * 部门名称
 * @author striner
 *
 */
public class CreDepartment {

	private String depId;   //部门id
	private String depName; //部门名称
	
	//多个职务属于一个部门 --> 多对一
	private Set<CrmPost> postSet = new HashSet<CrmPost>();
	
	public Set<CrmPost> getPostSet() {
		return postSet;
	}
	public void setPostSet(Set<CrmPost> postSet) {
		this.postSet = postSet;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
}
