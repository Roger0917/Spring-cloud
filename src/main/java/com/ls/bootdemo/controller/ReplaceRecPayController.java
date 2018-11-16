package com.ls.bootdemo.controller;

import com.ls.bootdemo.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 代收代付
 * @author Roger
 *
 */
@Controller
@RequestMapping("/rrpc")
public class ReplaceRecPayController {

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
	
	/**
	 * 代发代扣直连交易申请
	 * @throws Exception 
	 */
	@RequestMapping("/apply")
	public void apply() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CLP1051]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[0000000000]]></SYS_MSG_LEN>"); //应用报文长度
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
		sb.append("<Txn_SN><![CDATA[]]></Txn_SN>"); //客户序列号
		sb.append("<EBnk_SvAr_ID><![CDATA["+Const.EBnk_SvAr_ID+"]]></EBnk_SvAr_ID>"); //电子银行服务合约编号
		sb.append("<Entrst_Prj_ID><![CDATA["+Const.Entrst_Prj_ID+"]]></Entrst_Prj_ID>"); //委托项目编号
		sb.append("<Prj_Use_ID><![CDATA["+Const.Prj_Use_ID"]]></Prj_Use_ID>"); //项目用途编号
		sb.append("<EtrUnt_AccNo><![CDATA["+Const.EtrUnt_AccNo+"]]></EtrUnt_AccNo>"); //委托单位账号
		sb.append("<TDP_ID><![CDATA["+Const.TDP_ID+"]]></TDP_ID>"); //制单员编号
		sb.append("<TDP_Nm><![CDATA[]]></TDP_Nm>"); //制单员名称
		sb.append("<CntprtAcc><![CDATA[]]></CntprtAcc>"); //对方账户
		sb.append("<Cntrprt_AccNm><![CDATA[]]></Cntrprt_AccNm>"); //对方账户名称
		sb.append("<IwBk_BrNo><![CDATA[]]></IwBk_BrNo>"); //汇入行行号
		sb.append("<IwBk_BkNm><![CDATA[]]></IwBk_BkNm>"); //汇入行行名
		sb.append("<MltltAgrm_No><![CDATA[]]></MltltAgrm_No>"); //多方协议号
		sb.append("<CCY_ID ><![CDATA["+Const.CCY_ID+"]]></CCY_ID >"); //币种编号
		sb.append("<SRP_TxnAmt><![CDATA[]]></SRP_TxnAm t>"); //代收代付交易金额
		sb.append("<SCSP_Smy_Dsc><![CDATA[]]></SCSP_Sm y_D sc>"); //代收代付摘要描述
		sb.append("<Rvw_Ind><![CDATA[1]]></Rvw_Ind>"); //按批次复核标志
		sb.append("<TAmt><![CDATA[]]></TAmt"); //总金额
		sb.append("<TDnum><![CDATA[]]></TDnum>"); //总笔数
		sb.append("<VCHR_TP_CODE><![CDATA[0]]></VCHR_TP_CODE>"); //凭证类型
		sb.append("<Lng_Vrsn><![CDATA[1]]></Lng_Vrsn>"); //语言版本
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	/**
	 * 代发代扣直连不确定交易查询
	 * @throws Exception 
	 */
	@RequestMapping("/uncertainQuery")
	public void uncertainQuery() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CLP1053]]></SYS_TX_CODE>");	//服务名
		sb.append("<SYS_MSG_LEN><![CDATA[0000000000]]></SYS_MSG_LEN>"); //应用报文长度
		sb.append("<SYS_REQ_TIME><![CDATA["+ date +"]]></SYS_REQ_TIME>"); //发起方交易时间
		sb.append("<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>"); //服务版本号 
		sb.append("<TXN_DT><![CDATA["+ date2 +"]]></TXN_DT>"); //交易日期
		sb.append("<TXN_TM><![CDATA["+ date3 +"]]></TXN_TM>"); //交易时间
		sb.append("<TXN_STFF_ID><![CDATA["+Const.TDP_ID+"]]></TXN_STFF_ID>"); //交易人员编号
		sb.append("<MULTI_TENANCY_ID><![CDATA[CN000]]></MULTI_TENANCY_ID>"); //多实体标识
		sb.append("<LNG_ID><![CDATA[zh-cn]]></LNG_ID>"); //语言标识
		sb.append("<CHNL_CUST_NO><![CDATA["+ chanl_cust_no+ "]]></CHNL_CUST_NO>"); //电子银行合约编号
		//sb.append("<IttParty_Jrnl_No><![CDATA[]]></IttParty_Jrnl_No>"); //发起方流水号
		sb.append("<Txn_Itt_IP_Adr><![CDATA["+ip+"]]></Txn_Itt_IP_Adr>"); //交易发起方IP地址
		sb.append("</Transaction_Header>");
		
		sb.append("<Transaction_Body>");
		sb.append("<request>");
		sb.append("<Txn_SN><![CDATA["+Const.EBnk_SvAr_ID+"]]></Txn_SN>");  //客户序列号
		sb.append("<EBnk_SvAr_ID><![CDATA["+Const.CHANL_CUST_NO+"]]></EBnk_SvAr_ID>");  //电子银行服务合约编号
		sb.append("<VchID><![CDATA[]]></VchID>");  //凭证编号
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
	
	/**
	 * 代发代扣直联单据查询
	 * @throws Exception 
	 */
	@RequestMapping("/receiptQuery")
	public void receiptQuery() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CLP1054]]></SYS_TX_CODE>");	//服务名
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
		sb.append("<Txn_SN><![CDATA[]]></Txn_SN>"); //客户序列号
		sb.append("<EBnk_SvAr_ID><![CDATA["+Const.EBnk_SvAr_ID+"]]></EBnk_SvAr_ID>"); //电子银行服务合约编号
		sb.append("<EtrUnt_AccNo><![CDATA[]]></EtrUnt_AccNo>"); //委托单位账号
		sb.append("<VchID><![CDATA[]]></VchID>"); //凭证编号
		sb.append("<Vchr_StDt><![CDATA[]]></Vchr_StDt >"); //凭证起始日期
		sb.append("<Vchr_CODt><![CDATA[]]></V chr_CODt >"); //凭证截止日期
		sb.append("<VCHR_TP_CODE><![CDATA[]]></VCHR_TP_CODE>"); //凭证类型
		sb.append("<Vchr_St><![CDATA[]]></Vchr_St>"); //凭证状态
		sb.append("<Vchr_Beg_Amt><![CDATA[]]></Vchr_Beg_Amt>"); //凭证起始金额
		sb.append("<Vchr_CtOf_Amt><![CDATA[]]></Vchr_CtOf_Amt>"); //凭证截止金额
		sb.append("<Entrst_Prj_ID><![CDATA[]]></Entrst_Prj_ID>"); //委托项目编号
		sb.append("<TDP_ID><![CDATA[]]></TDP_ID>"); //制单员编号
		sb.append("<SBSTRCVPY_PRJ_TPCD><![CDATA[]]></SBSTRCVPY_PRJ_TPCD >"); //代收代付项目类型代码
		sb.append("<IntrBnk_IndCd><![CDATA[]]></IntrBnk_IndCd>"); //跨行标志代码
		sb.append("</request>");
		sb.append("</Transaction_Body>");
		sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}

    /**
     * 代发代扣直联单据明细查询
     * @throws Exception
     */
	@RequestMapping("/receiptDetQuery")
	public void receiptDetQuery() throws Exception{
		/**
		 * 拼接报文
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<Transaction>");
		sb.append("<Transaction_Header>");
		sb.append("<SYS_TX_CODE><![CDATA[P1CLP1055]]></SYS_TX_CODE>");	//服务名
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
		sb.append("<Txn_SN><![CDATA[]]></Txn_SN>"); //客户序列号
		sb.append("<EBnk_SvAr_ID><![CDATA["+Const.EBnk_SvAr_ID+"]]></EBnk_SvAr_ID>"); //电子银行服务合约编号
		sb.append("<VchID><![CDATA[]]></VchID>"); //凭证编号
        sb.append("</request>");
        sb.append("</Transaction_Body>");
        sb.append("</Transaction>");
		
		xml = sb.toString();
		
		System.out.println("请求报文"+pController.formatXml(xml));
		String respXml = pController.sendAndGetXml(xml);
		System.out.println("响应报文"+respXml);
	}
}
