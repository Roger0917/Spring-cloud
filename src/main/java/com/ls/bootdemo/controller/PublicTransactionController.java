package com.ls.bootdemo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.swing.text.Document;

import com.ls.bootdemo.common.Const;
import com.ls.bootdemo.util.Des3Util;
import com.ls.bootdemo.util.HttpUtil;
import com.ls.bootdemo.util.RsaUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 公共交易
 * @author Roger
 *
 */
@Controller
@RequestMapping("/ptc")
public class PublicTransactionController {
	
	private String xml = "";
	private String signature = "";
	
	private static String chanl_cust_no = Const.CHANL_CUST_NO;
	private String date = Const.SYS_REQ_TIME;
	private String date2 = Const.TXN_DT;
	private String date3 = Const.TXN_TM;
	private String ip = Const.Txn_Itt_IP_Adr;
	
	private static String priRsa = Const.LocalPriRsa;
	private static String pubRsa = Const.CCBPubRsa;
	private static String deS = Const.Des3Key;
	/**
	 * 签到接口
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("/in")
	@ResponseBody
	public String signIn() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1OPME001]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]> </TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[333333]]> </TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");

		sb.append("<ASPD_ECD><![CDATA[00000881]]></ASPD_ECD>");
		sb.append("<SChl_No><![CDATA[000000000000000]]></SChl_No>");
		sb.append("<FwCtl_Node_ID><![CDATA[000000000000000]]></FwCtl_Node_ID>");
		sb.append("<SvM_24H_r_Ind><![CDATA[0]]></SvM_24H_r_Ind>");
		sb.append("<Tm_zon_ECD><![CDATA[08]]></Tm_zon_ECD>");
		sb.append("<Cm_pt_Ent_ID><![CDATA[0000CN000]]></Cm_pt_Ent_ID>");
		sb.append("<CC_stTr_ID><![CDATA[CMN0003101838]]></CC_stTr_ID>");
		sb.append("<CC_stTrNdID><![CDATA[ND75389000000047925600011]]></CC_stTrNdID>");

		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		xml = sb.toString();
		System.out.println("xml====>"+xml);
		System.out.println(xml.length()+"---"+xml.getBytes().length);
		String length = "0000000"+xml.length();
		System.out.println("请求报文"+formatXml(xml));
		String respXml = sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
		return respXml;
	}
	
	/**
	 * 签退接口
	 * @throws Exception 
	 */
	@RequestMapping("/out")
	@ResponseBody
	public String signOut() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1OPME002]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]> </TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]> </TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		//sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		xml = sb.toString();

		System.out.println("xml====>"+xml);
		
		System.out.println("请求报文"+formatXml(xml));
		String respXml = sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
		return respXml;
	}
	
	/**
	 * 发送接收报文通用方法
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String sendAndGetXml(String xml) throws Exception{
		//报文签名
				System.out.println("本地私钥串"+priRsa);
				System.out.println("3des密钥串"+deS);
				PrivateKey pKey = RsaUtil.getPrivateKey(priRsa);
				String signature = RsaUtil.getMd5Sign(xml, pKey);
				//报文加解密des密钥
				//报文加密
				xml = Des3Util.encode3Des(deS, xml);
				System.out.println("加密后xml字符串长度"+xml.length());
				Map<String, String> map =new HashMap<>();
				map.put("chanl_cust_no", chanl_cust_no);
				map.put("xml",xml);
				map.put("signature", signature);
				//发送请求报文获得响应报文
				Map<Integer, InputStream> map2 = HttpUtil.doPost2("http://124.127.94.46:8181/interlink/interlink", map);
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
						System.out.println("响应的报文字节数组长度"+data.length);
						bytestream.close();
						//加密后公钥base64字符串
						String incodeRsabase = Base64.encodeBase64String(data);
						System.out.println("响应的报文byte[]串"+incodeRsabase);
						//截取数字签名长度
						byte[] len_byte = new byte[10];
						System.arraycopy(data, 0, len_byte,0, 10);
						String signatureLengthStr = new String(len_byte,"utf-8");
						System.out.println(signatureLengthStr);
						int signatureLength = Integer.parseInt(signatureLengthStr);
						System.out.println("数字签名长度"+signatureLength);
						byte[] signatureByte = new byte[signatureLength];
						//截取数字签名字节数组
						System.arraycopy(data, 10, signatureByte, 0, signatureLength);
						String respSignature = Base64.encodeBase64String(signatureByte);	
						//截取报文密文
						byte[] respxml = new byte[data.length-10-signatureLength];
						System.arraycopy(data, 10+signatureLength, respxml, 0, respxml.length);
						String respXml = Base64.encodeBase64String(respxml);
						//解密
						String decodeRespXml = Des3Util.decode3Des(deS, respXml);
						//验证签名
						PublicKey publicKey = RsaUtil.getPublicKey(pubRsa);
						Boolean verify = RsaUtil.verifyWhenMd5Sign(decodeRespXml, respSignature, publicKey);
						if(verify){
							//验签成功
							//解密响应报文
							return decodeRespXml;
						}else{
							return "验签失败";
						}		
					}else{
						return "响应码不是200,请求失败";
					}
				}
				return null;
				
	}
	
/*	*//**
	 * 文件上传接口
	 *//*
	public void uploadFile(String name,String flag) throws Exception {
		//文件名3des加密
		String filename = Des3Util.encode3Des(deS, filename);
		//文件名做数字签名
		PrivateKey pKey = RsaUtil.getPrivateKey(priRsa);
		String signature = RsaUtil.getMd5Sign(filename, pKey);
		String data = Des3Util.encode3Des(deS, srcStr);
		String dirflag = Des3Util.encode3Des(deS, flag);
		Map<String, String> map =new HashMap<>();
		map.put("chanl_cust_no", chanl_cust_no);
		map.put("filename",filename);
		map.put("signature", signature);
		map.put("data", data);
		map.put("dirflag", dirflag);
		Map<Integer, InputStream> map2 = HttpUtil.doPost2("http://124.127.94.46:8181/interlink/UploadFile", map);
		for (Entry<Integer, InputStream> entry : map2.entrySet()) { 
			if(entry.getKey() == 200){
				InputStream is = entry.getValue();
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int ch;
				while ((ch = is.read(buffer)) != -1) {
					bytestream.write(buffer,0,ch);
				}
				byte resultData[] = bytestream.toByteArray();
				System.out.println("响应的报文字节数组长度"+resultData.length);
				bytestream.close();
				//加密后公钥base64字符串
				String incodeRsabase = Base64.encodeBase64String(resultData);
				System.out.println("响应的报文byte[]串"+incodeRsabase);
				//截取数字签名长度
				byte[] len_byte = new byte[10];
				System.arraycopy(resultData, 0, len_byte,0, 10);
				String signatureLengthStr = Base64.encodeBase64String(len_byte);
				int signatureLength = Integer.parseInt(signatureLengthStr);
				byte[] signatureByte = new byte[signatureLength];
				//截取数字签名字节数组
				System.arraycopy(resultData, 0, signatureByte, 10, 10+signatureLength);
				String respSignature = Base64.encodeBase64String(signatureByte);
				//截取上传结果信息
				byte[] respInfo = new byte[resultData.length-10-signatureLength];
				System.arraycopy(data, 0, respInfo, 10+signatureLength, resultData.length-1);
				String respXml = Base64.encodeBase64String(respInfo);
				
			}
		}
	}
		*/
	
	public String formatXml(String str) throws DocumentException, IOException {
	    SAXReader reader=new SAXReader();
	    StringReader in=new StringReader(str);  
	    org.dom4j.Document doc=reader.read(in);  
	    OutputFormat formater=OutputFormat.createPrettyPrint();  
	    formater.setEncoding("UTF-8");  
	    StringWriter out=new StringWriter();  
	    XMLWriter writer=new XMLWriter(out,formater);  
	    writer.write(doc);  
	    writer.close();  
	    return out.toString();
	}

}