package striner.crm.staff.service.impl;

import java.util.List;

import striner.crm.staff.dao.StaffDao;
import striner.crm.staff.domain.CrmStaff;
import striner.crm.staff.service.StaffService;
import striner.crm.utils.MyStringUtils;

public class StaffServiceImpl implements StaffService{

	private StaffDao staffDao;
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	@Override
	public CrmStaff login(CrmStaff staff) {
		//MD5加密
		String loginPwd = MyStringUtils.getMD5Value(staff.getLoginPwd());
		return staffDao.find(staff.getLoginName(), loginPwd);
	}

	@Override
	public List<CrmStaff> fingAllStaff() {
		return this.staffDao.findAll();
	}

	@Override
	public CrmStaff findById() {
		return this.staffDao.findById("staffId");
	}

}
