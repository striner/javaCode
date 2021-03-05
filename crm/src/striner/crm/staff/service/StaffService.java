package striner.crm.staff.service;

import java.util.List;

import striner.crm.staff.domain.CrmStaff;

public interface StaffService {

	/**
	 * 登录
	 * @param staff
	 * @return
	 */
	public CrmStaff login(CrmStaff staff);
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<CrmStaff> fingAllStaff();
	
	/**
	 * 根据id查询
	 * @return
	 */
	public CrmStaff findById(String id);
}
