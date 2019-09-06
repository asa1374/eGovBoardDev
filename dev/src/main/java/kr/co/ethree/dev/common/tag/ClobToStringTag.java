package kr.co.ethree.dev.common.tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ClobToStringTag extends SimpleTagSupport {

	private Object value = null;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {

		if (getValue() == null) {
			return;
		}

		StringBuffer strBuffer = new StringBuffer();

		if (getValue() instanceof Clob || getValue() instanceof oracle.sql.CLOB) {
			Reader reader = null;
			BufferedReader buffer = null;

			try {
				String line;
				reader = ((Clob) getValue()).getCharacterStream();
				buffer = new BufferedReader(reader);

				while (null != (line = buffer.readLine())) {
					if ("".equals(line)) {
						continue;
					}

					strBuffer.append(line).append("\n");
				}
			} catch (Exception e) {
				strBuffer.setLength(0);

			} finally {
				try {
					if (null != buffer) {
						buffer.close();
					}
					if (null != reader) {
						reader.close();
					}
				} catch (Exception ce) {

				}
			}

		} else if (getValue() instanceof BigDecimal) {
			strBuffer.append(String.valueOf((BigDecimal) getValue()));

		} else {
			strBuffer.append(String.valueOf(getValue()));
		}

		getJspContext().getOut().write(strBuffer.toString());
	}
}