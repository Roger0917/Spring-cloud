package com.ls.bootdemo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ls.bootdemo.util.Des3Util;
import com.ls.bootdemo.util.ExcLogUtil;
import com.ls.bootdemo.util.HelperUtil;
import com.ls.bootdemo.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/open")
@Slf4j
public class ExchangeRsaController {

	/** 企业客户号 */
	private static String clientNumber = "9020510801";
	private String pubKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2NVDNPSeGtT+CnkroQp17PqsTnt6Iyg+WmtFw+bHMw/xFSjErQ9vJFx8I2AlW5ojAtvwbXWFeFaIOAMoVesJa3YEfF5dcRfEDBFd4LhVPcNN/fn1opr2VzmDR9iC34dTbrbePncrwJYuEy7wLjXc5+c+sq+1YYRAP2SIXaZdIVwIDAQAB";
	private String privateKeyString = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALY1UM09J4a1P4KeSuhCnXs+qxOe3ojKD5aa0XD5sczD/EVKMStD28kXHwjYCVbmiMC2/BtdYV4Vog4AyhV6wlrdgR8Xl1xF8QMEV3guFU9w039+fWimvZXOYNH2ILfh1Nutt4+dyvAli4TLvAuNdzn5z6yr7VhhEA/ZIhdpl0hXAgMBAAECgYAolfunm8WgJeM9rxC0Z5I4j57/wdWD1q5eN6ILbjMCj20z6fDF2Fmuky3ghG6gIr23X4sMdWJo2Jy+TcK1wiBkR+la3DXMxfqAC9n3h0uPel+kRoQ3ErHvGCWpWNgfctn1UOMQQAQd2F36EymsGte0kh5e0VtdMuvYi1MU6bvc4QJBAPgoh9a1Xpt5yTBDghZkdvJfs/TRoM/AaAwfV3/QQtRKk2+NxdLIvhjsCcr7QGVwcyMZamgTSmV60+0TIKmKu68CQQC790iyZ0mIhwsjw6eBl9Mp+edrFBiHCg3pLINum4JLI6egm3nXjvJz87gCr8LDylIVMjEDhi9DJjON2666/B/ZAkEAr7ICesFueiV/i+w/7hqzHGclwe+pK23OPMbz7EpHBobGW/TEWrhF3t8/D7dToH49Bly4OXdu9OpBoVgph9/xgQJAPCwphZMzIxW4JzbXRNFJtEJgJxND1tcNJPwvBxWZXFyJqQycsLj+PMhku9dwTiVSqZ5rumkWcDSmhF8zTICx4QJAFbOtiISVcpuQXli6/oh5T+IFbWSoN0Asdqz8i2bcmgVD+d9TRQCKKI28GkQUx90IZXYUNPyC0P7aGFUlSeCq5w==";
	private String ccbpubKeyRsaString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNL2lEclzJRScUAnI96KFv3A/0XZAUzbxe4ccr3AhP7gHZAgpn/zByYdyMQHhnuTOsTSXRjRpQT/8X/nv7dnSnj1c6fAvgKu593wtTD3cD8Ykdrfe0hmyi1mn/wLSd5Oug4cZLVhVNsHwwtWpzpl8QzJVWHp/fzWvgcSmpa7xyjQIDAQAB";
	private String ccbdesKey = "lJ2M2o+oqNAvB/cWDt/0q5SdjNqPqKjQ";
	@RequestMapping(value = "/pubkey")
	public void httpClientResp(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 要对公钥加密的3des约定密钥
		try {
			String des = clientNumber + HelperUtil.parseDate("yyMMdd", new Date());
			// 约定密钥转换成16进制秘钥数组
			byte[] desKey = asc2bin(des);
			byte[] pubKey = Base64.decodeBase64(pubKeyString);
			System.out.println(Base64.encodeBase64String(pubKey));
			byte[] pubkeyEncrypt = Des3Util.encrypt(pubKey, desKey);
			byte[] head = "000000".getBytes();

			OutputStream outputStream = response.getOutputStream();
			outputStream.write(head);
			outputStream.write(pubkeyEncrypt);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			ExcLogUtil.recordException(e);
		}

	}

	@RequestMapping("/download")
	@ResponseBody
	public String httpClientReq(String type) throws IOException {
		String chanl_cust_no = "SC51000009020510801";
		String des = clientNumber + HelperUtil.parseDate("yyMMdd", new Date());
		//约定密钥转换成16进制秘钥数组
		byte[] desKey = asc2bin(des);
		Map<String, String> map =new HashMap<>();
		map.put("chanl_cust_no", chanl_cust_no);
		map.put("type",type);
		//返回的的公钥字节流
		Map<Integer,InputStream> map2 = HttpUtil.doPost2("http://124.127.94.46:8181/interlink/KeyTransfer", map);
		for (Entry<Integer, InputStream> entry : map2.entrySet()) { 
			if(entry.getKey() == 200){
				InputStream is = entry.getValue();
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int ch;
				while ((ch = is.read(buffer)) != -1) {
					bytestream.write(buffer,0,ch);
				}
				byte data[] = bytestream.toByteArray();
				System.out.println("响应的字节流数组长度"+data.length);
				bytestream.close();
				//加密后公钥base64字符串
				String incodeRsabase = Base64.encodeBase64String(data);
				System.out.println("加密后的base64串或相应失败串"+incodeRsabase);
				String incodeRsabaseRemove = incodeRsabase.replaceAll(Base64.encodeBase64String("000000".getBytes()), "");
				log.info(incodeRsabaseRemove);
				byte[] decodeRsa = Des3Util.DecryptString(incodeRsabaseRemove, desKey);
				log.info("解密后的私钥字节数组长度"+decodeRsa.length);
				String decodeRsabase = Base64.encodeBase64String(decodeRsa);
				log.info(decodeRsabase);
				return decodeRsabase;
			}else{
				log.info("响应码"+entry.getKey());
				InputStream is = entry.getValue();
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int ch;
				while ((ch = is.read(buffer)) != -1) {
					bytestream.write(buffer,0,ch);
				}
				byte data[] = bytestream.toByteArray();
				System.out.println("响应的字节流数组长度"+data.length);
				bytestream.close();
				String incodeRsabase = Base64.encodeBase64String(data);
				return incodeRsabase;
			}
		}
		return null;		
	}
	
	public static void main(String[] args) throws Exception {
		/*String desKey = clientNumber + HelperUtil.parseDate("yyMMdd", new Date());
		Map<String, Object> keypair = RSACoder.initKey();
		String publicKey = keypair.get(RSACoder.PUBLIC_KEY).toString();
		String privateKey = keypair.get(RSACoder.PRIVATE_KEY).toString();
		System.out.println("pub--->" + publicKey);
		System.out.println("pri-->" + privateKey);
		// System.out.println(keypair);
		String prefix = "000000";
		String encryptKey = DESCoder.encrypt(prefix + publicKey, desKey);
		System.out.println("1--->" + encryptKey);
		System.out.println("2--->" + DESCoder.decrypt(encryptKey, desKey));*/
		System.out.println(Base64.encodeBase64String("000000".getBytes()));
	}

	/**
	 * 字符串转换成为16进制(无需Unicode编码)
	 * 
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)
	 * 
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * 16进制字符串转数组
	 * 
	 * @param hexString
	 * @return
	 */
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

}
