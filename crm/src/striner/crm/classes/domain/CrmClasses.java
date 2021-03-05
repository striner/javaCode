package striner.crm.classes.domain;

import java.util.Date;

import striner.crm.courseType.domain.CrmCourseType;
/**
 * 班级
 * @author striner
 *
 */
public class CrmClasses {

	private String classId;
	
	private String name;       //班级名称
	private Date beginTime;    //开班时间
	private Date endTime;	    //毕业时间
	private String status;	    //状态(未开课/已开课/已结束)
	private Integer totalCount;    //学生总数
	private Integer upgradeCount;  //升学数
	private Integer changeCount;   //转进班的人数
	private Integer runoffCount;//退费数
	private String remark;		//其他说明
	private Date uploadTime;	//上传时间
	private String uploadPath; //课表路径
	private String uploadFilename;//上传课表名称
	
	//班级对课程 --> 多对一
	private CrmCourseType courseType;    //多对一这里不能new!

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getUpgradeCount() {
		return upgradeCount;
	}

	public void setUpgradeCount(Integer upgradeCount) {
		this.upgradeCount = upgradeCount;
	}

	public Integer getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(Integer changeCount) {
		this.changeCount = changeCount;
	}

	public Integer getRunoffCount() {
		return runoffCount;
	}

	public void setRunoffCount(Integer runoffCount) {
		this.runoffCount = runoffCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUploadFilename() {
		return uploadFilename;
	}

	public void setUploadFilename(String uploadFilename) {
		this.uploadFilename = uploadFilename;
	}

	public CrmCourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CrmCourseType courseType) {
		this.courseType = courseType;
	}
}
