package striner.crm.staff.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import striner.crm.staff.dao.StaffDao;
import striner.crm.staff.domain.CrmStaff;

public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao {
//之后spring配置dao层时,需要注入SessionFactory
	@Override
	public CrmStaff find(String loginName, String loginPwd) {

		List<CrmStaff> allStaff = this.getHibernateTemplate().find("FROM CrmStaff WHERE loginName=? AND loginPWD=?", loginName, loginPwd);
		if(allStaff.size() == 1) {
			return allStaff.get(0);
		}
		return null;
	}

	@Override
	public List<CrmStaff> findAll() {
		
		return this.getHibernateTemplate().find("FROM CrmStaff");
	}

	@Override
	public CrmStaff findById(String staffId) {
		return this.getHibernateTemplate().get(CrmStaff.class, staffId);
	}

}
