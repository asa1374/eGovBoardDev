package kr.co.ethree.dev.common.util;

import org.apache.commons.codec.binary.Base64;

public class StringUtil {

	/**
	 * html의 특수문자 원복
	 *
	 * @param srcString
	 * @return String
	 * @exception Exception
	 * @see
	 */
	public static String getHtmlStrCnvr(String srcString) {

		String tmpString = srcString;

		tmpString = tmpString.replaceAll("&lt;", "<");
		tmpString = tmpString.replaceAll("&gt;", ">");
		tmpString = tmpString.replaceAll("&amp;", "&");
		tmpString = tmpString.replaceAll("&nbsp;", " ");
		tmpString = tmpString.replaceAll("&apos;", "\'");
		tmpString = tmpString.replaceAll("&quot;", "\"");

		return tmpString;

	}

	/**
	 * base64 인코딩
	 * 
	 * @param value
	 * @return
	 */
	public static String base64Encode(final String value) {

		String str = "";

		if (value != null && value.length() > 0) {
			str = new String(Base64.encodeBase64(value.getBytes(), false));
		}

		return str;
	}

	/**
	 * base64 디코딩
	 * 
	 * @param value
	 * @return
	 */
	public static String base64Decode(final String value) {

		String str = "";

		if (value != null && value.length() > 0) {
			str = new String(Base64.decodeBase64(value.getBytes()));

			if (str != null && str.length() > 0) {
				str = str.replaceAll("<", "&lt;");
				str = str.replaceAll(">", "&gt;");
			}
		}

		return str;
	}

	/**
	 * 문자를 바이트 단위로 자르기
	 *
	 * @param str
	 * @param cutByte
	 * @param suffix
	 * @return
	 */
	public static String cutByteString(String str, int cutByte, String suffix) {

		if (str == null || str.length() <= 0) {
			return "";
		}

		if (suffix == null || suffix.length() <= 0) {
			suffix = "..";
		}

		char[] charArray = str.toCharArray();
		int strIndex = 0;
		int byteLength = 0;

		for (int len = str.length(); strIndex < len; strIndex++) {
			// 1byte, 2byte character 체크
			int byteSize = charArray[strIndex] < 256 ? 1 : 2;

			if ((byteLength + byteSize) > cutByte) {
				break;
			}

			byteLength += byteSize;
		}

		if (strIndex == str.length()) {
			suffix = "";
		}

		return str.substring(0, strIndex) + suffix;
	}

}
