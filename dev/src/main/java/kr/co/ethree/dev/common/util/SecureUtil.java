package kr.co.ethree.dev.common.util;

import java.security.SecureRandom;

/**
 * 암호화 관련 유틸
 */
public class SecureUtil {

	/**
	 * 암호화(단방향, 복호화 불가)
	 *
	 * @param plainText
	 * @site https://seed.kisa.or.kr/index.do
	 * @see KISA_SHA256 > java
	 * @return
	 */
	public String encryptKisaSHA256(String plainText) {

		String encrypted = "";

		try {
			if (plainText != null && plainText.length() > 0) {

				byte pbCipher[] = new byte[32];

				KISA_SHA256.SHA256_Encrpyt(plainText.getBytes(), plainText.length(), pbCipher);

				for (int i = 0; i < pbCipher.length; i++) {
					encrypted += Integer.toHexString(0xff & pbCipher[i]);
				}
			}

		} catch (Exception exception) {
			System.out.println("encryptkisaSha256 exception : " + exception);
		}

		return encrypted.toUpperCase();
	}

	/**
	 * 임시패스워 발급
	 *
	 * @param size : 패스워드 길이
	 * @return
	 */
	public String getTempPassword(int size) {

		StringBuffer buffer = new StringBuffer();
		SecureRandom random = new SecureRandom();

		String tempPassStr = null;

		if (tempPassStr == null || tempPassStr.length() == 0) {
			tempPassStr = "a,b,c,d,e,f,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,2,3,4,5,6,7,8";
		}

		String strArr[] = tempPassStr.split(",");

		for (int i = 0; i < size; i++) {
			buffer.append(strArr[random.nextInt(strArr.length)]);
		}

		return buffer.toString();
	}
}