package com.ls.bootdemo.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;

@Slf4j
public class RsaUtil {

	/**
	 * 随机生成密钥对
	 * @return 
	 */
	public static List  genKeyPair() {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024);
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String privateKeyString = "";
		try {
			// 得到公钥字符串
			System.out.println("公钥字节位数"+publicKey.getEncoded().length);
			String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
			System.out.println("公钥base64字符串"+publicKeyString);
			System.out.println("公钥base64字符串长度"+publicKeyString.length());
			// 得到私钥字符串
			System.out.println("私钥字节位数"+privateKey.getEncoded().length);
			privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
			System.out.println("私钥字符串"+privateKeyString);
			System.out.println("私钥base64字符串长度"+privateKeyString.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List list = new ArrayList();
		list.add(privateKey);
		list.add(privateKeyString);
		return list;
	}
	
	 //用md5生成内容摘要，再用RSA的私钥加密，进而生成数字签名
    public static byte[] getMd5Sign(String content , PrivateKey privateKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(contentBytes);
        byte[] signs = signature.sign();
        return signs;
    }

    //对用md5和RSA私钥生成的数字签名进行验证
   public static boolean verifyWhenMd5Sign(String content, byte[] sign, PublicKey publicKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(contentBytes);
        return signature.verify(sign);
    }

    /**
     * 得到公钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = Base64.decodeBase64(key);

          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PublicKey publicKey = keyFactory.generatePublic(keySpec);
          RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
          return rsaPublicKey;
    }
    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = Base64.decodeBase64(key);

          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
          RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
          return rsaPrivateKey;
    }

	
	public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024);
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String privateKeyString = "";
        // 得到公钥字符串
        System.out.println("公钥字节位数"+publicKey.getEncoded().length);
        String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
        System.out.println("公钥base64字符串"+publicKeyString);
        System.out.println("公钥base64字符串长度"+publicKeyString.length());
        // 得到私钥字符串
        System.out.println("私钥字节位数"+privateKey.getEncoded().length);
        privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println("私钥字符串"+privateKeyString);
        System.out.println("私钥base64字符串长度"+privateKeyString.length());

        RSAPublicKey publicKey1 = getPublicKey(publicKeyString);
        RSAPrivateKey privateKey1 = getPrivateKey(privateKeyString);
        Boolean bo1 = Arrays.equals(publicKey.getEncoded(),publicKey1.getEncoded());
        Boolean bo2 = Arrays.equals(privateKey.getEncoded(),privateKey1.getEncoded());
        log.info(""+bo1+bo2);
    }
}


