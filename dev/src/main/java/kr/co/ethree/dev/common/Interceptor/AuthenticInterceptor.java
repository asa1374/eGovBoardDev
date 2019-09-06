package kr.co.ethree.dev.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("AuthenticInterceptor : " + request.getRequestURI());

		String str = request.getParameter("isSuccess");

		if (str == null || str.length() == 0) {
			return true;

		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/mainPageCheck.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

}
