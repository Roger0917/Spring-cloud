package com.ls.bootdemo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;


@Slf4j
public class Des3Util {
	/**
	 * 公钥加密,仅用于密钥交换
	 * @param t
	 * @param bkey
	 * @return
	 */
	public static byte[] encrypt(byte[] t, byte[] bkey) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(bkey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 用密钥初化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			byte[] bResult = cipher.doFinal(t);
			return bResult;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public static void main(String[] args) {
		String des = "9020510801" + "181023";
		// 约定密钥转换成16进制秘钥数组
		byte[] desKey = asc2bin(des);
		byte[] pubKey = RsaUtil.genKeyPair();
		System.out.println(Base64.encodeBase64String(pubKey));

		byte[] pubkeyEncrypt = encrypt(pubKey, desKey);
		System.out.println(pubkeyEncrypt.length);

		System.out.println(Base64.encodeBase64String(pubkeyEncrypt));
		byte[] pubKeyDecrypt = DecryptString(Base64.encodeBase64String(pubkeyEncrypt), desKey);
		System.out.println(Base64.encodeBase64String(pubKeyDecrypt));
	}*/

	public static byte[] asc2bin(String hexString) {
		byte[] hexbyte = hexString.getBytes();
		byte[] bitmap = new byte[hexbyte.length / 2];
		for (int i = 0; i < bitmap.length; i++) {
			hexbyte[i * 2] -= hexbyte[i * 2] > '9' ? 7 : 0;
			hexbyte[i * 2 + 1] -= hexbyte[i * 2 + 1] > '9' ? 7 : 0;
			bitmap[i] = (byte) ((hexbyte[i * 2] << 4 & 0xf0) | (hexbyte[i * 2 + 1] & 0x0f));
		}
		return bitmap;

	}

	/*
	 * 公钥解密
	 */
	public static byte[] DecryptString(String strText, byte[] bkey) {
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = null;
		try {
			desKey = new DESKeySpec(bkey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		// 创建一个密钥工厂
		SecretKeyFactory keyFactory = null;
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey secreKey = null;
		try {
			secreKey = keyFactory.generateSecret(desKey);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		// Cipher对象实际完成解密操作
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		// 用密钥初始化Cipher对象
		try {
			cipher.init(Cipher.DECRYPT_MODE, secreKey, random);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		// 真正开始解密
		// 2、解密前，需要将加密后的字符串从base64转回来再解密，如：
		byte[] encodeByte;
		byte[] b;
		try {
			encodeByte = Base64.decodeBase64(new String(strText.getBytes(), "UTF-8"));
			b = cipher.doFinal(encodeByte);
			return b;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 3DES加密
     * @param key 密钥，24位
     * @param srcStr 将加密的字符串
     * @return
     *
     * lee on 2017-08-09 10:51:44
     */
    public static String  encode3Des(String key,String srcStr){  
    	//byte[] keybyte = hex(key);
    	byte[] keybyte = Base64.decodeBase64(key);
    	log.info("报文加密3des密钥长度"+keybyte.length);
    	byte[] src = srcStr.getBytes();
        try {  
           //生成密钥  
           SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
           //加密  
           Cipher c1 = Cipher.getInstance("DESede");
           c1.init(Cipher.ENCRYPT_MODE, deskey);  
           byte[] pwdByte = c1.doFinal(src);
           log.info("加密后xml字节数组长度"+pwdByte.length);
           String pwd = Base64.encodeBase64String(c1.doFinal(src));
//           return c1.doFinal(src);//在单一方面的加密或解密  
           return pwd;
       } catch (NoSuchAlgorithmException e1) {
           // TODO: handle exception  
            e1.printStackTrace();  
       }catch(NoSuchPaddingException e2){
           e2.printStackTrace();  
       }catch(Exception e3){
           e3.printStackTrace();  
       }  
       return null;  
   }
    
   /**
    * 3DES解密
    * @param key 加密密钥，长度为24字节  
    * @param desStr 解密后的字符串
    * @return
    *
    * lee on 2017-08-09 10:52:54
    */ 
    public static byte[] decode3Des(String key, String desStr){
    	Base64 base64 = new Base64();
    	//byte[] keybyte = hex(key);
    	byte[] keybyte = Base64.decodeBase64(key);
    	byte[] src = base64.decode(desStr);
    			
        try {  
            //生成密钥  
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");  
            //解密  
            Cipher c1 = Cipher.getInstance("DESede");  
            c1.init(Cipher.DECRYPT_MODE, deskey);  
            //String pwd = new String(c1.doFinal(src),"utf-8");
            return c1.doFinal(src);
            //return pwd;
        } catch (NoSuchAlgorithmException e1) {
            // TODO: handle exception  
            e1.printStackTrace();  
        }catch(NoSuchPaddingException e2){
            e2.printStackTrace();  
        }catch(Exception e3){
            e3.printStackTrace();  
        }  
        return null;          
    }


	public static byte[] encrypt2(String key,String str) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    	byte[] keybyte = Base64.decodeBase64(key);
		SecretKey deskey = new SecretKeySpec(keybyte, "DESede"); //用到javax下的SecreKey,传入密钥和加密方式    
		byte[] input =str.getBytes("UTF-8"); //需要字节数组类型    utf-8
		Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding"); //emmm....获得一个Cipher实例
		c1.init(Cipher.ENCRYPT_MODE, deskey); //加载这个加密算法 
		byte[] str1 = c1.doFinal(input);
		return str1;
	}

	public static byte[] decrtpt2(String key,byte[] str1) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
    	byte[] keybyte = Base64.decodeBase64(key);
		SecretKey deskey2 = new SecretKeySpec(keybyte, "DESede");
		Cipher c2 = Cipher.getInstance("DESede");
		c2.init(Cipher.DECRYPT_MODE, deskey2);//加载解密算法
		byte[] str2 = c2.doFinal(str1);//获得解密后的数据
		return  str2;
	}
}