package kr.co.ethree.dev.user.login.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ethree.dev.admin.login.dao.AdminLoginDAO;
import kr.co.ethree.dev.common.base.BaseAbstractServiceImpl;
import kr.co.ethree.dev.user.login.dao.UserLoginDAO;

@Service("userLoginService")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginDAO userLoginDAO;

	@Override
	public Map selectUserId(String userId) throws Exception {
		return userLoginDAO.selectUserId(userId);
	}

	@Override
	public void registUser(Map paramMap) throws Exception {
		userLoginDAO.registUser(paramMap);
	}

	@Override
	public Map selectUserInfo(Map paramMap) throws Exception {
		return userLoginDAO.selectUserInfo(paramMap);
	}

	@Override
	public void deleteUser(Map paramMap) throws Exception {
		userLoginDAO.deleteUser(paramMap);
	}

}