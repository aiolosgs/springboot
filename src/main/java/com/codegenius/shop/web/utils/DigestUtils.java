package com.codegenius.shop.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;

public class DigestUtils {

	public static String digest(String text, String alg, String encoding) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		byte[] data = text.getBytes(encoding);
		MessageDigest md = MessageDigest.getInstance(alg);
		return Hex.encodeHexString(md.digest(data));
	}
	
	public static String sha256DigestWithSalt(String password,String salt){
		try{
			return digest(password+salt,"SHA-256","UTF-8");
		}catch(UnsupportedEncodingException e1){
			e1.printStackTrace();
		}catch(NoSuchAlgorithmException e2){
			e2.printStackTrace();
		}
		return null;
	}
	
}
