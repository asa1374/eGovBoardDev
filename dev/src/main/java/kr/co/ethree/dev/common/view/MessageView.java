package kr.co.ethree.dev.common.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import egovframework.com.cmm.service.EgovProperties;

@SuppressWarnings({ "rawtypes" })
public class MessageView extends AbstractView {

	public MessageView() {
		setContentType("text/html");
	}

	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = null;

		String alertMsg = (String) model.get("alertMsg");
		String location = (String) model.get("location");

		try {
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			out = response.getWriter();

			out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
			out.write("<html lang=\"ko\">");
			out.write("<head>");
			out.write("<title>" + EgovProperties.getPathProperty("Globals.SiteTitle") + "</title>");
			out.write("<script type=\"text/javascript\">");

			if (alertMsg != null && alertMsg.length() > 0) {
				out.write("alert('");
				out.write(alertMsg);
				out.write("');");
			}

			if (location == null || location.length() == 0) {
				out.write("history.back();");

			} else if (location != null && location.equals("self.close")) {
				out.write("self.close();");

			} else {
				out.write("location.replace('");
				out.write(location);
				out.write("');");
			}

			out.write("</script>");
			out.write("</head>");
			out.write("</html>");

			out.flush();
			out.close();

		} catch (Exception e) {
			logger.error("Message View 처리중 에러", e);

			if (null != out) {
				try {
					out.close();

				} catch (Exception ex) {
					logger.error("out.close Exception : ", e);
				}
			}
		}
	}

}
