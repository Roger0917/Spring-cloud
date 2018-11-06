/*
package com.ls.bootdemo.controller;

import javax.xml.ws.RequestWrapper;

import com.ls.bootdemo.common.Const;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;



*/
/**
 * 收付款
 * @author Roger
 *
 *//*

@Controller
@RequestMapping("/rpc")
public class ReveivePayController {
	
	@Autowired
	private PublicTransactionController pController;
	
	private String xml = "";
	private String signature = "";
	
	private static String chanl_cust_no = Const.CHANL_CUST_NO;
	private String date = Const.SYS_REQ_TIME;
	private String date2 = Const.TXN_DT;
	private String date3 = Const.TXN_TM;
	private String ip = Const.Txn_Itt_IP_Adr;
	
	private static String priRsa = Const.LocalPriRsa;
	private static String deS = Const.Des3Key;

	*/
/**
	 * 人民币单笔付款
	 *//*

	@RequestMapping("/pay")
	public void pay() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET35]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Pyr_BkCgyCd><![CDATA[]]></Pyr_BkCgyCd>"); //付款方行别代码
		sb.append("<Pyr_Cst_AccNo><![CDATA[]]></Pyr_Cst_AccNo>"); //付款方客户账号
		sb.append("<Pyr_AccNm><![CDATA[]]></Pyr_AccNm>"); //付款方账户名称
		sb.append("<RcvPrt_BkCgyCd><![CDATA[]]></RcvPrt_BkCgyCd>"); //收款方行别代码
		sb.append("<RcvPrt_Cst_AccNo><![CDATA[]]></RcvPrt_Cst_AccNo>"); //收款方客户账户
		sb.append("<RcvPtAc_Nm><![CDATA[]]></RcvPtAc_Nm>"); //收款方账户名称
		sb.append("<Py_Cnd_TpCd><![CDATA[]]></Py_Cnd_TpCd>"); //付款条件类型代码
		sb.append("<RvPy_ExMd_Cd><![CDATA[]]></RvPy_ExMd_Cd>"); //收付款执行方式代码
		sb.append("<Cst_Dlv_Dt><![CDATA[]]></Cst_Dlv_Dt>"); //客户提交日期
		sb.append("<Cst_D lv_Tm><![CDATA[]]></Cst_D lv_Tm>"); //客户提交时间
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
		
	}
	
	*/
/**
	 * 查询人民币单笔付款交易结果信息
	 *//*

	@RequestMapping("/payRes")
	public void payResult() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET36]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 * 人民币财务公司单笔代理付款
	 *//*

	@RequestMapping("/proxyPay")
	public void proxyPay() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET37]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Pyr_BkCgyCd><![CDATA[]]></Pyr_BkCgyCd>"); //付款方行别代码
		sb.append("<Pyr_Cst_AccNo><![CDATA[]]></Pyr_Cst_AccNo>"); //付款方客户账号
		sb.append("<Pyr_AccNm><![CDATA[]]></Pyr_AccNm>"); //付款方账户名称
		sb.append("<Pyr_ACMUnAcNo><![CDATA[]]></Pyr_ACMUnAcNo>"); //付款方财务公司成员单位账号
		sb.append("<Pyr_ACMUnAc_Nm><![CDATA[]]></Pyr_ACM UnAc_Nm>"); //付款方财务公司成员单位账户名称
		sb.append("<RcvPrt_BkCgyCd><![CDATA[]]></RcvPrt_BkCgyCd>"); //收款方行别代码
		sb.append("<RcvPrt_Cst_AccNo><![CDATA[]]></RcvPrt_Cst_AccNo>"); //收款方客户账户
		sb.append("<RcvPtAc_Nm><![CDATA[]]></RcvPtAc_Nm>"); //收款方账户名称
		sb.append("<Py_Cnd_TpCd><![CDATA[]]></Py_Cnd_TpCd>"); //付款条件类型代码
		sb.append("<RvPy_ExM d_Cd><![CDATA[]]></RvPy_ExM d_Cd>"); //收付款执行方式代码
		sb.append("<Cst_Dlv_Dt><![CDATA[]]></Cst_Dlv_Dt>"); //客户提交日期
		sb.append("<Cst_D lv_Tm><![CDATA[]]></Cst_D lv_Tm>"); //客户提交时间
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 * 查询人民币财务公司单笔代理付款交易结果信息
	 *//*

	@RequestMapping("/proxyPayRes")
	public void proxyPayResult() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET38]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 * 外币集团定向式付款申请及审核 
	 *//*

	@RequestMapping("/forDirePay")
	public void forDirePay() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET03]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Cst_Inpt_Dt></Cst_Inpt_Dt>"); //客户录入时期
		sb.append("<Cst_Inpt_Tm></Cst_Inpt_Tm>"); //客户录入时间
		sb.append("<CstPty_TxnSrlNo></CstPty_TxnSrlNo>"); //客户方交易流水号
		sb.append("<CcyCd></CcyCd>"); //币种代码
		sb.append("<RvPy_ExM d_Cd ></RvPy_ExM d_Cd >"); //收付款执行方式代码
		sb.append("<Pyr_Cst_AccN o></Pyr_Cst_AccN o>"); //付款方客户账号
		sb.append("<Pyr_AccN m ></Pyr_AccN m >"); //付款方账户名称
		sb.append("<Pyr_Acc_CgyCd></Pyr_Acc_CgyCd>"); //付款方账户类别代码
		sb.append("<Pyr_BkCgyCd ></Pyr_BkCgyCd >"); //付款方行别代码
		sb.append("<RcvPrt_Cst_AccNo><![CDATA[]]></RcvPrt_Cst_AccNo>"); //收款方客户账户
		sb.append("<RcvPtAc_Nm><![CDATA[]]></RcvPtAc_Nm>"); //收款方账户名称
		sb.append("<RcvPtAc_CgyCd></RcvPtAc_CgyCd>");  //收款方账户类别代码
		sb.append("<RcvPrt_BkCgyCd ></RcvPrt_BkCgyCd >"); //收款方行别代码
		sb.append("<Incm _CcyCd></Incm _CcyCd>"); //收入币种代码
		sb.append("<H pnAm></H pnAm>"); //发生额
		sb.append("<U se_N m ></U se_N m >"); //用途名称
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 * 查询外币单笔付款交易结果信息
	 *//*

	@RequestMapping("/forPayResQ")
	public void forPayResQ() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET04]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 *	查询汇款交易流水信息列表 
	 *//*

	@RequestMapping("/transStreamListQ")
	public void transStreamListQ() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET05]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<EBnk_SvAr_ID></EBnk_SvAr_ID>"); //电子银行服务合约编号
		sb.append("<Enqr_StD t></Enqr_StD t>"); //#查询起始日期
		sb.append("<Enqr_CO D t></Enqr_CO D t>"); //#查询截止日期
		sb.append("<EM P_ELCBN K_SV AR_ID></EM P_ELCBN K_SV AR_ID>"); //任务执行人的电子银行合约编号
		sb.append("<..W F_FCN _ID></..W F_FCN _ID>"); //功能编号
		sb.append("<PD _ID ></PD _ID >"); //产品编号
		sb.append("<..W F_EXTR_ID ></..W F_EXTR_ID >"); //指定执行人
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 *	外币单笔付款交易明细查询 
	 *//*

	@RequestMapping("/transDeaQuery")
	public void transDeaQuery() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET06]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Enqr_StD t></Enqr_StD t>"); //#查询起始日期
		sb.append("<Enqr_CO D t></Enqr_CO D t>"); //#查询截止日期		
		sb.append("<EM P_ELCBN K_SV AR_ID></EM P_ELCBN K_SV AR_ID>"); //任务执行人的电子银行合约编号
		sb.append("<..W F_FCN _ID></..W F_FCN _ID>"); //功能编号
		sb.append("<PD _ID ></PD _ID >"); //产品编号
		sb.append("<..W F_EXTR_ID ></..W F_EXTR_ID >"); //指定执行人
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	*/
/**
	 *	查询外币集团定向式付款交易结果信息
	 *//*

	@RequestMapping("/dirPayResQ")
	public void dirPayResQ() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSET08]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
		
	}
	
	*/
