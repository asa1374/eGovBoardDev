package kr.co.ethree.dev.admin.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.ethree.dev.common.base.BaseAbstractDAO;
import kr.co.ethree.dev.common.model.ListHelperVO;

@Repository("adminBoardDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminBoardDAO extends BaseAbstractDAO {

	// ####################################################################################################
	// ## 게시판
	// ####################################################################################################

	/**
	 * 게시판 정보 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardInfoMap(Map paramMap) throws Exception {
		return (Map) sqlSession.selectOne("adminBoardMapper.selectBoardInfoMap", paramMap);
	}

	// ####################################################################################################
	// ## 게시글
	// ####################################################################################################

	/**
	 * 게시글 목록 및 개수 조회
	 * 
	 * @param listHelper
	 * @return
	 * @throws Exception
	 */
	public ListHelperVO getBoardDataListVO(ListHelperVO listHelperVO) throws Exception {
		return getListHelperVO("adminBoardMapper.selectBoardDataListCnt", "adminBoardMapper.selectBoardDataList", listHelperVO);
	}

	/**
	 * 게시글 목록 개수 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectBoardDataListCnt(Map paramMap) throws Exception {
		return (Integer) sqlSession.selectOne("adminBoardMapper.selectBoardDataListCnt", paramMap);
	}

	/**
	 * 게시글 목록 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public List selectBoardDataList(Map paramMap) throws Exception {
		return sqlSession.selectList("adminBoardMapper.selectBoardDataList", paramMap);
	}

	/**
	 * 게시글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataMap(Map paramMap) throws Exception {
		return (Map) sqlSession.selectOne("adminBoardMapper.selectBoardDataMap", paramMap);
	}

	/**
	 * 게시글 이전 다음글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataNextPrevMap(Map paramMap) throws Exception {
		return (Map) sqlSession.selectOne("adminBoardMapper.selectBoardDataNextPrevMap", paramMap);
	}

	/**
	 * 게시글 등록
	 * 
	 * @param paramMap
	 * @return
	 */
	public int insertBoardData(Map paramMap) throws Exception {
		return (Integer) sqlSession.insert("adminBoardMapper.insertBoardData", paramMap);
	}

	/**
	 * 게시글 수정
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateBoardData(Map paramMap) throws Exception {
		return sqlSession.update("adminBoardMapper.updateBoardData", paramMap);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardData(Map paramMap) throws Exception {
		return sqlSession.update("adminBoardMapper.deleteBoardData", paramMap);
	}

}