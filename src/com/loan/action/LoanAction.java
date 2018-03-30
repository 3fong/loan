package com.loan.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.loan.model.LoanInfoBean;
import com.loan.model.LoanInfoSecondaryBean;
import com.loan.model.LoanOrderQueryBean;
import com.loan.model.LoanOrderQuerySecondaryBean;
import com.loan.model.LoanRechargeOrderQueryBean;
import com.loan.model.LoanRechargeReturnBean;
import com.loan.model.LoanRegisterBindReturnBean;
import com.loan.model.LoanReturnInfoBean;
import com.loan.model.LoanTransferReturnBean;
import com.loan.model.LoanWithdrawsOrderQueryBean;
import com.loan.util.Common;
import com.loan.util.HttpClientUtil;
import com.loan.util.MD5;
import com.loan.util.RsaHelper;

public class LoanAction extends BaseAction
{
	// 公共
	private String PlatformMoneymoremore = "";
	private String SubmitURL = "";
	private String SubmitURLPrefix = "http://218.4.234.150:88/main/";
	private String ReturnURL = "";
	private String NotifyURL = "";
	private String SignInfo = "";
	private String ResultCode = "";
	private String Message = "";
	private String ReturnTimes = "";
	private String verifySignature = "";
	private String RandomTimeStamp = "";
	private final int antistate = 0;
	
	// 注册
	private String RegisterType = "";
	private String AccountType = "";
	private String AccountNumber = "";
	private String Mobile = "";
	private String Email = "";
	private String RealName = "";
	private String IdentificationNo = "";
	private String Image1 = "";
	private String Image2 = "";
	private String AuthFee = "";
	private String AuthState = "";
	
	// 绑定
	private String LoanPlatformAccount = "";
	private String LoanMoneymoremore = "";
	private String MoneymoremoreId = "";
	
	// 转账
	private String LoanOutMoneymoremore1 = "";
	private String LoanInMoneymoremore1 = "";
	private String OrderNo1 = "";
	private String BatchNo1 = "";
	private String ExchangeBatchNo1 = "";
	private String AdvanceBatchNo1 = "";
	private String Amount1 = "";
	private String FullAmount1 = "";
	private String TransferName1 = "";
	private String MainRemark1 = "";
	private String Remark1 = "";
	private String LoanInMoneymoremore2 = "";
	private String OrderNo2 = "";
	private String BatchNo2 = "";
	private String Amount2 = "";
	private String FullAmount2 = "";
	private String TransferName2 = "";
	private String MainRemark2 = "";
	private String Remark2 = "";
	private String LoanInMoneymoremore3 = "";
	private String OrderNo3 = "";
	private String BatchNo3 = "";
	private String Amount3 = "";
	private String FullAmount3 = "";
	private String TransferName3 = "";
	private String MainRemark3 = "";
	private String Remark3 = "";
	
	private String SLoanInMoneymoremore1 = "";
	private String SAmount1 = "";
	private String STransferName1 = "";
	private String SRemark1 = "";
	private String SLoanInMoneymoremore2 = "";
	private String SAmount2 = "";
	private String STransferName2 = "";
	private String SRemark2 = "";
	
	private String LoanJsonList = "";
	private String TransferAction = "";
	private String Action = "";
	private String TransferType = "";
	private String NeedAudit = "";
	
	// 余额查询
	private String PlatformId = "";
	private String PlatformType = "";
	private String QueryType = "";
	
	// 充值
	private String RechargeMoneymoremore = "";
	private String OrderNo = "";
	private String Amount = "";
	private String FeePlatform = "";
	private String RechargeType = "";
	private String FeeType = "";
	private String CardNoList = "";
	
	// 提现
	private String WithdrawMoneymoremore = "";
	private String FeePercent = "";
	private String Fee = "";
	private String FreeLimit = "";
	private String FeeMax = "";
	private String FeeWithdraws = "";
	private String FeeRate = "";
	private String FeeQuota = "";
	private String FeeSplitting = "";
	private String CardNo = "";
	private String CardType = "";
	private String BankCode = "";
	private String BranchBankName = "";
	private String Province = "";
	private String City = "";
	
	// 对账
	private String LoanNo = "";
	private String BatchNo = "";
	private String BeginTime = "";
	private String EndTime = "";
	
	// 授权
	private String AuthorizeType = "";
	private String AuthorizeTypeOpen = "";
	private String AuthorizeTypeClose = "";
	
	// 释放
	private String ReleaseType = "";
	
	// 审核
	private String LoanNoList = "";
	private String LoanNoListFail = "";
	private String AuditType = "";
	
	// 三合一
	private String WithholdBeginDate = "";
	private String WithholdEndDate = "";
	private String SingleWithholdLimit = "";
	private String TotalWithholdLimit = "";
	
	// 验证码发送
	private String SendAccount = "";
	private String MobileType = "";
	
	// 姓名匹配
	private String IdentityJsonList = "";
	private String IdentityFailJsonList = "";
	private String RealName2 = "";
	private String IdentificationNo2 = "";
	
	/******************** 测试 开始 ********************/
	
