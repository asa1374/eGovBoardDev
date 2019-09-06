package kr.co.ethree.dev.admin.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ethree.dev.admin.board.dao.AdminBoardDAO;
import kr.co.ethree.dev.common.base.BaseAbstractServiceImpl;
import kr.co.ethree.dev.common.model.ListHelperVO;

@Service("adminBoardService")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminBoardServiceImpl extends BaseAbstractServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardDAO adminBoardDAO;

	/**
	 * 게시판 하드코딩 파라미터
	 * 
	 * @param request
	 * @return
	 */
	public Map getBoardHardParam(HttpServletRequest request) throws Exception {

		Map hMap = new HashMap();

		String requestURI = request.getRequestURI();

		if (requestURI != null) {

			if (requestURI.contains("/notice/")) {
				hMap.put("boardProgId", "notice");

			} else if (requestURI.contains("/data/")) {
				hMap.put("boardProgId", "data");
			}
		}

		return hMap;
	}

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
		return adminBoardDAO.selectBoardInfoMap(paramMap);
	}

	/**
	 * 게시판 정보 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardInfoMap(String progId) throws Exception {

		if (progId == null || progId.length() == 0) {
			return null;
		}

		Map paramMap = new HashMap();
		paramMap.put("progId", progId);

		return this.selectBoardInfoMap(paramMap);
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
		return adminBoardDAO.getBoardDataListVO(listHelperVO);
	}

	/**
	 * 게시글 목록 개수 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectBoardDataListCnt(Map paramMap) throws Exception {
		return adminBoardDAO.selectBoardDataListCnt(paramMap);
	}

	/**
	 * 게시글 목록 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public List selectBoardDataList(Map paramMap) throws Exception {
		return adminBoardDAO.selectBoardDataList(paramMap);
	}

	/**
	 * 게시글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataMap(Map paramMap) throws Exception {
		return adminBoardDAO.selectBoardDataMap(paramMap);
	}

	/**
	 * 게시글 이전 다음글 조회
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map selectBoardDataNextPrevMap(Map paramMap) throws Exception {
		return adminBoardDAO.selectBoardDataNextPrevMap(paramMap);
	}

	/**
	 * 게시글 등록
	 * 
	 * @param paramMap
	 * @return
	 */
	public int insertBoardData(Map paramMap) throws Exception {
		return adminBoardDAO.insertBoardData(paramMap);
	}

	/**
	 * 게시글 수정
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateBoardData(Map paramMap) throws Exception {
		return adminBoardDAO.updateBoardData(paramMap);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardData(Map paramMap) throws Exception {
		return adminBoardDAO.deleteBoardData(paramMap);
	}

}
