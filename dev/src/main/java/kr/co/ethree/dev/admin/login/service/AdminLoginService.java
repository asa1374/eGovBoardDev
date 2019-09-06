package kr.co.ethree.dev.admin.login.service;

import java.util.Map;

@SuppressWarnings({ "rawtypes" })
public interface AdminLoginService {

	/**
	 * 계정 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectUserInfoMap(Map paramMap) throws Exception;

}