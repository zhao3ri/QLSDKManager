package com.item.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Md5PwdEncoder {

	private String salt = "";

	public String encodePassword(String rawPass) {
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		
		return new String(Hex.encodeHex(digest));
	}

	public boolean isPasswordValid(String encPass, String rawPass) {
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass);
		
		return pass1.equals(pass2);
	}

	protected final MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
		}
	}

	protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
		if (password == null)
			password = "";
		
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1))
				throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
		}
		
		if ((salt == null) || "".equals(salt))
			return password;
		else
			return password + "{" + salt.toString() + "}";
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
