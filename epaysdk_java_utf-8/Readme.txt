
┌───────────────┐
│     兴业银行 收付直通车      │
│          代码示例            │
└───────────────┘

	开发语言：JAVA 1.6+
	版本：1.1.3 beta

────────────
注意事项
────────────

1.SDK中的代码仅为示例，供商户参考。若商户应用于生产环境中，需要自行进行定制修改，并考虑安全方面的问题。
2.SDK提供的接口对传入参数没有作检查和过滤，商户在传入参数前，需要自行对传入的参数进行检查和过滤。
  特别是生成跳转页面代码的接口中（如网关支付接口等），请务必对传入参数进行安全性检查，防止出现XSS攻击等安全问题。
3.SDK示例中的商户号(appid)和商户秘钥（commKey）仅为示例，实际使用时需要向运营人员索取测试环境的商户号和商户秘钥并
  设置，否则使用SDK中示例的商户号和商户秘钥时，收付直通车会返回“MAC校验失败”或“未签署商户合约或合约无效”。


────────────
文件结构说明
────────────
	epay_acquire_java_utf-8
	│
	│  Readme.txt		本说明文件
	│
	├─src
	│  └─com
	│      └─cib
	│          └─epay
	│              └─sdk
	│                  │  EPay.java			收付直通车代收部分接口统一入口
	│                  │  Example.java			示例、说明代码（该类不需要包含到商户程序中）
	│                  │  
	│                  ├─comm
	│                  │      HttpsPostRequest.java	https协议post方式通讯类（若商户自己实现该类，请继承IRequestService类）
	│                  │      IRequestService.java	通讯类父类
	│                  │      
	│                  ├─common
	│                  │      Configure.java		收付直通车全局配置类，首次调用API前需要配置该类
	│                  │      SignAlgorithm.java		收付直通车签名算法配置类
	│                  │      
	│                  ├─service
	│                  │  │  IDownloadService.java	下载文件模式接口
	│                  │  │  IPostService.java		POST模式接口
	│                  │  │  IRedirectService.java	跳转模式生成中间页面代码接口
	│                  │  │
	│                  │  ├─dl				文件下载
	│                  │  │      DlFile.java		行号文件下载类
	│                  │  │      DlSettleFile.java	对账文件下载类
	│                  │  │  
	│                  │  ├─ep				快捷支付
	│                  │  │      EpAuth.java		快捷认证跳转页面生成类
	│                  │  │      EpAuthCancel.java	快捷支付签约解绑类
	│                  │  │      EpAuthCheckSms.java	快捷支付短信确认接口类
	│                  │  │      EpAuthPay.java		快捷支付无签约方式支付跳转页面生成类
	│                  │  │      EpAuthQuery.java	快捷认证结果查询类
	│                  │  │      EpAuthQuickSms.java	快捷认证同步接口类（带短信验证）
	│                  │  │      EpPay.java		快捷支付交易类
	│                  │  │      EpQuery.java		快捷支付交易结果查询类
	│                  │  │      EpRefund.java		快捷支付退款交易类
	│                  │  │      EpRefundQuery.java	快捷支付退款交易结果查询类
	│                  │  │      
	│                  │  ├─gp				网关支付
	│                  │  │      GpPay.java		网关支付交易类
	│                  │  │      GpQuery.java		网关支付交易结果查询类
	│                  │  │      GpRefund.java		网关支付退款交易类
	│                  │  │      GpRefundQuery.java	网关支付退款结果查询类
	│                  │  └─py				智能代付
	│                  │          PyBatchPay.java		智能代付批量付款类
	│                  │          PyBatchPayQuery.java	智能代付批量付款结果查询类
	│                  │          PyGetMrch.java		智能代付获取商户信息类
	│                  │          PyPay.java		智能代付单笔付款类
	│                  │          PyQuery.java		智能代付单笔订单查询类
	│                  │          
	│                  └─util
	│                          DateTimeUtil.java		日期时间工具类
	│                          FileUtil.java		文件工具类
	│                          IPUtil			IP地址工具类
	│                          Signature.java		签名、验签工具类
	│                          
	└─WebRoot
	       epay_notify.jsp					回调通知处理页面
	       epay_redirect.jsp				示例跳转中间页面
	       example.jsp					示例页面（该页面不需要包含到商户程序中）
	       
	       
────────────
商户需要修改或配置的文件
────────────
epay_notify.jsp：
需要添加自己的业务逻辑处理代码

epay_redirect.jsp
该页面仅供参考，商户可以实现自己的页面。
例如只使用网关支付方式的话，可以去掉里面快捷支付的相关内容。
同时该页里面也需要加上商户自己的业务逻辑，如订单状态变更为支付中等。
需要注意的时，SDK中接口不对传入参数进行检查，因此，在调用SDK中的接口前，商户系统务必对用户输入的参数进行合法性检查和过滤，防止出现安全问题。


