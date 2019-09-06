package kr.co.ethree.dev.admin.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.ethree.dev.common.model.ListHelperVO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public interface AdminBoardService {

	/**
	 * 게시판 하드코딩 파라미터
	 * 
	 * @param request
	 * @return
	 */
	public Map getBoardHardParam(HttpServletRequest request) throws Exception;

	// ####################################################################################################
	// ## 게시판
	// ####################################################################################################

	/**
	 * 게시판 정보 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardInfoMap(Map paramMap) throws Exception;

	/**
	 * 게시판 정보 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardInfoMap(String progId) throws Exception;

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
	public ListHelperVO getBoardDataListVO(ListHelperVO listHelperVO) throws Exception;

	/**
	 * 게시글 목록 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectBoardDataListCnt(Map paramMap) throws Exception;

	/**
	 * 게시글 목록 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public List selectBoardDataList(Map paramMap) throws Exception;

	/**
	 * 게시글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataMap(Map paramMap) throws Exception;

	/**
	 * 게시글 이전 다음글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataNextPrevMap(Map paramMap) throws Exception;

	/**
	 * 게시글 등록
	 * 
	 * @param paramMap
	 * @return
	 */
	public int insertBoardData(Map paramMap) throws Exception;

	/**
	 * 게시글 수정
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateBoardData(Map paramMap) throws Exception;

	/**
	 * 게시글 삭제
	 * 
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardData(Map paramMap) throws Exception;

}