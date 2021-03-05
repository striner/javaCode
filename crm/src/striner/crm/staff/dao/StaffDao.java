package striner.crm.staff.dao;

import java.util.List;

import striner.crm.staff.domain.CrmStaff;

public interface StaffDao {
	/**
	 * 通过用户名和密码查询
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	public CrmStaff find(String loginName, String loginPwd);
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<CrmStaff> findAll();
	
	/**
	 * 通过id查询
	 * @param staffId
	 * @return
	 */
	public CrmStaff findById(String staffId);
}
