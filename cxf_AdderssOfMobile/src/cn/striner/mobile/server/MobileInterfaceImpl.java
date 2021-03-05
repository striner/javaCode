package cn.striner.mobile.server;

import cn.striner.mobile.server.client.MobileCodeWSSoap;

/**
 * 手机号归属地查询  _SEI实现类
 * @author striner
 *
 */
public class MobileInterfaceImpl implements MobileInterface {

	private MobileCodeWSSoap mobileClient;
	
	@Override
	public String queryMobile(String phoneNum) {
		return mobileClient.getMobileCodeInfo(phoneNum, "");
	}

	public MobileCodeWSSoap getMobileClient() {
		return mobileClient;
	}

	public void setMobileClient(MobileCodeWSSoap mobileClient) {
		this.mobileClient = mobileClient;
	}
}
