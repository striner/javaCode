package cn.striner.mobile.server;

import javax.jws.WebService;

/**
 * 手机号归属地查询  _SEI接口
 * @author striner
 *
 */
@WebService
public interface MobileInterface {
	
	public String queryMobile(String phoneNum);
}
