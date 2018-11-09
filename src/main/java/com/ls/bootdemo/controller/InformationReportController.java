package com.ls.bootdemo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ls.bootdemo.common.Const;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 信息报告
 * @author Roger
 *
 */
@Controller
@RequestMapping("/irc")
public class InformationReportController {
	@Autowired
	private PublicTransactionController pController;
	
	//private static String ElcSubAR_ID = "05800000000000005885622";
	private String xml = "";
	private String signature = "";
	
	private static String chanl_cust_no = Const.CHANL_CUST_NO;
	private String date = Const.SYS_REQ_TIME;
	private String date2 = Const.TXN_DT;
	private String date3 = Const.TXN_TM;
	private String ip = Const.Txn_Itt_IP_Adr;
	
	private static String priRsa = Const.LocalPriRsa;
	private static String deS = Const.Des3Key;
	
	/**
	 * 查询多账户余额信息
	 * @throws Exception 
	 */
	@RequestMapping("/checkAccount")
	public void checkAccount() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSER18]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		//sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<ElcSubAR_ID><![CDATA[]]></ElcSubAR_ID>"); //电子合约编号
		sb.append("<Rvl_Rcrd_Num><![CDATA[3]]></Rvl_Rcrd_Num>");	//循环记录条数
		sb.append("<LIST1 type='G'>");
		sb.append("<..Insn_Seq_No><![CDATA[]]></..Insn_Seq_No>");	//指令序列号
		sb.append("<..Cash_Cst_AccNo><![CDATA[6227000132060030594]]></..Cash_Cst_AccNo>");	//现金客户账户
		sb.append("<..CshMgt_Acc_TpCd><![CDATA[1]]></..CshMgt_Acc_TpCd>"); //现金管理账户类型代码
		sb.append("<..CcyCd><![CDATA[156]]></..CcyCd>"); //币种代码
		sb.append("<..BkltNo><![CDATA[]]></..BkltNo>"); //存折册号
		sb.append("<..Dep_DepSeqNo><![CDATA[]]></..Dep_DepSeqNo>"); //存款笔号
		sb.append("</LIST1>");

		sb.append("<ASPD_ECD><![CDATA[00000879]]></ASPD_ECD>");
		sb.append("<SChl_No><![CDATA[000000000000000]]></SChl_No>");
		sb.append("<FwCtl_Node_ID><![CDATA[000000000000000]]></FwCtl_Node_ID>");
		sb.append("<SvM24Hr_Ind><![CDATA[0]]></SvM24Hr_Ind>");
		sb.append("<Tmzon_ECD><![CDATA[08]]></Tmzon_ECD>");
		sb.append("<Cmpt_Ent_ID><![CDATA[0000CN000]]></Cmpt_Ent_ID>");
		sb.append("<CCstTr_ID><![CDATA[CMN0003101838]]></CCstTr_ID>");
		sb.append("<CCstTrNdID><![CDATA[ND75389000000047925600011]]></CCstTrNdID>");


		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	/**
	 * 查询活期/流动性账户历史余额_历史
	 * @throws Exception 
	 */
	@RequestMapping("/checkAccHis")
	public void checkAccountHis() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSER47]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]> </TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]> </TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Rvl_Rcrd_Num><![CDATA[3]]></Rvl_Rcrd_Num>");	//循环记录条数
		sb.append("<LIST1 p_type='G'>");
		sb.append("<..AccNo><![CDATA[6227003604320043309]]></..AccNo>"); //账号
		sb.append("<..CCstTr_ID><![CDATA[]]></..CCstTr_ID>"); //现金客户树编号
		sb.append("<..CCstTrNdID><![CDATA[]]></..CCstTrNdID>"); //现金客户树节点编号
		sb.append("</LIST1>");
		sb.append("<CcyCd><![CDATA[157]]></CcyCd>"); //币种代码
		sb.append("<CshEx_Cd><![CDATA[]]></CshEx_Cd>"); //钞汇代码
		sb.append("<StDt><![CDATA[20180520]]></StDt>"); //开始日期
		sb.append("<EdDt><![CDATA[20181105]]></EdDt>"); //结束日期


		sb.append("<ASPD_ECD><![CDATA[00000879]]></ASPD_ECD>");
		sb.append("<SChl_No><![CDATA[000000000000000]]></SChl_No>");
		sb.append("<FwCtl_Node_ID><![CDATA[000000000000000]]></FwCtl_Node_ID>");
		sb.append("<SvM24Hr_Ind><![CDATA[0]]></SvM24Hr_Ind>");
		sb.append("<Tmzon_ECD><![CDATA[08]]></Tmzon_ECD>");
		sb.append("<Cmpt_Ent_ID><![CDATA[0000CN000]]></Cmpt_Ent_ID>");
		sb.append("<CCstTr_ID><![CDATA[CMN0003101838]]></CCstTr_ID>");
		sb.append("<CCstTrNdID><![CDATA[ND75389000000047925600011]]></CCstTrNdID>");

		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	/**
	 * 查询活期账户明细
	 * @throws Exception 
	 */
	@RequestMapping("/checkAccDet")
	public void checkAccDet() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSER65]]></SYS_TX_CODE>");	//服务名
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
		sb.append("<Rvl_Rcrd_Num><![CDATA[3]]></Rvl_Rcrd_Num>");	//循环记录条数
		sb.append("<LIST1 p_type='G'>");
		sb.append("<..AccNo><![CDATA[]]></..AccNo>"); //账号
		sb.append("<..Beg_Dtl_No><![CDATA[]]></..Beg_Dtl_No>"); //起始明细号
		sb.append("<..End_Dtl_No><![CDATA[]]></..End_Dtl_No>"); //结束明细号
		sb.append("</LIST1>");
		sb.append("<StDt><![CDATA[]]></StDt>"); //开始日期
		sb.append("<EdDt><![CDATA[]]></EdDt>"); //结束日期
		sb.append("<Amt_LwrLmt_Val><![CDATA[]]></Amt_LwrLmt_Val>"); //金额下限值
		sb.append("<ZONE_TP><![CDATA[]]></ZONE_TP>"); //区间类型
		sb.append("<Amt_UpLm_Val><![CDATA[]]></Amt_UpLm_Val>"); //金额上限值
		sb.append("<DbtCrDrcCd><![CDATA[]]></DbtCrDrcCd>"); //借贷方向代码
		sb.append("<Cntrprt_AccNm><![CDATA[]]></Cntrprt_AccNm>"); //对方账户名称
		sb.append("<CntprtAcc><![CDATA[]]></CntprtAcc>"); //对方账号
		sb.append("<Enqr_D tl_TpCd><![CDATA[]]></Enqr_D tl_TpCd"); //查询明细类型代码
		sb.append("<CcyCd><![CDATA[]]></CcyCd>"); //币种代码
		sb.append("<Acrt_Enqr_Ind><![CDATA[]]></Acrt_Enqr_Ind>"); //精确查询标志
		sb.append("<Rmrk><![CDATA[]]></Rmrk>"); //备注
		sb.append("<Rvrs_Txn_Ind><![CDATA[]]></Rvrs_Txn_Ind>"); //冲正交易标志
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");

		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	/**
	 * 查询财资账户明细
	 * @throws Exception 
	 */
	@RequestMapping("/cheTreDet")
	public void cheTreasuryDet() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CMSERB4]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]> </TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA[000001]]> </TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Show_Grp_Lnkg_Txn_Ind><![CDATA[]]></Show_Grp_Lnkg_Txn_Ind>"); //展示集团联动交易标志
		sb.append("<Rvl_Rcrd_Num><![CDATA[]]></Rvl_Rcrd_N um>"); //循环记录条数
		sb.append("<LIST1 p_type='G'>");
		sb.append("<AccNo><![CDATA[]]></..AccNo>"); //账号
		sb.append("<Beg_Dtl_No><![CDATA[]]></Beg_Dtl_No>"); //起始明细号
		sb.append("<End_Dtl_No><![CDATA[]]></End_Dtl_No>"); //结束明细号
		sb.append("<Enqr_StDt><![CDATA[]]></Enqr_StDt>"); //查询起始日期
		sb.append("<Enqr_CODt><![CDATA[]]></Enqr_CODt>"); //查询截止日期
		sb.append("</LIST1>");

		sb.append("<Rvl_Rcrd_Num_1><![CDATA[]]></Rvl_Rcrd_Num _1>"); //循环记录条数
		sb.append("<LIST2 p_type='G'>");
		sb.append("<..Tres_G thr_Cd><![CDATA[]]></..Tres_G thr_Cd>"); //财资归集代码
		sb.append("</LIST2>");

		sb.append("<Rvl_Rcrd_Num_2><![CDATA[]]></Rvl_Rcrd_Num_2>"); //循环记录条数
		sb.append("<LIST3 p_type='G'>");
		sb.append("<..Smy_Cd ><![CDATA[]]></..Smy_Cd >"); //摘要代码
		sb.append("<CcyCd><![CDATA[]]></CcyCd>"); //币种代码
		sb.append("<CshEx_Cd><![CDATA[]]></CshEx_Cd>"); //钞汇代码
		sb.append("<CntprtAcc><![CDATA[]]></CntprtAcc>"); //对方账户
		sb.append("<Cntrprt_AccNm><![CDATA[]]></Cntrprt_AccNm>"); //对方账户名称
		sb.append("<DbtCrDrcCd><![CDATA[]]></DbtCrDrcCd>"); //借贷方向代码
		sb.append("<Amt_LwrLmt_Val><![CDATA[]]></Amt_LwrLmt_Val>"); //金额下限值
		sb.append("<ZONE_TP><![CDATA[]]></ZONE_TP>"); //区间类型
		sb.append("<Amt_UpLm_Val><![CDATA[]]></Amt_UpLm_Val>"); //金额上限值
		sb.append("<Rmrk><![CDATA[]]></Rmrk>"); //备注
		sb.append("</LIST3>");
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
}
