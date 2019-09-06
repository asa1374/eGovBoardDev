package kr.co.ethree.dev.admin.login.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.ethree.dev.common.base.BaseAbstractDAO;

@Repository("adminLoginDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminLoginDAO extends BaseAbstractDAO {

	/**
	 * 게시글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectUserInfoMap(Map paramMap) throws Exception {
		return (Map) sqlSession.selectOne("adminLoginMapper.selectUserInfoMap", paramMap);
	}

}
