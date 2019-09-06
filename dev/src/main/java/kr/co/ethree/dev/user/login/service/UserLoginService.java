package kr.co.ethree.dev.user.login.service;

import java.util.Map;

@SuppressWarnings({ "rawtypes" })
public interface UserLoginService {
	
	public Map selectUserId(String userId) throws Exception;
	public Map selectUserInfo(Map paramMap) throws Exception;
	public void registUser(Map paramMap) throws Exception;
	public void deleteUser(Map paramMap) throws Exception;

}