/**
	 *	外币单笔付款流程申请
	 *//*

	@RequestMapping("/appPayProcess")
	public void appPayProcess() throws Exception {
		*/
/**
		 * 拼接报文
		 *//*

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSETA2]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<EBnk_SvAr_ID ></EBnk_SvAr_ID >"); //电子银行服务合约编号
		sb.append("<TD P_N m></TD P_N m>"); //制单员名称
		sb.append("<EM P_ELCBN K_SV AR_ID></EM P_ELCBN K_SV AR_ID>"); //当前任务执行人的电子银行合约编号
		sb.append("<PRO CESS_IN ST_ID></PRO CESS_IN ST_ID>"); //流程实例标识
		sb.append("<TASK_IN ST_ID ></TASK_IN ST_ID >"); //任务实例ID 
		sb.append("<Cst_Inpt_D t ></Cst_Inpt_D t >"); //客户录入日期
		sb.append("<Cst_Inpt_Tm ></Cst_Inpt_Tm >"); //客户录入时间
		sb.append("<CstPty_TxnSrlN o></CstPty_TxnSrlN o>"); //客户方交易流水号
		sb.append("<Rm t_CcyCd ></Rm t_CcyCd >"); //汇款币种代码
		sb.append("<CshEx_Cd></CshEx_Cd>"); //钞汇代码
		sb.append("<FrnCy_Py_O bj_Cd></FrnCy_Py_O bj_Cd>"); //外币付款对象代码
		sb.append("<Rm t_Psn_CorpPrvt_Cd></Rm t_Psn_CorpPrvt_Cd"); //汇款人对公对私代码
		sb.append("<Rm t_Psn_N m ></Rm t_Psn_N m >"); //汇款人名称
		sb.append("<FrnCy_Py_Rm t_Am t></FrnCy_Py_Rm t_Am t>"); //外币付款汇款金额
		sb.append("<StlM e_Cd ></StlM e_Cd >"); //结算方式代码
		sb.append("<M sgRp_Prty_Cd></M sgRp_Prty_Cd>"); //报文优先级代码
		sb.append("<RcvPym tPs_N m ></RcvPym tPs_N m >"); //收款人名称
		sb.append("<FrnCy_RcvPym tPs_AccN o></FrnCy_RcvPym tPs_AccN o>"); //外币收款人账号
		sb.append("<D stAbrdCst_ChrgTo_Cd></D stAbrdCst_ChrgTo_Cd>"); //国内外费用承担代码 
		sb.append("<FrnCy_Py_O rCd></FrnCy_Py_O rCd>"); //#外币付款组织机构代码
		sb.append("<Rvl_Rcrd_N um ></Rvl_Rcrd_N um >"); //循环记录条数
		
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+ pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
}
*/
