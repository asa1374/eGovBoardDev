package kr.co.ethree.dev.user.login.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.ethree.dev.common.base.BaseAbstractDAO;

@Repository("userLoginDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserLoginDAO extends BaseAbstractDAO {
	
	public void registUser(Map paramMap) throws Exception {
		sqlSession.insert("userLoginMapper.registUser", paramMap);
	}
	public Map selectUserId(String userId) throws Exception {
		return (Map) sqlSession.selectOne("userLoginMapper.selectUserId", userId);
	}
	public Map selectUserInfo(Map paramMap) throws Exception {
		return (Map) sqlSession.selectOne("userLoginMapper.selectUserInfo", paramMap);
	}
	public void deleteUser(Map paramMap) throws Exception {
		sqlSession.delete("userLoginMapper.deleteUser", paramMap);
	}
	
}
