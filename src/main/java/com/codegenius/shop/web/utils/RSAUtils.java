package com.codegenius.shop.web.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtils {

	public static final String RSA_PUBLIC_KEY = "RSAPublicKey";
	public static final String RSA_PRIVATE_KEY = "RSAPrivateKey";
	
	public static Map<String,String> generateKey(){
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024, new SecureRandom());
			KeyPair keyPair = generator.generateKeyPair();
			Map<String,String> keyMap = new HashMap<String,String>();
			keyMap.put(RSA_PUBLIC_KEY, new String(Base64.encodeBase64(((RSAPublicKey)keyPair.getPublic()).getEncoded())));
			keyMap.put(RSA_PRIVATE_KEY, new String(Base64.encodeBase64(((RSAPrivateKey)keyPair.getPrivate()).getEncoded())));
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encrypt(String text, String publicKey) throws Exception{
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey key = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(text.getBytes("UTF-8")));
	}
	
	public static String decrypt(String text, String privateKey) throws Exception{
		byte[] cipherByte = Base64.decodeBase64(text.getBytes("UTF-8"));
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey key = (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(cipherByte));
	}
}
