package kr.co.ethree.dev.admin.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ethree.dev.admin.board.service.AdminBoardService;
import kr.co.ethree.dev.common.base.BaseAbstractController;
import kr.co.ethree.dev.common.model.ListHelperVO;
import kr.co.ethree.dev.common.util.RequestUtil;
import kr.co.ethree.dev.common.util.StringUtil;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminBoardController extends BaseAbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService adminBoardService;

	// ####################################################################################################
	// ## 게시글
	// ####################################################################################################

	/**
	 * 게시글 목록 페이지
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/noticeList.do", "/admin/board/data/dataList.do" })
	public String adminBoardList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		/**
		 * 파람맵 객체 사용 또는 일반 해쉬맵에 유틸사용 <br>
		 * 파람맵의 경우 getStr(), getInt() 형변환 메소드 및 CLOB 자동처리 제공
		 */
		// ParamMap paramMap = new ParamMap(request);
		// Map paramMap = RequestUtil.getRequestMap(request);

		/**
		 * 리스트 헬퍼 사용<br>
		 * 파라미터를 request 사용 시 페이징 js 없이 동작가능<br>
		 * 파라미터를 Map 사용 시 js 필요
		 */
		ListHelperVO boardDataListVO = new ListHelperVO(request);
		boardDataListVO.setParamValue("boardSeq", boardInfoMap.get("seq"));

		model.addAttribute("boardInfoMap", boardInfoMap);
		model.addAttribute("boardDataListVO", adminBoardService.getBoardDataListVO(boardDataListVO));
		model.addAttribute("paramMap", boardDataListVO.getParamMap());

		return "admin/board/adminBoardList";
	}

	@RequestMapping({ "/admin/board/notice/noticeListPop.do", "/admin/board/data/dataListPop.do" })
	public String adminBoardListPop(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		// ParamMap paramMap = new ParamMap(request);
		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("cntPerPage", paramMap.get("cntPerPage") != null ? paramMap.get("cntPerPage") : "10");
		paramMap.put("boardSeq", boardInfoMap.get("seq"));

		/**
		 * 리스트 헬퍼 미사용
		 */
		int boardListCnt = adminBoardService.selectBoardDataListCnt(paramMap);
		if (boardListCnt > 0) {
			model.addAttribute("boardListCnt", boardListCnt);
			model.addAttribute("boardList", adminBoardService.selectBoardDataList(paramMap));
		}

		model.addAttribute("boardInfoMap", boardInfoMap);
		model.addAttribute("paramMap", paramMap);

		return "admin/board/adminBoardListPop";
	}

	/**
	 * 게시글 조회 페이지
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/noticeView.do", "/admin/board/data/dataView.do" })
	public String adminBoardView(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("seq", paramMap.get("seq"));
		paramMap.put("boardSeq", boardInfoMap.get("seq"));

		/**
		 * 게시글 조회
		 */
		Map boardDataMap = adminBoardService.selectBoardDataMap(paramMap);

		if (boardDataMap == null) {
			model.addAttribute("alertMsg", "게시글이 존재하지 않습니다.");
			return "messageView";
		}

		HttpSession session = request.getSession();
		String hitFlag = (String) session.getAttribute("hitFlag_" + boardDataMap.get("seq"));

		if (hitFlag == null) {
			/**
			 * 조회수 증가
			 */
			// adminBoardService.updateBoardDataHitCnt(boardDataMap);
			session.setAttribute("hitFlag_" + boardDataMap.get("seq"), "Y");
		}

		/**
		 * 이전글 다음글 조회
		 */
		Map nextPrevView = adminBoardService.selectBoardDataNextPrevMap(paramMap);

		model.addAttribute("boardInfoMap", boardInfoMap);
		model.addAttribute("boardDataMap", boardDataMap);
		model.addAttribute("nextPrevView", nextPrevView);
		model.addAttribute("paramMap", paramMap);

		model.addAttribute("queryString", StringUtil.base64Encode(RequestUtil.getQueryString(request)));
		model.addAttribute("toQueryString", StringUtil.base64Decode((String) paramMap.get("queryString")));

		return "/admin/board/adminBoardView";
	}

	/**
	 * 게시글 수정 페이지
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/noticeModify.do", "/admin/board/data/dataModify.do" })
	public String adminBoardModify(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("seq", paramMap.get("seq"));
		paramMap.put("boardSeq", boardInfoMap.get("seq"));

		/**
		 * 게시글 조회
		 */
		Map boardDataMap = adminBoardService.selectBoardDataMap(paramMap);

		if (boardDataMap == null) {
			model.addAttribute("alertMsg", "게시글이 존재하지 않습니다.");
			return "messageView";
		}

		HttpSession session = request.getSession();
		String hitFlag = (String) session.getAttribute("hitFlag_" + boardDataMap.get("seq"));

		if (hitFlag == null) {
			/**
			 * 조회수 증가
			 */
			// adminBoardService.updateBoardDataHitCnt(boardDataMap);
			session.setAttribute("hitFlag_" + boardDataMap.get("seq"), "Y");
		}

		/**
		 * 이전글 다음글 조회
		 */
		Map nextPrevMap = adminBoardService.selectBoardDataNextPrevMap(paramMap);

		model.addAttribute("boardInfoMap", boardInfoMap);
		model.addAttribute("boardDataMap", boardDataMap);
		model.addAttribute("nextPrevMap", nextPrevMap);
		model.addAttribute("paramMap", paramMap);

		return "/admin/board/adminBoardModify";
	}

	/**
	 * 게시글 등록 페이지
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/noticeWrite.do", "/admin/board/data/dataWrite.do" })
	public String adminBoardWrite(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("boardSeq", boardInfoMap.get("seq"));

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("boardInfoMap", boardInfoMap);

		return "/admin/board/adminBoardWrite";
	}

	/**
	 * 게시글 등록 동작
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/insertNotice.do", "/admin/board/data/insertData.do" })
	public String insertAdminBoard(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("boardSeq", boardInfoMap.get("seq"));
		paramMap.put("regId", paramMap.get("gsUserId"));

		if (paramMap.get("showWcsrnYn") != null && paramMap.get("showWcsrnYn").equals("Y")) {
			paramMap.put("wcsrnCode", paramMap.get("gsUserWcsrnCode"));
		}

		/**
		 * 게시글 등록
		 */
		int seq = adminBoardService.insertBoardData(paramMap);

		model.addAttribute("location", "/admin/board/" + boardInfoMap.get("boardProgId") + "/" + boardInfoMap.get("boardProgId") + "List.do");
		model.addAttribute("alertMsg", "등록되었습니다.");

		return "messageView";
	}

	/**
	 * 게시글 수정 동작
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/updateNotice.do", "/admin/board/data/updateData.do" })
	public String updateAdminBoard(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("seq", paramMap.get("seq"));
		paramMap.put("boardSeq", boardInfoMap.get("seq"));
		paramMap.put("modId", paramMap.get("userId"));

		/**
		 * 게시글 조회
		 */
		Map boardDataMap = adminBoardService.selectBoardDataMap(paramMap);

		if (boardDataMap == null) {
			model.addAttribute("alertMsg", "게시글이 존재하지 않습니다.");
			return "messageView";
		}

		/**
		 * 게시글 수정
		 */
		adminBoardService.updateBoardData(paramMap);

		String location = "/admin/board/" + boardInfoMap.get("boardProgId") + "/" + boardInfoMap.get("boardProgId") + "View.do?seq=" + boardDataMap.get("seq");

		model.addAttribute("location", location);
		model.addAttribute("alertMsg", "수정되었습니다.");

		return "messageView";
	}

	/**
	 * 게시글 삭제 동작
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/admin/board/notice/deleteNotice.do", "/admin/board/data/deleteData.do" })
	public String deleteAdminBoard(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map boardInfoMap = adminBoardService.selectBoardInfoMap(adminBoardService.getBoardHardParam(request));

		if (boardInfoMap == null) {
			model.addAttribute("alertMsg", "확인되지 않은 게시판 유형입니다.");
			return "messageView";
		}

		Map paramMap = RequestUtil.getRequestMap(request);
		paramMap.put("seq", paramMap.get("seq"));
		paramMap.put("boardSeq", boardInfoMap.get("seq"));
		paramMap.put("modId", paramMap.get("userId"));

		/**
		 * 게시글 조회
		 */
		Map boardDataMap = adminBoardService.selectBoardDataMap(paramMap);

		if (boardDataMap == null) {
			model.addAttribute("alertMsg", "게시글이 존재하지 않습니다.");
			return "messageView";
		}

		/**
		 * 게시글 삭제
		 */
		adminBoardService.deleteBoardData(paramMap);

		model.addAttribute("location", "/admin/board/" + boardInfoMap.get("boardProgId") + "/" + boardInfoMap.get("boardProgId") + "List.do");
		model.addAttribute("alertMsg", "삭제되었습니다.");

		return "messageView";
	}

}