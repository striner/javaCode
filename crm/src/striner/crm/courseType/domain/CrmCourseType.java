package striner.crm.courseType.domain;

import java.util.HashSet;
import java.util.Set;

import striner.crm.classes.domain.CrmClasses;

/**
 * 课程类别
 * @author striner
 *
 */
public class CrmCourseType {

	private String courseTypeId;
	private String courseCost;  //课程总价格
	private Integer total;          //总学时
	private String courseName;  //课程类别名称
	private String remark;      //描述
	
	//课程对班级 --> 一对多
	private Set<CrmClasses> classesSet = new HashSet<>();

	public String getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseCost() {
		return courseCost;
	}

	public void setCourseCost(String courseCost) {
		this.courseCost = courseCost;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CrmClasses> getClassesSet() {
		return classesSet;
	}

	public void setClassesSet(Set<CrmClasses> classesSet) {
		this.classesSet = classesSet;
	}

}
