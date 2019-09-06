package kr.co.ethree.dev.admin.login.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ethree.dev.admin.login.dao.AdminLoginDAO;
import kr.co.ethree.dev.common.base.BaseAbstractServiceImpl;

@Service("adminLoginService")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminLoginServiceImpl extends BaseAbstractServiceImpl implements AdminLoginService {

	@Autowired
	private AdminLoginDAO adminLoginDAO;

	/**
	 * 계정조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectUserInfoMap(Map paramMap) throws Exception {
		return adminLoginDAO.selectUserInfoMap(paramMap);
	}

}