	/**
	 * 生成开户信息
	 */
	public String testLoanRegisterBind()
	{
		try
		{
			RegisterType = "2";
			Mobile = "138" + Common.getRandomNum(8);
			Email = Common.getRandomNum(8) + "@qqq.com";
			RealName = "杨大财";
			IdentificationNo = "320502198001011234";
			LoanPlatformAccount = "天边的云朵" + Common.getRandomNum(6);
			PlatformMoneymoremore = "p1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成开户信息 第二步
	 */
	public String testLoanRegisterBind2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanregisterbind.action";
			ReturnURL = basePath + "loan/testloanregisterbindreturn.action";
			NotifyURL = basePath + "loan/testloanregisterbindnotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = RegisterType + AccountType + Mobile + Email + RealName + IdentificationNo + Image1 + Image2 + LoanPlatformAccount + PlatformMoneymoremore + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			if (RegisterType.equals("1"))
			{
				Map<String, String> req = new TreeMap<String, String>();
				req.put("RegisterType", RegisterType);
				req.put("AccountType", AccountType);
				req.put("Mobile", Mobile);
				req.put("Email", Email);
				req.put("RealName", RealName);
				req.put("IdentificationNo", IdentificationNo);
				req.put("Image1", Image1);
				req.put("Image2", Image2);
				req.put("LoanPlatformAccount", LoanPlatformAccount);
				req.put("PlatformMoneymoremore", PlatformMoneymoremore);
				req.put("RandomTimeStamp", RandomTimeStamp);
				req.put("Remark1", Remark1);
				req.put("Remark2", Remark2);
				req.put("Remark3", Remark3);
				req.put("ReturnURL", ReturnURL);
				req.put("NotifyURL", NotifyURL);
				req.put("SignInfo", SignInfo);
				
				String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
				System.out.println(resultarr[1]);
				
				if (StringUtils.isNotBlank(resultarr[1]) && resultarr[1].startsWith("{"))
				{
					LoanRegisterBindReturnBean lrbrb = (LoanRegisterBindReturnBean) Common.JSONDecode(resultarr[1], LoanRegisterBindReturnBean.class);
					if (lrbrb != null)
					{
						String publickey = Common.publicKey;
						
						dataStr = lrbrb.getAccountType() + lrbrb.getAccountNumber() + lrbrb.getMobile() + lrbrb.getEmail() + lrbrb.getRealName() + lrbrb.getIdentificationNo() + lrbrb.getLoanPlatformAccount() + lrbrb.getMoneymoremoreId() + lrbrb.getPlatformMoneymoremore() + lrbrb.getAuthFee() + lrbrb.getAuthState() + lrbrb.getRandomTimeStamp() + lrbrb.getRemark1() + lrbrb.getRemark2() + lrbrb.getRemark3() + lrbrb.getResultCode();
						if (antistate == 1)
						{
							dataStr = md5.getMD5Info(dataStr);
						}
						// System.out.println(dataStr);
						// 签名
						boolean verifySignature = rsa.verifySignature(lrbrb.getSignInfo(), dataStr, publickey);
						this.verifySignature = Boolean.toString(verifySignature);
						System.out.println(this.verifySignature);
					}
				}
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成批量转账信息
	 */
	public String testLoanTransfer()
	{
		try
		{
			OrderNo1 = Common.getRandomNum(10);
			BatchNo1 = Common.getRandomNum(10);
			Amount1 = "10";
			FullAmount1 = "20";
			TransferName1 = "投标";
			MainRemark1 = "投标备注";
			
			LoanInMoneymoremore2 = "p1";
			OrderNo2 = OrderNo1 + "2";
			BatchNo2 = BatchNo1;
			Amount2 = "2";
			FullAmount2 = "10";
			TransferName2 = "手续费";
			MainRemark2 = "手续费备注";
			
			OrderNo3 = OrderNo1 + "3";
			BatchNo3 = BatchNo1;
			Amount3 = "5";
			FullAmount3 = "10";
			TransferName3 = "红包";
			MainRemark3 = "红包备注";
			
			SLoanInMoneymoremore1 = "";
			SAmount1 = "2";
			STransferName1 = "二次分配1";
			SRemark1 = "二次分配备注1";
			
			SLoanInMoneymoremore2 = "";
			SAmount2 = "4";
			STransferName2 = "二次分配2";
			SRemark2 = "二次分配备注2";
			
			TransferAction = "1";
			Action = "1";
			TransferType = "2";
			NeedAudit = "";
			PlatformMoneymoremore = "p1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成批量转账信息 第二步
	 */
	public String testLoanTransfer2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<LoanInfoSecondaryBean> listmlisb = new ArrayList<LoanInfoSecondaryBean>();
			
			LoanInfoSecondaryBean mlisb = null;
			if (StringUtils.isNotBlank(SLoanInMoneymoremore1))
			{
				mlisb = new LoanInfoSecondaryBean();
				mlisb.setLoanInMoneymoremore(SLoanInMoneymoremore1);
				mlisb.setAmount(SAmount1);
				mlisb.setTransferName(STransferName1);
				mlisb.setRemark(SRemark1);
				listmlisb.add(mlisb);
			}
			
			if (StringUtils.isNotBlank(SLoanInMoneymoremore2))
			{
				mlisb = new LoanInfoSecondaryBean();
				mlisb.setLoanInMoneymoremore(SLoanInMoneymoremore2);
				mlisb.setAmount(SAmount2);
				mlisb.setTransferName(STransferName2);
				mlisb.setRemark(SRemark2);
				listmlisb.add(mlisb);
			}
			
			String SecondaryJsonList = "";
			if (listmlisb.size() > 0)
			{
				SecondaryJsonList = Common.JSONEncode(listmlisb);
			}
			
			List<LoanInfoBean> listmlib = new ArrayList<LoanInfoBean>();
			
			LoanInfoBean mlib = new LoanInfoBean();
			mlib.setLoanOutMoneymoremore(LoanOutMoneymoremore1);
			mlib.setLoanInMoneymoremore(LoanInMoneymoremore1);
			mlib.setOrderNo(OrderNo1);
			mlib.setBatchNo(BatchNo1);
			mlib.setExchangeBatchNo(ExchangeBatchNo1);
			mlib.setAdvanceBatchNo(AdvanceBatchNo1);
			mlib.setAmount(Amount1);
			mlib.setFullAmount(FullAmount1);
			mlib.setTransferName(TransferName1);
			mlib.setRemark(MainRemark1);
			mlib.setSecondaryJsonList(SecondaryJsonList);
			listmlib.add(mlib);
			
			if (StringUtils.isNotBlank(LoanInMoneymoremore2))
			{
				mlib = new LoanInfoBean();
				mlib.setLoanOutMoneymoremore(LoanOutMoneymoremore1);
				mlib.setLoanInMoneymoremore(LoanInMoneymoremore2);
				mlib.setOrderNo(OrderNo2);
				mlib.setBatchNo(BatchNo2);
				mlib.setAmount(Amount2);
				mlib.setFullAmount(FullAmount2);
				mlib.setTransferName(TransferName2);
				mlib.setRemark(MainRemark2);
				// mlib.setSecondaryJsonList(SecondaryJsonList);
				listmlib.add(mlib);
			}
			
			if (StringUtils.isNotBlank(LoanInMoneymoremore3))
			{
				mlib = new LoanInfoBean();
				mlib.setLoanOutMoneymoremore(PlatformMoneymoremore);
				mlib.setLoanInMoneymoremore(LoanInMoneymoremore3);
				mlib.setOrderNo(OrderNo3);
				mlib.setBatchNo(BatchNo3);
				mlib.setAmount(Amount3);
				mlib.setFullAmount(FullAmount3);
				mlib.setTransferName(TransferName3);
				mlib.setRemark(MainRemark3);
				// mlib.setSecondaryJsonList(SecondaryJsonList);
				listmlib.add(mlib);
			}
			
			LoanJsonList = Common.JSONEncode(listmlib);
			
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/loan.action";
			ReturnURL = basePath + "loan/testloantransferreturn.action";
			NotifyURL = basePath + "loan/testloantransfernotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = LoanJsonList + PlatformMoneymoremore + TransferAction + Action + TransferType + NeedAudit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			LoanJsonList = Common.UrlEncoder(LoanJsonList, "utf-8");
			
			if (Action.equals("2"))
			{
				Map<String, String> req = new TreeMap<String, String>();
				req.put("LoanJsonList", LoanJsonList);
				req.put("PlatformMoneymoremore", PlatformMoneymoremore);
				req.put("TransferAction", TransferAction);
				req.put("Action", Action);
				req.put("TransferType", TransferType);
				req.put("NeedAudit", NeedAudit);
				req.put("RandomTimeStamp", RandomTimeStamp);
				req.put("Remark1", Remark1);
				req.put("Remark2", Remark2);
				req.put("Remark3", Remark3);
				req.put("ReturnURL", ReturnURL);
				req.put("NotifyURL", NotifyURL);
				req.put("SignInfo", SignInfo);
				
				String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
				System.out.println(resultarr[1]);
				
				if (StringUtils.isNotBlank(resultarr[1]) && (resultarr[1].startsWith("[") || resultarr[1].startsWith("{")))
				{
					// 转账
					List<Object> loanobjectlist = Common.JSONDecodeList(resultarr[1], LoanTransferReturnBean.class);
					if (loanobjectlist != null && loanobjectlist.size() > 0)
					{
						for (int i = 0; i < loanobjectlist.size(); i++)
						{
							if (loanobjectlist.get(i) instanceof LoanTransferReturnBean)
							{
								LoanTransferReturnBean ltrb = (LoanTransferReturnBean) loanobjectlist.get(i);
								System.out.println(ltrb);
								
								ltrb.setLoanJsonList(Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8"));
								
								String publickey = Common.publicKey;
								
								dataStr = ltrb.getLoanJsonList() + ltrb.getPlatformMoneymoremore() + ltrb.getAction() + ltrb.getRandomTimeStamp() + ltrb.getRemark1() + ltrb.getRemark2() + ltrb.getRemark3() + ltrb.getResultCode();
								if (antistate == 1)
								{
									dataStr = md5.getMD5Info(dataStr);
								}
								
								// 签名
								boolean verifySignature = rsa.verifySignature(ltrb.getSignInfo(), dataStr, publickey);
								this.verifySignature = Boolean.toString(verifySignature);
								System.out.println(this.verifySignature);
								
								if (verifySignature)
								{
									// 转账列表
									if (StringUtils.isNotBlank(ltrb.getLoanJsonList()))
									{
										List<Object> loaninfolist = Common.JSONDecodeList(ltrb.getLoanJsonList(), LoanReturnInfoBean.class);
										if (loaninfolist != null && loaninfolist.size() > 0)
										{
											for (int j = 0; j < loaninfolist.size(); j++)
											{
												if (loaninfolist.get(j) instanceof LoanReturnInfoBean)
												{
													LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist.get(j);
													System.out.println(lrib);
													
													// 二次分配列表
													if (StringUtils.isNotBlank(lrib.getSecondaryJsonList()))
													{
														List<Object> loansecondarylist = Common.JSONDecodeList(lrib.getSecondaryJsonList(), LoanInfoSecondaryBean.class);
														if (loansecondarylist != null && loansecondarylist.size() > 0)
														{
															for (int k = 0; k < loansecondarylist.size(); k++)
															{
																if (loansecondarylist.get(k) instanceof LoanInfoSecondaryBean)
																{
																	LoanInfoSecondaryBean lisb = (LoanInfoSecondaryBean) loansecondarylist.get(k);
																	System.out.println(lisb);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成余额查询信息
	 */
	public String testLoanBalanceQuery()
	{
		try
		{
			PlatformMoneymoremore = "p1";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成余额查询信息 第二步
	 */
	public void testLoanBalanceQuery2()
	{
		try
		{
			SubmitURL = SubmitURLPrefix + "loan/balancequery.action";
			
			String privatekey = Common.privateKeyPKCS8;
			
			String dataStr = PlatformId + PlatformType + QueryType + PlatformMoneymoremore;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			Map<String, String> req = new TreeMap<String, String>();
			req.put("PlatformId", PlatformId);
			req.put("PlatformType", PlatformType);
			req.put("QueryType", QueryType);
			req.put("PlatformMoneymoremore", PlatformMoneymoremore);
			req.put("SignInfo", SignInfo);
			
			String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
			System.out.println(resultarr[1]);
			
			if (StringUtils.isNotBlank(resultarr[1]))
			{
				String[] balance = resultarr[1].split("\\|");
				if (balance != null)
				{
					for (int i = 0; i < balance.length; i++)
					{
						System.out.println(balance[i]);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成充值信息
	 */
	public String testLoanRecharge()
	{
		try
		{
			OrderNo = Common.getRandomNum(10);
			Amount = "10";
			RechargeType = "";
			CardNo = "";
			PlatformMoneymoremore = "p1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成充值信息 第二步
	 */
	public String testLoanRecharge2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanrecharge.action";
			ReturnURL = basePath + "loan/testloanrechargereturn.action";
			NotifyURL = basePath + "loan/testloanrechargenotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			String publickey = Common.publicKey;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore + OrderNo + Amount + RechargeType + FeeType + CardNo + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			if (StringUtils.isNotBlank(CardNo))
			{
				CardNo = rsa.encryptData(CardNo, publickey);
			}
			
			if (RechargeType.equals("1"))
			{
				Map<String, String> req = new TreeMap<String, String>();
				req.put("RechargeMoneymoremore", RechargeMoneymoremore);
				req.put("PlatformMoneymoremore", PlatformMoneymoremore);
				req.put("OrderNo", OrderNo);
				req.put("Amount", Amount);
				req.put("RechargeType", RechargeType);
				req.put("FeeType", FeeType);
				req.put("CardNo", CardNo);
				req.put("RandomTimeStamp", RandomTimeStamp);
				req.put("Remark1", Remark1);
				req.put("Remark2", Remark2);
				req.put("Remark3", Remark3);
				req.put("ReturnURL", ReturnURL);
				req.put("NotifyURL", NotifyURL);
				req.put("SignInfo", SignInfo);
				
				String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
				System.out.println(resultarr[1]);
				
				if (StringUtils.isNotBlank(resultarr[1]) && resultarr[1].startsWith("{"))
				{
					LoanRechargeReturnBean lrrb = (LoanRechargeReturnBean) Common.JSONDecode(resultarr[1], LoanRechargeReturnBean.class);
					if (lrrb != null)
					{
						System.out.println(lrrb);
						
						if (StringUtils.isNotBlank(lrrb.getCardNoList()))
						{
							lrrb.setCardNoList(rsa.decryptData(lrrb.getCardNoList(), privatekey));
							if (StringUtils.isBlank(lrrb.getCardNoList()))
							{
								lrrb.setCardNoList("");
							}
						}
						System.out.println(lrrb.getCardNoList());
						dataStr = lrrb.getRechargeMoneymoremore() + lrrb.getPlatformMoneymoremore() + lrrb.getLoanNo() + lrrb.getOrderNo() + lrrb.getAmount() + lrrb.getFee() + lrrb.getFeePlatform() + lrrb.getRechargeType() + lrrb.getFeeType() + lrrb.getCardNoList() + lrrb.getRandomTimeStamp() + lrrb.getRemark1() + lrrb.getRemark2() + lrrb.getRemark3() + lrrb.getResultCode();
						
						if (antistate == 1)
						{
							dataStr = md5.getMD5Info(dataStr);
						}
						
						// 签名
						boolean verifySignature = rsa.verifySignature(lrrb.getSignInfo(), dataStr, publickey);
						this.verifySignature = Boolean.toString(verifySignature);
						System.out.println(this.verifySignature);
					}
				}
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成提现信息
	 */
	public String testLoanWithdraws()
	{
		try
		{
			OrderNo = Common.getRandomNum(10);
			Amount = "10";
			FeePercent = "50";
			CardNo = "6222123412341234123";
			CardType = "0";
			BankCode = "2";
			BranchBankName = "招商银行苏州支行";
			Province = "10";
			City = "1078";
			PlatformMoneymoremore = "p1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成提现信息 第二步
	 */
	public String testLoanWithdraws2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanwithdraws.action";
			ReturnURL = basePath + "loan/testloanwithdrawsreturn.action";
			NotifyURL = basePath + "loan/testloanwithdrawsnotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			String publickey = Common.publicKey;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore + OrderNo + Amount + FeePercent + FeeMax + FeeRate + FeeQuota + CardNo + CardType + BankCode + BranchBankName + Province + City + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			CardNo = rsa.encryptData(CardNo, publickey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成对账信息
	 */
	public String testLoanOrderQuery()
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();
			EndTime = sdf.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			BeginTime = sdf.format(calendar.getTime());
			PlatformMoneymoremore = "p1";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成对账信息 第二步
	 */
	public void testLoanOrderQuery2()
	{
		try
		{
			SubmitURL = SubmitURLPrefix + "loan/loanorderquery.action";
			
			String privatekey = Common.privateKeyPKCS8;
			
			String dataStr = PlatformMoneymoremore + Action + LoanNo + OrderNo + BatchNo + BeginTime + EndTime;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			Map<String, String> req = new TreeMap<String, String>();
			req.put("PlatformMoneymoremore", PlatformMoneymoremore);
			req.put("Action", Action);
			req.put("LoanNo", LoanNo);
			req.put("OrderNo", OrderNo);
			req.put("BatchNo", BatchNo);
			req.put("BeginTime", BeginTime);
			req.put("EndTime", EndTime);
			req.put("SignInfo", SignInfo);
			
			String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
			System.out.println(resultarr[1]);
			
			if (StringUtils.isNotBlank(resultarr[1]) && (resultarr[1].startsWith("[") || resultarr[1].startsWith("{")))
			{
				if (StringUtils.isBlank(Action))
				{
					// 转账
					List<Object> loanobjectlist = Common.JSONDecodeList(resultarr[1], LoanOrderQueryBean.class);
					if (loanobjectlist != null && loanobjectlist.size() > 0)
					{
						for (int i = 0; i < loanobjectlist.size(); i++)
						{
							if (loanobjectlist.get(i) instanceof LoanOrderQueryBean)
							{
								LoanOrderQueryBean loqb = (LoanOrderQueryBean) loanobjectlist.get(i);
								System.out.println(loqb);
								// 二次分配列表
								if (StringUtils.isNotBlank(loqb.getSecondaryJsonList()))
								{
									List<Object> loansecondarylist = Common.JSONDecodeList(loqb.getSecondaryJsonList(), LoanOrderQuerySecondaryBean.class);
									if (loansecondarylist != null && loansecondarylist.size() > 0)
									{
										for (int j = 0; j < loansecondarylist.size(); j++)
										{
											if (loansecondarylist.get(j) instanceof LoanOrderQuerySecondaryBean)
											{
												LoanOrderQuerySecondaryBean loqsb = (LoanOrderQuerySecondaryBean) loansecondarylist.get(j);
												System.out.println(loqsb);
											}
										}
									}
								}
							}
						}
					}
				}
				else if (Action.equals("1"))
				{
					// 充值
					List<Object> loanobjectlist = Common.JSONDecodeList(resultarr[1], LoanRechargeOrderQueryBean.class);
					if (loanobjectlist != null && loanobjectlist.size() > 0)
					{
						for (int i = 0; i < loanobjectlist.size(); i++)
						{
							if (loanobjectlist.get(i) instanceof LoanRechargeOrderQueryBean)
							{
								LoanRechargeOrderQueryBean lroqb = (LoanRechargeOrderQueryBean) loanobjectlist.get(i);
								System.out.println(lroqb);
							}
						}
					}
				}
				else if (Action.equals("2"))
				{
					// 提现
					List<Object> loanobjectlist = Common.JSONDecodeList(resultarr[1], LoanWithdrawsOrderQueryBean.class);
					if (loanobjectlist != null && loanobjectlist.size() > 0)
					{
						for (int i = 0; i < loanobjectlist.size(); i++)
						{
							if (loanobjectlist.get(i) instanceof LoanWithdrawsOrderQueryBean)
							{
								LoanWithdrawsOrderQueryBean lwoqb = (LoanWithdrawsOrderQueryBean) loanobjectlist.get(i);
								System.out.println(lwoqb);
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成授权信息
	 */
	public String testLoanAuthorize()
	{
		try
		{
			PlatformMoneymoremore = "p1";
			AuthorizeTypeOpen = "1,2,3";
			AuthorizeTypeClose = "";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成授权信息 第二步
	 */
	public String testLoanAuthorize2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanauthorize.action";
			ReturnURL = basePath + "loan/testloanauthorizereturn.action";
			NotifyURL = basePath + "loan/testloanauthorizenotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + AuthorizeTypeClose + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成资金释放信息
	 */
	public String testLoanRelease()
	{
		try
		{
			PlatformMoneymoremore = "p1";
			OrderNo = Common.getRandomNum(10);
			Amount = "10";
			Remark1 = "备注1";
			Remark2 = "备注2";
			Remark3 = "备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成资金释放信息 第二步
	 */
	public String testLoanRelease2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanrelease.action";
			ReturnURL = basePath + "loan/testloanreleasereturn.action";
			NotifyURL = basePath + "loan/testloanreleasenotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + OrderNo + Amount + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成审核信息
	 */
	public String testLoanTransferAudit()
	{
		try
		{
			PlatformMoneymoremore = "p1";
			AuditType = "1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成审核信息 第二步
	 */
	public String testLoanTransferAudit2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloantransferaudit.action";
			ReturnURL = basePath + "loan/testloantransferauditreturn.action";
			NotifyURL = basePath + "loan/testloantransferauditnotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = LoanNoList + PlatformMoneymoremore + AuditType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成三合一信息
	 */
	public String testLoanFastPay()
	{
		try
		{
			PlatformMoneymoremore = "p1";
			Action = "1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成三合一信息 第二步
	 */
	public String testLoanFastPay2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloanfastpay.action";
			ReturnURL = basePath + "loan/testloanfastpayreturn.action";
			NotifyURL = basePath + "loan/testloanfastpaynotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			String publickey = Common.publicKey;
			
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + Action + CardNo + WithholdBeginDate + WithholdEndDate + SingleWithholdLimit + TotalWithholdLimit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			if (StringUtils.isNotBlank(CardNo))
			{
				CardNo = rsa.encryptData(CardNo, publickey);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成验证码发送信息
	 */
	@SuppressWarnings("unchecked")
	public String testLoanSendVerifyCode()
	{
		try
		{
			SendAccount = "138" + Common.getRandomNum(8);
			MobileType="1";	
			PlatformMoneymoremore = "p1";
			Remark1 = "自定义备注1";
			Remark2 = "自定义备注2";
			Remark3 = "自定义备注3";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成验证码发送信息 第二步
	 */
	public void testLoanSendVerifyCode2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "loan/toloansendverifycode.action";
			NotifyURL = basePath + "loan/testloansendverifycodenotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = SendAccount + MobileType + PlatformMoneymoremore + RandomTimeStamp + Remark1 + Remark2 + Remark3 + NotifyURL;
			// System.out.println(dataStr);
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			Map<String, String> req = new TreeMap<String, String>();
			req.put("SendAccount", SendAccount);
			req.put("MobileType", MobileType);
			req.put("PlatformMoneymoremore", PlatformMoneymoremore);
			req.put("RandomTimeStamp", RandomTimeStamp);
			req.put("Remark1", Remark1);
			req.put("Remark2", Remark2);
			req.put("Remark3", Remark3);
			req.put("NotifyURL", NotifyURL);
			req.put("SignInfo", SignInfo);
			
			String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
			System.out.println(resultarr[1]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成姓名匹配信息
	 */
	public String testIdentityMatching()
	{
		try
		{
			PlatformMoneymoremore = "p1";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 生成姓名匹配信息 第二步
	 */
	public void testIdentityMatching2()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<Map<String, String>> listidentity = new ArrayList<Map<String, String>>();
			if (StringUtils.isNotBlank(RealName) && StringUtils.isNotBlank(IdentificationNo))
			{
				Map<String, String> maptemp = new TreeMap<String, String>();
				maptemp.put("realName", RealName);
				maptemp.put("identificationNo", IdentificationNo);
				listidentity.add(maptemp);
			}
			if (StringUtils.isNotBlank(RealName2) && StringUtils.isNotBlank(IdentificationNo2))
			{
				Map<String, String> maptemp = new TreeMap<String, String>();
				maptemp.put("realName", RealName2);
				maptemp.put("identificationNo", IdentificationNo2);
				listidentity.add(maptemp);
			}
			
			IdentityJsonList = Common.JSONEncode(listidentity);
			
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			SubmitURL = SubmitURLPrefix + "authentication/identityMatching.action";
			NotifyURL = basePath + "loan/testidentitymatchingnotify.action";
			
			String privatekey = Common.privateKeyPKCS8;
			
			if (antistate == 1)
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				RandomTimeStamp = Common.getRandomNum(2) + sdf.format(d);
			}
			
			String dataStr = PlatformMoneymoremore + IdentityJsonList +RandomTimeStamp + NotifyURL;
			// 签名
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			SignInfo = rsa.signData(dataStr, privatekey);
			
			IdentityJsonList = Common.UrlEncoder(IdentityJsonList, "utf-8");
			
			Map<String, String> req = new TreeMap<String, String>();
			req.put("PlatformMoneymoremore", PlatformMoneymoremore);
			req.put("IdentityJsonList", IdentityJsonList);
			req.put("RandomTimeStamp", RandomTimeStamp);
			// req.put("Remark1", Remark1);
			// req.put("Remark2", Remark2);
			// req.put("Remark3", Remark3);
			req.put("NotifyURL", NotifyURL);
			req.put("SignInfo", SignInfo);
			
			String[] resultarr = HttpClientUtil.doPostQueryCmd(SubmitURL, req);
			System.out.println(resultarr[1]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收开户页面返回信息
	 * 
	 * @return
	 */
	public String testLoanRegisterBindReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = AccountType + AccountNumber + Mobile + Email + RealName + IdentificationNo + LoanPlatformAccount + MoneymoremoreId + PlatformMoneymoremore + AuthFee + AuthState + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收开户后台通知信息
	 * 
	 * @return
	 */
	public void testLoanRegisterBindNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = AccountType + AccountNumber + Mobile + Email + RealName + IdentificationNo + LoanPlatformAccount + MoneymoremoreId + PlatformMoneymoremore + AuthFee + AuthState + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收转账页面返回信息
	 * 
	 * @return
	 */
	public String testLoanTransferReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			if (Action == null)
			{
				Action = "";
			}
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收转账后台通知信息
	 * 
	 * @return
	 */
	public void testLoanTransferNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			LoanJsonList = Common.UrlDecoder(LoanJsonList, "utf-8");
			if (Action == null)
			{
				Action = "";
			}
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收充值页面返回信息
	 * 
	 * @return
	 */
	public String testLoanRechargeReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			String privatekey = Common.privateKeyPKCS8;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			
			if (StringUtils.isNotBlank(CardNoList))
			{
				CardNoList = rsa.decryptData(CardNoList, privatekey);
				if (StringUtils.isBlank(CardNoList))
				{
					CardNoList = "";
				}
			}
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + Fee + FeePlatform + RechargeType + FeeType + CardNoList + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收充值后台通知信息
	 * 
	 * @return
	 */
	public void testLoanRechargeNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			String privatekey = Common.privateKeyPKCS8;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			
			if (StringUtils.isNotBlank(CardNoList))
			{
				CardNoList = rsa.decryptData(CardNoList, privatekey);
				if (StringUtils.isBlank(CardNoList))
				{
					CardNoList = "";
				}
			}
			String dataStr = RechargeMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + Fee + FeePlatform + RechargeType + FeeType + CardNoList + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收提现页面返回信息
	 * 
	 * @return
	 */
	public String testLoanWithdrawsReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws + FeePercent + Fee + FreeLimit + FeeRate + FeeSplitting + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收提现后台通知信息
	 * 
	 * @return
	 */
	public void testLoanWithdrawsNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = WithdrawMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws + FeePercent + Fee + FreeLimit + FeeRate + FeeSplitting + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收授权页面返回信息
	 * 
	 * @return
	 */
	public String testLoanAuthorizeReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收授权后台通知信息
	 * 
	 * @return
	 */
	public void testLoanAuthorizeNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收资金释放页面返回信息
	 * 
	 * @return
	 */
	public String testLoanReleaseReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + LoanNo + OrderNo + Amount + ReleaseType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收资金释放后台通知信息
	 * 
	 * @return
	 */
	public void testLoanReleaseNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + LoanNo + OrderNo + Amount + ReleaseType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收审核页面返回信息
	 * 
	 * @return
	 */
	public String testLoanTransferAuditReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail + PlatformMoneymoremore + AuditType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收审核后台通知信息
	 * 
	 * @return
	 */
	public void testLoanTransferAuditNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = LoanNoList + LoanNoListFail + PlatformMoneymoremore + AuditType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收三合一页面返回信息
	 * 
	 * @return
	 */
	public String testLoanFastPayReturn()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			String privatekey = Common.privateKeyPKCS8;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			
			if (StringUtils.isNotBlank(CardNo))
			{
				CardNo = rsa.decryptData(CardNo, privatekey);
				if (StringUtils.isBlank(CardNo))
				{
					CardNo = "";
				}
			}
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + Action + CardType + BankCode + CardNo + BranchBankName + Province + City + WithholdBeginDate + WithholdEndDate + SingleWithholdLimit + TotalWithholdLimit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			this.verifySignature = Boolean.toString(verifySignature);
			System.out.println("页面返回:" + this.verifySignature);
			System.out.println("返回码:" + ResultCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 接收三合一后台通知信息
	 * 
	 * @return
	 */
	public void testLoanFastPayNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			String privatekey = Common.privateKeyPKCS8;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			
			if (StringUtils.isNotBlank(CardNo))
			{
				CardNo = rsa.decryptData(CardNo, privatekey);
				if (StringUtils.isBlank(CardNo))
				{
					CardNo = "";
				}
			}
			String dataStr = MoneymoremoreId + PlatformMoneymoremore + Action + CardType + BankCode + CardNo + BranchBankName + Province + City + WithholdBeginDate + WithholdEndDate + SingleWithholdLimit + TotalWithholdLimit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收验证码发送后台通知信息
	 * 
	 * @return
	 */
	public void testLoanSendVerifyCodeNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			String dataStr = SendAccount + MobileType + PlatformMoneymoremore + Fee + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// System.out.println(dataStr);
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收姓名匹配后台通知信息
	 * 
	 * @return
	 */
	public void testIdentityMatchingNotify()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String publickey = Common.publicKey;
			
			IdentityJsonList = Common.UrlDecoder(IdentityJsonList, "utf-8");
			IdentityFailJsonList = Common.UrlDecoder(IdentityFailJsonList, "utf-8");
			
			MD5 md5 = new MD5();
			RsaHelper rsa = RsaHelper.getInstance();
			
			String dataStr = PlatformMoneymoremore + IdentityJsonList + IdentityFailJsonList + RandomTimeStamp + Amount + ResultCode;
			if (antistate == 1)
			{
				dataStr = md5.getMD5Info(dataStr);
			}
			
			// 签名
			boolean verifySignature = rsa.verifySignature(SignInfo, dataStr, publickey);
			System.out.println("后台通知:" + verifySignature);
			System.out.println("返回码:" + ResultCode);
			System.out.println("返回次数:" + ReturnTimes);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/******************** 测试 结束 ********************/
	
	/******************** 分隔 ********************/
	
	public String getPlatformMoneymoremore()
	{
		return PlatformMoneymoremore;
	}
	
	public void setPlatformMoneymoremore(String platformMoneymoremore)
	{
		PlatformMoneymoremore = platformMoneymoremore;
	}
	
	public String getReturnURL()
	{
		return ReturnURL;
	}
	
	public void setReturnURL(String returnURL)
	{
		ReturnURL = returnURL;
	}
	
	public String getNotifyURL()
	{
		return NotifyURL;
	}
	
	public void setNotifyURL(String notifyURL)
	{
		NotifyURL = notifyURL;
	}
	
	public String getSignInfo()
	{
		return SignInfo;
	}
	
	public void setSignInfo(String signInfo)
	{
		SignInfo = signInfo;
	}
	
	public String getRegisterType()
	{
		return RegisterType;
	}
	
	public void setRegisterType(String registerType)
	{
		RegisterType = registerType;
	}
	
	public String getMobile()
	{
		return Mobile;
	}
	
	public void setMobile(String mobile)
	{
		Mobile = mobile;
	}
	
	public String getEmail()
	{
		return Email;
	}
	
	public void setEmail(String email)
	{
		Email = email;
	}
	
	public String getRealName()
	{
		return RealName;
	}
	
	public void setRealName(String realName)
	{
		RealName = realName;
	}
	
	public String getIdentificationNo()
	{
		return IdentificationNo;
	}
	
	public void setIdentificationNo(String identificationNo)
	{
		IdentificationNo = identificationNo;
	}
	
	public String getImage1()
	{
		return Image1;
	}
	
	public void setImage1(String image1)
	{
		Image1 = image1;
	}
	
	public String getImage2()
	{
		return Image2;
	}
	
	public void setImage2(String image2)
	{
		Image2 = image2;
	}
	
	public String getLoanPlatformAccount()
	{
		return LoanPlatformAccount;
	}
	
	public void setLoanPlatformAccount(String loanPlatformAccount)
	{
		LoanPlatformAccount = loanPlatformAccount;
	}
	
	public String getLoanMoneymoremore()
	{
		return LoanMoneymoremore;
	}
	
	public void setLoanMoneymoremore(String loanMoneymoremore)
	{
		LoanMoneymoremore = loanMoneymoremore;
	}
	
	public String getLoanOutMoneymoremore1()
	{
		return LoanOutMoneymoremore1;
	}
	
	public void setLoanOutMoneymoremore1(String loanOutMoneymoremore1)
	{
		LoanOutMoneymoremore1 = loanOutMoneymoremore1;
	}
	
	public String getLoanInMoneymoremore1()
	{
		return LoanInMoneymoremore1;
	}
	
	public void setLoanInMoneymoremore1(String loanInMoneymoremore1)
	{
		LoanInMoneymoremore1 = loanInMoneymoremore1;
	}
	
	public String getOrderNo1()
	{
		return OrderNo1;
	}
	
	public void setOrderNo1(String orderNo1)
	{
		OrderNo1 = orderNo1;
	}
	
	public String getBatchNo1()
	{
		return BatchNo1;
	}
	
	public void setBatchNo1(String batchNo1)
	{
		BatchNo1 = batchNo1;
	}
	
	public String getAmount1()
	{
		return Amount1;
	}
	
	public void setAmount1(String amount1)
	{
		Amount1 = amount1;
	}
	
	public String getTransferName1()
	{
		return TransferName1;
	}
	
	public void setTransferName1(String transferName1)
	{
		TransferName1 = transferName1;
	}
	
	public String getRemark1()
	{
		return Remark1;
	}
	
	public void setRemark1(String remark1)
	{
		Remark1 = remark1;
	}
	
	public String getLoanInMoneymoremore2()
	{
		return LoanInMoneymoremore2;
	}
	
	public void setLoanInMoneymoremore2(String loanInMoneymoremore2)
	{
		LoanInMoneymoremore2 = loanInMoneymoremore2;
	}
	
	public String getOrderNo2()
	{
		return OrderNo2;
	}
	
	public void setOrderNo2(String orderNo2)
	{
		OrderNo2 = orderNo2;
	}
	
	public String getBatchNo2()
	{
		return BatchNo2;
	}
	
	public void setBatchNo2(String batchNo2)
	{
		BatchNo2 = batchNo2;
	}
	
	public String getAmount2()
	{
		return Amount2;
	}
	
	public void setAmount2(String amount2)
	{
		Amount2 = amount2;
	}
	
	public String getTransferName2()
	{
		return TransferName2;
	}
	
	public void setTransferName2(String transferName2)
	{
		TransferName2 = transferName2;
	}
	
	public String getRemark2()
	{
		return Remark2;
	}
	
	public void setRemark2(String remark2)
	{
		Remark2 = remark2;
	}
	
	public String getLoanJsonList()
	{
		return LoanJsonList;
	}
	
	public void setLoanJsonList(String loanJsonList)
	{
		LoanJsonList = loanJsonList;
	}
	
	public String getTransferAction()
	{
		return TransferAction;
	}
	
	public void setTransferAction(String transferAction)
	{
		TransferAction = transferAction;
	}
	
	public String getAction()
	{
		return Action;
	}
	
	public void setAction(String action)
	{
		Action = action;
	}
	
	public String getTransferType()
	{
		return TransferType;
	}
	
	public void setTransferType(String transferType)
	{
		TransferType = transferType;
	}
	
	public String getPlatformId()
	{
		return PlatformId;
	}
	
	public void setPlatformId(String platformId)
	{
		PlatformId = platformId;
	}
	
	public String getPlatformType()
	{
		return PlatformType;
	}
	
	public void setPlatformType(String platformType)
	{
		PlatformType = platformType;
	}
	
	public String getRechargeMoneymoremore()
	{
		return RechargeMoneymoremore;
	}
	
	public void setRechargeMoneymoremore(String rechargeMoneymoremore)
	{
		RechargeMoneymoremore = rechargeMoneymoremore;
	}
	
	public String getOrderNo()
	{
		return OrderNo;
	}
	
	public void setOrderNo(String orderNo)
	{
		OrderNo = orderNo;
	}
	
	public String getAmount()
	{
		return Amount;
	}
	
	public void setAmount(String amount)
	{
		Amount = amount;
	}
	
	public String getWithdrawMoneymoremore()
	{
		return WithdrawMoneymoremore;
	}
	
	public void setWithdrawMoneymoremore(String withdrawMoneymoremore)
	{
		WithdrawMoneymoremore = withdrawMoneymoremore;
	}
	
	public String getFeePercent()
	{
		return FeePercent;
	}
	
	public void setFeePercent(String feePercent)
	{
		FeePercent = feePercent;
	}
	
	public String getCardNo()
	{
		return CardNo;
	}
	
	public void setCardNo(String cardNo)
	{
		CardNo = cardNo;
	}
	
	public String getCardType()
	{
		return CardType;
	}
	
	public void setCardType(String cardType)
	{
		CardType = cardType;
	}
	
	public String getBankCode()
	{
		return BankCode;
	}
	
	public void setBankCode(String bankCode)
	{
		BankCode = bankCode;
	}
	
	public String getBranchBankName()
	{
		return BranchBankName;
	}
	
	public void setBranchBankName(String branchBankName)
	{
		BranchBankName = branchBankName;
	}
	
	public String getProvince()
	{
		return Province;
	}
	
	public void setProvince(String province)
	{
		Province = province;
	}
	
	public String getCity()
	{
		return City;
	}
	
	public void setCity(String city)
	{
		City = city;
	}
	
	public String getLoanNo()
	{
		return LoanNo;
	}
	
	public void setLoanNo(String loanNo)
	{
		LoanNo = loanNo;
	}
	
	public String getBatchNo()
	{
		return BatchNo;
	}
	
	public void setBatchNo(String batchNo)
	{
		BatchNo = batchNo;
	}
	
	public String getBeginTime()
	{
		return BeginTime;
	}
	
	public void setBeginTime(String beginTime)
	{
		BeginTime = beginTime;
	}
	
	public String getEndTime()
	{
		return EndTime;
	}
	
	public void setEndTime(String endTime)
	{
		EndTime = endTime;
	}
	
	public String getFullAmount1()
	{
		return FullAmount1;
	}
	
	public void setFullAmount1(String fullAmount1)
	{
		FullAmount1 = fullAmount1;
	}
	
	public String getFullAmount2()
	{
		return FullAmount2;
	}
	
	public void setFullAmount2(String fullAmount2)
	{
		FullAmount2 = fullAmount2;
	}
	
	public String getSLoanInMoneymoremore1()
	{
		return SLoanInMoneymoremore1;
	}
	
	public void setSLoanInMoneymoremore1(String sLoanInMoneymoremore1)
	{
		SLoanInMoneymoremore1 = sLoanInMoneymoremore1;
	}
	
	public String getSAmount1()
	{
		return SAmount1;
	}
	
	public void setSAmount1(String sAmount1)
	{
		SAmount1 = sAmount1;
	}
	
	public String getSTransferName1()
	{
		return STransferName1;
	}
	
	public void setSTransferName1(String sTransferName1)
	{
		STransferName1 = sTransferName1;
	}
	
	public String getSRemark1()
	{
		return SRemark1;
	}
	
	public void setSRemark1(String sRemark1)
	{
		SRemark1 = sRemark1;
	}
	
	public String getSLoanInMoneymoremore2()
	{
		return SLoanInMoneymoremore2;
	}
	
	public void setSLoanInMoneymoremore2(String sLoanInMoneymoremore2)
	{
		SLoanInMoneymoremore2 = sLoanInMoneymoremore2;
	}
	
	public String getSAmount2()
	{
		return SAmount2;
	}
	
	public void setSAmount2(String sAmount2)
	{
		SAmount2 = sAmount2;
	}
	
	public String getSTransferName2()
	{
		return STransferName2;
	}
	
	public void setSTransferName2(String sTransferName2)
	{
		STransferName2 = sTransferName2;
	}
	
	public String getSRemark2()
	{
		return SRemark2;
	}
	
	public void setSRemark2(String sRemark2)
	{
		SRemark2 = sRemark2;
	}
	
	public String getVerifySignature()
	{
		return verifySignature;
	}
	
	public void setVerifySignature(String verifySignature)
	{
		this.verifySignature = verifySignature;
	}
	
	public String getAccountNumber()
	{
		return AccountNumber;
	}
	
	public void setAccountNumber(String accountNumber)
	{
		AccountNumber = accountNumber;
	}
	
	public String getMoneymoremoreId()
	{
		return MoneymoremoreId;
	}
	
	public void setMoneymoremoreId(String moneymoremoreId)
	{
		MoneymoremoreId = moneymoremoreId;
	}
	
	public String getResultCode()
	{
		return ResultCode;
	}
	
	public void setResultCode(String resultCode)
	{
		ResultCode = resultCode;
	}
	
	public String getFee()
	{
		return Fee;
	}
	
	public void setFee(String fee)
	{
		Fee = fee;
	}
	
	public String getFreeLimit()
	{
		return FreeLimit;
	}
	
	public void setFreeLimit(String freeLimit)
	{
		FreeLimit = freeLimit;
	}
	
	public String getSubmitURL()
	{
		return SubmitURL;
	}
	
	public void setSubmitURL(String submitURL)
	{
		SubmitURL = submitURL;
	}
	
	public String getSubmitURLPrefix()
	{
		return SubmitURLPrefix;
	}
	
	public void setSubmitURLPrefix(String submitURLPrefix)
	{
		SubmitURLPrefix = submitURLPrefix;
	}
	
	public String getAuthorizeType()
	{
		return AuthorizeType;
	}
	
	public void setAuthorizeType(String authorizeType)
	{
		AuthorizeType = authorizeType;
	}
	
	public String getAuthorizeTypeOpen()
	{
		return AuthorizeTypeOpen;
	}
	
	public void setAuthorizeTypeOpen(String authorizeTypeOpen)
	{
		AuthorizeTypeOpen = authorizeTypeOpen;
	}
	
	public String getAuthorizeTypeClose()
	{
		return AuthorizeTypeClose;
	}
	
	public void setAuthorizeTypeClose(String authorizeTypeClose)
	{
		AuthorizeTypeClose = authorizeTypeClose;
	}
	
	public String getLoanInMoneymoremore3()
	{
		return LoanInMoneymoremore3;
	}
	
	public void setLoanInMoneymoremore3(String loanInMoneymoremore3)
	{
		LoanInMoneymoremore3 = loanInMoneymoremore3;
	}
	
	public String getOrderNo3()
	{
		return OrderNo3;
	}
	
	public void setOrderNo3(String orderNo3)
	{
		OrderNo3 = orderNo3;
	}
	
	public String getBatchNo3()
	{
		return BatchNo3;
	}
	
	public void setBatchNo3(String batchNo3)
	{
		BatchNo3 = batchNo3;
	}
	
	public String getAmount3()
	{
		return Amount3;
	}
	
	public void setAmount3(String amount3)
	{
		Amount3 = amount3;
	}
	
	public String getFullAmount3()
	{
		return FullAmount3;
	}
	
	public void setFullAmount3(String fullAmount3)
	{
		FullAmount3 = fullAmount3;
	}
	
	public String getTransferName3()
	{
		return TransferName3;
	}
	
	public void setTransferName3(String transferName3)
	{
		TransferName3 = transferName3;
	}
	
	public String getRemark3()
	{
		return Remark3;
	}
	
	public void setRemark3(String remark3)
	{
		Remark3 = remark3;
	}
	
	public String getNeedAudit()
	{
		return NeedAudit;
	}
	
	public void setNeedAudit(String needAudit)
	{
		NeedAudit = needAudit;
	}
	
	public String getRechargeType()
	{
		return RechargeType;
	}
	
	public void setRechargeType(String rechargeType)
	{
		RechargeType = rechargeType;
	}
	
	public String getCardNoList()
	{
		return CardNoList;
	}
	
	public void setCardNoList(String cardNoList)
	{
		CardNoList = cardNoList;
	}
	
	public String getAccountType()
	{
		return AccountType;
	}
	
	public void setAccountType(String accountType)
	{
		AccountType = accountType;
	}
	
	public String getRandomTimeStamp()
	{
		return RandomTimeStamp;
	}
	
	public void setRandomTimeStamp(String randomTimeStamp)
	{
		RandomTimeStamp = randomTimeStamp;
	}
	
	public String getAuditType()
	{
		return AuditType;
	}
	
	public void setAuditType(String auditType)
	{
		AuditType = auditType;
	}
	
	public String getLoanNoList()
	{
		return LoanNoList;
	}
	
	public void setLoanNoList(String loanNoList)
	{
		LoanNoList = loanNoList;
	}
	
	public String getLoanNoListFail()
	{
		return LoanNoListFail;
	}
	
	public void setLoanNoListFail(String loanNoListFail)
	{
		LoanNoListFail = loanNoListFail;
	}
	
	public String getWithholdBeginDate()
	{
		return WithholdBeginDate;
	}
	
	public void setWithholdBeginDate(String withholdBeginDate)
	{
		WithholdBeginDate = withholdBeginDate;
	}
	
	public String getWithholdEndDate()
	{
		return WithholdEndDate;
	}
	
	public void setWithholdEndDate(String withholdEndDate)
	{
		WithholdEndDate = withholdEndDate;
	}
	
	public String getSingleWithholdLimit()
	{
		return SingleWithholdLimit;
	}
	
	public void setSingleWithholdLimit(String singleWithholdLimit)
	{
		SingleWithholdLimit = singleWithholdLimit;
	}
	
	public String getTotalWithholdLimit()
	{
		return TotalWithholdLimit;
	}
	
	public void setTotalWithholdLimit(String totalWithholdLimit)
	{
		TotalWithholdLimit = totalWithholdLimit;
	}
	
	public String getFeeMax()
	{
		return FeeMax;
	}
	
	public void setFeeMax(String feeMax)
	{
		FeeMax = feeMax;
	}
	
	public String getFeeWithdraws()
	{
		return FeeWithdraws;
	}
	
	public void setFeeWithdraws(String feeWithdraws)
	{
		FeeWithdraws = feeWithdraws;
	}
	
	public String getMainRemark1()
	{
		return MainRemark1;
	}
	
	public void setMainRemark1(String mainRemark1)
	{
		MainRemark1 = mainRemark1;
	}
	
	public String getMainRemark2()
	{
		return MainRemark2;
	}
	
	public void setMainRemark2(String mainRemark2)
	{
		MainRemark2 = mainRemark2;
	}
	
	public String getMainRemark3()
	{
		return MainRemark3;
	}
	
	public void setMainRemark3(String mainRemark3)
	{
		MainRemark3 = mainRemark3;
	}
	
	public String getMessage()
	{
		return Message;
	}
	
	public void setMessage(String message)
	{
		Message = message;
	}
	
	public String getExchangeBatchNo1()
	{
		return ExchangeBatchNo1;
	}
	
	public void setExchangeBatchNo1(String exchangeBatchNo1)
	{
		ExchangeBatchNo1 = exchangeBatchNo1;
	}
	
	public String getAdvanceBatchNo1()
	{
		return AdvanceBatchNo1;
	}
	
	public void setAdvanceBatchNo1(String advanceBatchNo1)
	{
		AdvanceBatchNo1 = advanceBatchNo1;
	}
	
	public String getReturnTimes()
	{
		return ReturnTimes;
	}
	
	public void setReturnTimes(String returnTimes)
	{
		ReturnTimes = returnTimes;
	}
	
	public String getFeeType()
	{
		return FeeType;
	}
	
	public void setFeeType(String feeType)
	{
		FeeType = feeType;
	}
	
	public String getFeeRate()
	{
		return FeeRate;
	}
	
	public void setFeeRate(String feeRate)
	{
		FeeRate = feeRate;
	}
	
	public String getFeeSplitting()
	{
		return FeeSplitting;
	}
	
	public void setFeeSplitting(String feeSplitting)
	{
		FeeSplitting = feeSplitting;
	}
	
	public String getFeePlatform()
	{
		return FeePlatform;
	}
	
	public void setFeePlatform(String feePlatform)
	{
		FeePlatform = feePlatform;
	}
	
	public String getAuthFee()
	{
		return AuthFee;
	}
	
	public void setAuthFee(String authFee)
	{
		AuthFee = authFee;
	}
	
	public String getAuthState()
	{
		return AuthState;
	}
	
	public void setAuthState(String authState)
	{
		AuthState = authState;
	}
	
	public String getReleaseType()
	{
		return ReleaseType;
	}
	
	public void setReleaseType(String releaseType)
	{
		ReleaseType = releaseType;
	}

	public String getSendAccount()
	{
		return SendAccount;
	}

	public void setSendAccount(String sendAccount)
	{
		SendAccount = sendAccount;
	}

	public String getMobileType()
	{
		return MobileType;
	}

	public void setMobileType(String mobileType)
	{
		MobileType = mobileType;
	}

	public String getIdentityJsonList()
	{
		return IdentityJsonList;
	}

	public void setIdentityJsonList(String identityJsonList)
	{
		IdentityJsonList = identityJsonList;
	}

	public String getIdentityFailJsonList()
	{
		return IdentityFailJsonList;
	}

	public void setIdentityFailJsonList(String identityFailJsonList)
	{
		IdentityFailJsonList = identityFailJsonList;
	}

	public String getRealName2()
	{
		return RealName2;
	}

	public void setRealName2(String realName2)
	{
		RealName2 = realName2;
	}

	public String getIdentificationNo2()
	{
		return IdentificationNo2;
	}
	
	public void setIdentificationNo2(String identificationNo2)
	{
		IdentificationNo2 = identificationNo2;
	}
	
	public String getQueryType()
	{
		return QueryType;
	}
	
	public void setQueryType(String queryType)
	{
		QueryType = queryType;
	}
	
	public String getFeeQuota()
	{
		return FeeQuota;
	}
	
	public void setFeeQuota(String feeQuota)
	{
		FeeQuota = feeQuota;
	}
	
}