────────────
可供商户调用的接口说明
────────────
建议配合收付直通车代收接口文档查看。
[重要]各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，特别是生成HTML代码的方法，务必保证传入参数的安全性，否则会导致安全问题。

Class EPay:

★ 快捷支付部分：

* 快捷支付认证接口（异步接口）
* 该方法将生成跳转页面的全部HTML代码，商户直接输出该HTML代码至某个URL所对应的页面中，即可实现跳转，可以参考示例epay_redirect.jsp
* [重要]各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
* @param trac_no		商户跟踪号
* @param acct_type		卡类型：0-储蓄卡,1-信用卡
* @param bank_no		人行联网行号
* @param card_no		账号
* @param user_name		姓名（可选，若为非null，则用户界面显示该值且不可输）
* @param cert_no		证件号码（可选，若为非null，则用户界面显示该值且不可输）
* @param card_phone		联系电话（可选，若为非null，则用户界面显示该值且不可输）
* @param expireDate		信用卡有效期（仅信用卡有效，格式MMYY，可选，若为非null，则用户界面显示该值且不可输）
* @param cvn			信用卡CVN（仅信用卡有效，可选，若为非null，则用户界面显示该值且不可输）
* @return			跳转页面HTML代码
public static String epAuth(String trac_no, String acct_type, String bank_no, String card_no)
public static String epAuth(String trac_no, String acct_type, String bank_no, String card_no, String user_name, String cert_no, String card_phone, String expireDate, String cvn)


* 快捷支付认证接口（同步接口，需短信确认）
* @param trac_no		商户跟踪号
* @param acct_type		卡类型：0-储蓄卡,1-信用卡
* @param bank_no		人行联网行号
* @param card_no		账号
* @param user_name		姓名
* @param cert_no		证件号码
* @param card_phone		联系电话
* @param expireDate		信用卡有效期（仅信用卡时必输，格式MMYY）
* @param cvn			信用卡CVN（仅信用卡时必输）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epAuthSyncWithSms(String trac_no, String acct_type, String bank_no, String card_no, String user_name, String cert_no, String card_phone, String expireDate, String cvn)


* 快捷认证短信验证码确认接口
* @param trac_no		发起同步认证时的商户跟踪号
* @param sms_code		6位数字短信验证码
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epAuthCheckSms(String trac_no, String sms_code)


* 快捷支付账户认证结果查询接口
* @param trac_no		商户跟踪号
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epAuthQuery(String trac_no)


* 快捷支付账户解绑接口
* @param card_no		待解绑账号
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epAuthCancel(String card_no)


* 快捷支付交易接口
* @param order_no		订单号
* @param order_amount		金额，单位元，两位小数，例：8.00
* @param order_title		订单标题
* @param order_desc		订单描述
* @param card_no		支付卡号
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epPay(String order_no, String order_amount, String order_title, String order_desc, String card_no)


* 快捷支付交易查询接口
* @param order_no		订单号
* @param order_date		订单日期，格式yyyyMMdd（可选）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epQuery(String order_no, String order_date)
public static String epQuery(String order_no)


* 快捷支付退款交易接口
* @param order_no		待退款订单号
* @param order_date		订单下单日期，格式yyyyMMdd
* @param order_amount		退款金额（不能大于原订单金额）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epRefund(String order_no, String order_date, String order_amount)


* 快捷支付退款交易结果查询接口
* @param order_no		退款的订单号
* @param order_date		订单下单日期，格式yyyyMMdd（可选）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String epRefundQuery(String order_no, String order_date)
public static String epRefundQuery(String order_no)


* 无绑定账户快捷支付跳转页面生成接口
* 该方法将生成跳转页面的全部HTML代码，商户直接输出该HTML代码至某个URL所对应的页面中，即可实现跳转，可以参考示例epay_redirect.jsp
* [重要]各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
* 参数bank_no,acct_type,card_no需要全为null或者全不为null。
* @param order_no		订单号
* @param order_amount		金额，单位元，两位小数，例：8.00
* @param order_title		订单标题
* @param order_desc		订单描述
* @param remote_ip		客户端IP地址
* @param bank_no		人行联网行号（可选，若为非null，则用户界面显示该值且不可输）
* @param acct_type		卡类型：0-储蓄卡,1-信用卡（可选，若为非null，则用户界面显示该值且不可输）
* @param card_no		账号（可选，若为非null，则用户界面显示该值且不可输）
* @param user_name		姓名（可选，若为非null，则用户界面显示该值且不可输）
* @param cert_no		证件号码（可选，若为非null，则用户界面显示该值且不可输）
* @param card_phone		联系电话（可选，若为非null，则用户界面显示该值且不可输）
* @param expireDate		信用卡有效期（仅信用卡有效，格式MMYY，可选，若为非null，则用户界面显示该值且不可输）
* @param cvn			信用卡CVN（仅信用卡有效，可选，若为非null，则用户界面显示该值且不可输）
* @return			跳转页面HTML代码
public static String epAuthPay(String order_no, String order_amount, String order_title, String order_desc, String remote_ip)
public static String epAuthPay(String order_no, String order_amount, String order_title, String order_desc, String remote_ip, String bank_no, String acct_type, String card_no, String user_name, String cert_no, String card_phone, String expireDate, String cvn)


