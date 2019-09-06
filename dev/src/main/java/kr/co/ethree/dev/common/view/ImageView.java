package kr.co.ethree.dev.common.view;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView {

	public static final Logger logger = LoggerFactory.getLogger(ImageView.class);

	/**
	 * Header ContentType 정의
	 */
	public ImageView() {
		this.setContentType("image/jpeg; charset=utf-8");
	}

	/**
	 * 렌더링 재정의
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

		response.setContentType(this.getContentType());
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "filename=" + (String) model.get("fileName") + ";");

		String imagePath = (String) model.get("imagePath");

		FileInputStream fileIn = null;
		ByteArrayOutputStream fileOut = null;
		OutputStream out = null;

		try {
			if (imagePath != null && imagePath.length() > 0) {

				File file = new File(imagePath);

				if (file != null && file.exists()) {

					fileIn = new FileInputStream(file);
					fileOut = new ByteArrayOutputStream();

					byte[] buffer = new byte[1024];

					int bytes_read = 0;
					while ((bytes_read = fileIn.read(buffer)) != -1) {
						fileOut.write(buffer, 0, bytes_read);
					}

					byte[] imgBuf = null;
					imgBuf = fileOut.toByteArray();

					int imgBufLen = imgBuf.length;

					out = response.getOutputStream();
					out.write(imgBuf, 0, imgBufLen);
				}
			}

		} catch (Exception e) {
			logger.info("ImageView Exception");

		} finally {
			try {
				if (fileIn != null) {
					fileIn.close();
				}
				if (fileOut != null) {
					fileOut.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				logger.info("finally close Exception");
			}

		}
	}
}