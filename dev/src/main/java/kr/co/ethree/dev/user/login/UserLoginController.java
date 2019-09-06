package kr.co.ethree.dev.user.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ethree.dev.common.base.BaseAbstractController;
import kr.co.ethree.dev.common.util.RequestUtil;
import kr.co.ethree.dev.user.login.service.UserLoginService;

@Controller
@SuppressWarnings({"unchecked","rawtypes" })
public class UserLoginController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

	@Autowired UserLoginService userLoginService;
	@Autowired Map<String,Object> map;
	
	@RequestMapping(value="/user/idCheck/{userId}.do")
	public ModelAndView IdCheck(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		ModelAndView mview = new ModelAndView();
		
		int msg = 0;
		Map resultUserId = userLoginService.selectUserId(userId);
		if (resultUserId == null || resultUserId.isEmpty()) {
			msg = 0;
		}else {
			msg = 1;
		}
		mview.addObject("msg", msg);
		mview.setViewName("jsonView");
		
		return mview;
	}
	
	@RequestMapping("/user/signup.do")
	public String SignUp(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Map paramMap = RequestUtil.getRequestMap(request);
		userLoginService.registUser(paramMap);
		return "user/login";
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
	@RequestMapping("/user/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		Map paramMap = RequestUtil.getRequestMap(request);
		
		Map userInfoMap = userLoginService.selectUserInfo(paramMap);

		if (userInfoMap == null || userInfoMap.isEmpty()) {
			model.addAttribute("alertMsg", "아이디 또는 비밀번호를 확인해주세요.");
			return "messageView";
		}

		HttpSession session = request.getSession();
		session.setAttribute("id", userInfoMap.get("userId"));
		session.setAttribute("name", userInfoMap.get("userName"));
		session.setAttribute("auth", userInfoMap.get("userAuth"));
		session.setMaxInactiveInterval(60 * 30); // 30분
		
		if (userInfoMap.get("userAuth").equals("A")) {
			return "redirect:/user/admin/main.do";
		}
		
		return "redirect:/main2.do";
	}
	
	@RequestMapping("/user/delete.do")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("id"));
		
		Map paramMap = new HashMap<String, Object>();
		
		if(session.getAttribute("auth").equals("A")) {
			paramMap.put("userId", request.getParameter("id"));
			userLoginService.deleteUser(paramMap);
			model.addAttribute("location", "/user/admin/deleteList.do");
			model.addAttribute("alertMsg", "추방 완료");
			
			return "messageView";
		}
		
		paramMap.put("userId", session.getAttribute("id"));
		userLoginService.deleteUser(paramMap);
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.invalidate();

		return "redirect:/main2.do";
	}

	 /** 로그아웃 동작
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();

		session.removeAttribute("id");
		session.removeAttribute("name");
		session.invalidate();

		return "redirect:/main2.do";
	}

}