package com.ls.bootdemo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常量类
 * @author Roger
 *
 */
public class Const {
	public static final String LOCALPUBRSA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2NVDNPSeGtT+CnkroQp17PqsTnt6Iyg+WmtFw+bHMw/xFSjErQ9vJFx8I2AlW5ojAtvwbXWFeFaIOAMoVesJa3YEfF5dcRfEDBFd4LhVPcNN/fn1opr2VzmDR9iC34dTbrbePncrwJYuEy7wLjXc5+c+sq+1YYRAP2SIXaZdIVwIDAQAB";

	public static final String LocalPriRsa = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALY1UM09J4a1P4KeSuhCnXs+qxOe3ojKD5aa0XD5sczD/EVKMStD28kXHwjYCVbmiMC2/BtdYV4Vog4AyhV6wlrdgR8Xl1xF8QMEV3guFU9w039+fWimvZXOYNH2ILfh1Nutt4+dyvAli4TLvAuNdzn5z6yr7VhhEA/ZIhdpl0hXAgMBAAECgYAolfunm8WgJeM9rxC0Z5I4j57/wdWD1q5eN6ILbjMCj20z6fDF2Fmuky3ghG6gIr23X4sMdWJo2Jy+TcK1wiBkR+la3DXMxfqAC9n3h0uPel+kRoQ3ErHvGCWpWNgfctn1UOMQQAQd2F36EymsGte0kh5e0VtdMuvYi1MU6bvc4QJBAPgoh9a1Xpt5yTBDghZkdvJfs/TRoM/AaAwfV3/QQtRKk2+NxdLIvhjsCcr7QGVwcyMZamgTSmV60+0TIKmKu68CQQC790iyZ0mIhwsjw6eBl9Mp+edrFBiHCg3pLINum4JLI6egm3nXjvJz87gCr8LDylIVMjEDhi9DJjON2666/B/ZAkEAr7ICesFueiV/i+w/7hqzHGclwe+pK23OPMbz7EpHBobGW/TEWrhF3t8/D7dToH49Bly4OXdu9OpBoVgph9/xgQJAPCwphZMzIxW4JzbXRNFJtEJgJxND1tcNJPwvBxWZXFyJqQycsLj+PMhku9dwTiVSqZ5rumkWcDSmhF8zTICx4QJAFbOtiISVcpuQXli6/oh5T+IFbWSoN0Asdqz8i2bcmgVD+d9TRQCKKI28GkQUx90IZXYUNPyC0P7aGFUlSeCq5w==";
	
	public static final String CCBPubRsa = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNL2lEclzJRScUAnI96KFv3A/0XZAUzbxe4ccr3AhP7gHZAgpn/zByYdyMQHhnuTOsTSXRjRpQT/8X/nv7dnSnj1c6fAvgKu593wtTD3cD8Ykdrfe0hmyi1mn/wLSd5Oug4cZLVhVNsHwwtWpzpl8QzJVWHp/fzWvgcSmpa7xyjQIDAQAB";
	
	public static final String Des3Key = "lJ2M2o+oqNAvB/cWDt/0q5SdjNqPqKjQ";
	
	/**
	 * 报文头常量
	 */
	public static final String CHANL_CUST_NO = "SC51000009020510801"; //电子银行合约编号
	
	public static String SYS_REQ_TIME = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());  //发起方交易时间
	
	public static String TXN_DT = new SimpleDateFormat("yyyyMMdd").format(new Date());  //交易日期
	
	public static String TXN_TM = SYS_REQ_TIME.substring(8,14); //交易时间
	
	public static String Txn_Itt_IP_Adr = "175.155.75.200"; //交易发起方IP
	

	/**
	 * 报文体常量
	 */
	
	/**	信息报告 * */	
	public static final String AccNo = "51050164860300000409";  //账号
	
	public static final String Rvl_Rcrd_Num = "Rvl_Rcrd_Num"; //循环记录条数
	
	public static final String CCstTr_ID = "CMN0003101838"; //现金客户树编号
	
	public static final String CCstTrNdID = "ND75389000000047925600011"; //现金客户树节点编号
	
	public static final String Cash_Cst_AccNo = ""; //现金客户账号
	
	public static final String CshMgt_Acc_TpCd = ""; //现金管理账户类型代码
	
	public static final String CcyCd = ""; //币种代码
	
	public static final String StDt = ""; //开始日期,查询起始日期 yyyy mm dd
	
	public static final String EdDt = ""; //结束日期,查询截止日期 yyyy mm dd
	
	
	/**	代收代付  * */
	public static final String Txn_SN = ""; //客户序列号
	
	public static final String EBnk_SvAr_ID = ""; //电子银行服务合约编号
	
	public static final String Entrst_Prj_ID = "510130110"; //委托项目编号
	
	public static final String Prj_Use_ID = "";  //项目用途编号
	
	public static final String EtrUnt_AccNo = "51050164860300000409"; //委托单位账号
	
	public static final String TDP_ID = "";  //制单员编号
	
	public static final String CCY_ID = ""; //币种编号
	
	public static final String Rvw_Ind = ""; //按批次复核标志
	
	public static final String VCHR_TP_CODE = "";  //凭证类型

	public static void main(String[] args) {
		System.out.println("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALY1UM09J4a1P4KeSuhCnXs+qxOe3ojKD5aa0XD5sczD/EVKMStD28kXHwjYCVbmiMC2/BtdYV4Vog4AyhV6wlrdgR8Xl1xF8QMEV3guFU9w039+fWimvZXOYNH2ILfh1Nutt4+dyvAli4TLvAuNdzn5z6yr7VhhEA/ZIhdpl0hXAgMBAAECgYAolfunm8WgJeM9rxC0Z5I4j57/wdWD1q5eN6ILbjMCj20z6fDF2Fmuky3ghG6gIr23X4sMdWJo2Jy+TcK1wiBkR+la3DXMxfqAC9n3h0uPel+kRoQ3ErHvGCWpWNgfctn1UOMQQAQd2F36EymsGte0kh5e0VtdMuvYi1MU6bvc4QJBAPgoh9a1Xpt5yTBDghZkdvJfs/TRoM/AaAwfV3/QQtRKk2+NxdLIvhjsCcr7QGVwcyMZamgTSmV60+0TIKmKu68CQQC790iyZ0mIhwsjw6eBl9Mp+edrFBiHCg3pLINum4JLI6egm3nXjvJz87gCr8LDylIVMjEDhi9DJjON2666/B/ZAkEAr7ICesFueiV/i+w/7hqzHGclwe+pK23OPMbz7EpHBobGW/TEWrhF3t8/D7dToH49Bly4OXdu9OpBoVgph9/xgQJAPCwphZMzIxW4JzbXRNFJtEJgJxND1tcNJPwvBxWZXFyJqQycsLj+PMhku9dwTiVSqZ5rumkWcDSmhF8zTICx4QJAFbOtiISVcpuQXli6/oh5T+IFbWSoN0Asdqz8i2bcmgVD+d9TRQCKKI28GkQUx90IZXYUNPyC0P7aGFUlSeCq5w==".equals(LocalPriRsa));
	}
}