★ 网关支付部分：

* 网关支付交易跳转页面生成接口
* 该方法将生成跳转页面的全部HTML代码，商户直接输出该HTML代码至某个URL所对应的页面中，即可实现跳转，可以参考示例epay_redirect.jsp
* [重要]各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
* @param order_no		订单号
* @param order_amount		金额，单位元，两位小数，例：8.00
* @param order_title		订单标题
* @param order_desc		订单描述
* @param remote_ip		客户端IP地址
* @return			跳转页面HTML代码
public static String gpPay(String order_no, String order_amount, String order_title, String order_desc, String remote_ip)


* 网关支付交易查询接口
* @param order_no		订单号
* @param order_date		订单日期，格式yyyyMMdd（可选）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String gpQuery(String order_no, String order_date)
public static String gpQuery(String order_no)


* 网关支付退款交易接口
* @param order_no		待退款订单号
* @param order_date		订单下单日期，格式yyyyMMdd
* @param order_amount		退款金额（不能大于原订单金额）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String gpRefund(String order_no, String order_date, String order_amount)


* 网关支付退款交易结果查询接口
* @param order_no		退款的订单号
* @param order_date		订单下单日期，格式yyyyMMdd（可选）
* @return			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String gpRefundQuery(String order_no, String order_date)
public static String gpRefundQuery(String order_no)


★ 智能代付部分：

* 智能代付单笔付款接口
* @param order_no     		订单号
* @param to_bank_no   		收款行行号
* @param to_acct_no   		收款人账户
* @param to_acct_name 		收款人户名
* @param acct_type    		账户类型：0-储蓄卡,1-信用卡,2-对公账户
* @param trans_amt    		付款金额
* @param trans_usage  		用途
* @return 			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String pyPay(String order_no, String to_bank_no, String to_acct_no, String to_acct_name, String acct_type, String trans_amt, String trans_usage)


* 智能代付单笔订单查询接口
* @param order_no 		订单号
* @return 			json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String pyQuery(String order_no)


* 智能代付商户信息查询接口
* @return json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String pyGetMrch()


* 智能代付批量付款接口
* @param file_name 文件名
* @param file      文件路径（带文件名）
* @return json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String pyBatchPay(String file_name, String file)


* 智能代付批量付款结果查询接口
* @param file_name      文件名
* @param order_date     订单日期
* @param save_file_name 保存内容至该文件（置为null为不保存至文件），写入文件失败将返回Configure.FILE_ERROR_RESULT
* @return json格式结果，返回结果包含字段请参看收付直通车代收接口文档
public static String pyBatchPayQuery(String file_name, String order_date, String save_file_name)


★ 文件下载部分：

* 对账文件下载接口
* @param rcpt_type		回单类型：0-快捷入账回单；1-快捷出账回单；2-快捷手续费回单；3-网关支付入账回单；4-网关支付出账回单；5-网关支付手续费回单；6-代付入账回单；7-代付出账回单；8-代付手续费回单
* @param trans_date		交易日期，格式yyyyMMdd
* @param filename		保存文件名（可选，可带路径）
* @return			（请参看EPay类中注释或Example类中示例）
public static Object gpFile(String rcpt_type, String trans_date)
public static String gpFile(String rcpt_type, String trans_date, String filename)


* 行号文件下载接口（生成文件）
* @param download_type		文件类型：01-行号文件
* @param filename		保存文件名（可选，可带路径）
* @return			（请参看EPay类中注释或Example类中示例）
public static Object dlFile(String download_type)
public static String dlFile(String download_type, String filename)


Class Signature:

* 生成签名MAC字符串
* @param			参数列表
* @return 			MAC字符串
public static String generateMAC(Map<String, String> params)


* 验证服务器返回的信息中签名的正确性
* @param params			参数列表（包含mac参数）
* @return			true-验签通过，false-验签失败
public static boolean verifyMAC(Map<String, String> params)
