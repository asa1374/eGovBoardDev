package kr.co.ethree.dev.common.base;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.ethree.dev.common.model.ListHelperVO;

public abstract class BaseAbstractDAO {

	@Autowired
	protected SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 개수 및 목록 조회
	 * 
	 * @param countQuery
	 * @param listQuery
	 * @param listHelperVO
	 * @return
	 * @throws Exception
	 */
	protected ListHelperVO getListHelperVO(String countQuery, String listQuery, ListHelperVO listHelperVO) throws Exception {

		int totalCount = (Integer) sqlSession.selectOne(countQuery, listHelperVO.getParamMap());

		if (totalCount > 0) {
			listHelperVO.setTotalCount(totalCount);
			listHelperVO.setList(sqlSession.selectList(listQuery, listHelperVO.getParamMap()));
		}

		return listHelperVO;
	}

}