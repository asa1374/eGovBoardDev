package kr.co.ethree.dev.admin.login;

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

import kr.co.ethree.dev.admin.login.service.AdminLoginService;
import kr.co.ethree.dev.common.base.BaseAbstractController;
import kr.co.ethree.dev.common.util.RequestUtil;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminLoginController extends BaseAbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginController.class);

	@Autowired
	private AdminLoginService adminLoginService;

	/**
	 * 로그인 페이지
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/adminAuth.do")
	public String adminAuth(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();

		Map userInfoMap = (Map) session.getAttribute("userInfoMap");

		if (userInfoMap != null && !userInfoMap.isEmpty()) {
			model.addAttribute("alertMsg", "이미 로그인한 계정입니다.");
			model.addAttribute("location", "/main.do");
			return "messageView";
		}

		model.addAttribute("paramMap", RequestUtil.getRequestMap(request));

		return "admin/login/adminAuth";
	}

	/**
	 * 로그인 동작
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Map paramMap = RequestUtil.getRequestMap(request);

		Map userInfoMap = adminLoginService.selectUserInfoMap(paramMap);

		if (userInfoMap == null || userInfoMap.isEmpty()) {
			model.addAttribute("alertMsg", "계정이 존재하지 않습니다.");
			return "messageView";
		}

		HttpSession session = request.getSession();
		session.setAttribute("userInfoMap", userInfoMap);
		session.setMaxInactiveInterval(60 * 30); // 30분

		return "redirect:/main.do";
	}

	/**
	 * 로그아웃 동작
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();

		session.removeAttribute("userInfoMap");
		session.invalidate();

		return "redirect:/main.do";
	}

}