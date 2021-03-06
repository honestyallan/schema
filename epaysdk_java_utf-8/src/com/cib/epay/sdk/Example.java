/**
 * 示例类<br />
 * <b>注意</b> 该类仅供参考学习，请不要将该类直接包含在商户代码中<br />
 * <b>重要</b> 各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，特别是生成HTML代码的方法，务必保证传入参数的安全性，否则会导致安全问题。
 *
 * @author xiezz
 * @version 1.1.3
 */
package com.cib.epay.sdk;

import com.cib.epay.sdk.common.Configure;
import com.cib.epay.sdk.util.DateTimeUtil;


public class Example {

    public static void main(String[] args) {

        System.out.println("==重要== 调用任何API前，请先配置Configure类");

        // 调用任何API前，需要先行配置Configure类:
        Configure.setAppid("A0000093");                                //商户ID，appid
        Configure.setCommKey("85EDE15FE3654471B3117D9F2BC9F3D4");      //商户秘钥
        Configure.setSub_mrch("SDK测试商城");                          //二级商户名称
        Configure.setDevEnv(true);                                     //使用测试环境，生产上使用请设置为false

        System.out.println("收付直通车API统一入口：EPay类，可以参看该类中的注释");
        System.out.println("快捷支付接口示例，请参看Example类中的easyPayExample()中方法");
        System.out.println("网关支付接口示例，请参看Example类中的gatePayExample()中方法");
    }

    /**
     * 快捷支付示例
     */
    @SuppressWarnings("unused")
    public void easyPayExample() {

        // 一些通用的变量
        String order_date = DateTimeUtil.getDate();             //交易日期，格式：yyyyMMdd
        String acct_type = "0";                                 //银行账户类型：0-储蓄卡，1-信用卡，2-企业账户
        String card_no = "6222801234567888953";                 //账户号，这里是测试卡号
        String bank_no = "105100000017";                        //上述测试卡号对应的银行的人行联网行号

        String user_name = "张三";                              //姓名
        String cert_no = "340102198212062039";                  //证件号码
        String card_phone = "13900000000";                      //银行卡对应的联系电话


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-1 快捷支付认证（异步）】
        // 流程：商户根据业务信息生成快捷支付认证请求 -> 将html和js代码展示给用户 -> 用户浏览器跳转至收付直通车进行快捷认证
        // 回调：认证成功后 -> 收付直通车 -> 回调商户服务器的URL
        // 注意：每个银行卡（card_no）在支付交易前都需要进行认证操作

        // 商户系统跟踪号，商户系统生成，后续可根据该值调用查询接口查询认证情况
        String trac_no = "TN" + DateTimeUtil.getDateTime();       //这里示例使用TN20150806120001格式的订单号

        // 根据trac_no, acct_type, bank_no, card_no四个变量生成用户跳转的整个页面的HTML代码。
        // 注意：该函数生成的是跳转的中间页面的整页HTML代码，因此不要在该中间跳转页中输出其它的内容。
        // 重要：各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
        // 具体使用示例可以参看epay_redirect.jsp中的源码。
        // 该页面会在显示给用户的瞬间跳转至收付直通车快捷认证页面，上面的内容用户一般不会看到。
        String ex1_1html = EPay.epAuth(trac_no, acct_type, bank_no, card_no);
        // 如果需要固定其它某些值，如手机号，则可以送对应的参数：
        String ex1_1html_ = EPay.epAuth(trac_no, acct_type, bank_no, card_no, null, null, "18800000000", null, null);
        // 因该示例需要用户交互，可以访问或查看example.jsp中Ex.1-1示例
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-2 快捷支付认证（同步，短信验证）】
        // 流程：商户系统 --post（认证请求）--> 收付直通车，收付直通车 --短信--> 持卡人，商户系统 --post（短信确认）--> 收付直通车

        // 注意：每个银行卡（card_no）在支付交易前都需要进行认证操作，借记卡最后两个参数请置null
        String ex1_2aresult = EPay.epAuthSyncWithSms(trac_no, acct_type, bank_no, card_no, user_name, cert_no, card_phone, null, null);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档

        // 当认证信息正确时，收付直通车会往持卡人手机号上发送一条带6位数字的短信验证码，
        // 商户系统在收到持卡人填写的短信验证码后，调用短信确认接口完成快捷认证整个过程。
        String ex1_2bresult = EPay.epAuthCheckSms(trac_no, "123456");
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        // 注意，只有调用确认接口正确完成后，才能使用此卡进行支付操作。
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-3 快捷认证解绑】
        // 流程：商户系统 --post--> 收付直通车

        // 这里card_no是待解绑的账号，解绑后，如果该卡需要继续支付，需要重新调用快捷认证接口并认证成功后方可支付。
        String ex1_3result = EPay.epAuthCancel(card_no);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-4 快捷认证结果查询】
        // 流程：商户系统 --post--> 收付直通车

        // 这里trac_no是Ex.1-1或Ex.1-2中调用认证接口的商户系统跟踪号
        String ex1_4result = EPay.epAuthQuery(trac_no);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-5 快捷支付交易】
        // 流程：商户系统 --post--> 收付直通车
        // 注意：对于每个银行卡（card_no），在支付前，必须成功进行过快捷支付认证（调用Ex.1-1或Ex.1-2中接口进行认证），否则会支付失败

        // order_no是商户订单号，由商户系统生成，应当注意订单号在商户系统中应当全局唯一，即不会出现两笔订单有相同的订单号
        String order_no = "SDK" + DateTimeUtil.getDateTime();        //这里示例使用SDK20150806120001格式的订单号
        String order_amount = "1.00";                                //支付金额
        String order_title = "SDK支付示例";                          //订单标题
        String order_desc = "这笔订单是由SDK发起的示例订单";         //订单详情

        String ex1_5result = EPay.epPay(order_no, order_amount, order_title, order_desc, card_no);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-6 快捷支付交易结果查询】
        // 流程：商户系统 --post--> 收付直通车
        // 实际环境中可能由于网络环境等问题导致商户未收到支付结果通知，商户系统可以使用该接口查询订单的支付结果

        // 两种方式：1、查询当天订单，2、查询指定日期订单
        String ex1_6result = EPay.epQuery(order_no);             //order_no为调用EPay.epPay(...)时的订单号
        ex1_6result = EPay.epQuery(order_no, order_date);        //order_date为发起交易的日期
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-7 快捷支付退款交易】
        // 流程：商户系统 --post--> 收付直通车

        // 这里的order_no是待退款订单号，order_date是订单交易日期（注意不是退款的日期），
        // order_amount是退款金额，支持部分退款，但每笔成功的订单只能退款一次
        String ex1_7result = EPay.epRefund(order_no, order_date, order_amount);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-8 快捷支付退款结果查询】
        // 流程：商户系统 --post--> 收付直通车
        // 实际环境中可能由于网络环境等问题导致商户未收到退款结果，商户系统可以使用该接口查询退款结果

        // 两种方式：1、查询当天退款，2、查询指定日期退款结果
        String ex1_8result = EPay.epRefundQuery(order_no);              //order_no为调用EPay.epRefund(...)时的订单号
        ex1_8result = EPay.epRefundQuery(order_no, order_date);         //order_date为发起退款的日期
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.1-9 无绑定账户的快捷支付接口（异步）】
        // 流程：商户根据业务信息生成支付请求 -> 将html和js代码展示给用户 -> 用户浏览器跳转至收付直通车页面进行支付
        // 该快捷支付接口不需要对银行卡提前进行认证，用户跳转至收付直通车支付页面时，同时进行认证和支付的操作
        // 回调：支付成功后 -> 收付直通车 -> 回调商户服务器的URL

        // remote_ip为用户IP地址（客户端），其它参数含义同上，可以从HttpSevletRequest中获取（注意是jsp或servlet里的）:
        // String remote_ip	= request.getRemoteAddr();
        // 这里作为示例，直接使用一个定值（实际使用时不能写死）：
        String remote_ip = "8.8.8.8";

        // 注意：该函数生成的是跳转的中间页面的整页HTML代码，因此不要在该中间跳转页中输出其它的内容。
        // 重要：各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
        // 具体使用示例可以参看epay_redirect.jsp中的源码。
        // 该页面会在显示给用户的瞬间跳转至收付直通车快捷认证页面，上面的内容用户一般不会看到。
        String ex1_9html = EPay.epAuthPay(order_no, order_amount, order_title, order_desc, remote_ip);
        // 如果需要固定其它某些值，如手机号，则可以送对应的参数：
        String ex1_9html_ = EPay.epAuthPay(order_no, order_amount, order_title, order_desc, remote_ip, null, null, null, null, null, "18800000000", null, null);
        // 因该示例需要用户交互，可以访问或查看example.jsp中Ex.1-9示例
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @SuppressWarnings("unused")
    public void gatePayExample() {

        // 一些通用的变量
        String order_date = DateTimeUtil.getDate();                     //交易日期，格式：yyyyMMdd


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.2-1 网关支付（异步）】
        // 流程：商户根据业务信息生成网关支付请求 -> 将html和js代码展示给用户 -> 用户浏览器跳转至收付直通车进行网关支付
        // 回调（前台）：网银支付成功后 -> 收付直通车显示支付成功页面 -> 用户点击，带参数跳转到商户服务器的回调URL
        // 回调（后台）：支付成功后 -> 收付直通车 --post--> 商户服务器回调URL

        // order_no是商户订单号，由商户系统生成，应当注意订单号在商户系统中应当全局唯一，即不会出现两笔订单有相同的订单号
        String order_no = "SDK" + DateTimeUtil.getDateTime();           //这里示例使用SDK20150806120001格式的订单号

        // remote_ip为用户IP地址（客户端），其它参数含义同上，可以从HttpSevletRequest中获取（注意是jsp或servlet里的）:
        // String remote_ip	= request.getRemoteAddr();
        // 这里作为示例，直接使用一个定值（实际使用时不能写死）：
        String remote_ip = "8.8.8.8";

        String order_amount = "1.00";                                   //支付金额
        String order_title = "SDK支付示例";                             //订单标题
        String order_desc = "这笔订单是由SDK发起的示例订单";            //订单详情

        // 注意：该函数生成的是跳转的中间页面的整页HTML代码，因此不要在该中间跳转页中输出其它的内容。
        // 重要：各传入参数SDK都不作任何检查、过滤，请务必在传入前进行安全检查或过滤，保证传入参数的安全性，否则会导致安全问题。
        // 具体使用示例可以参看epay_redirect.jsp中的源码。
        // 该页面会在显示给用户的瞬间跳转至收付直通车网关支付页面，上面的内容用户一般不会看到。
        String ex2_1html = EPay.gpPay(order_no, order_amount, order_title, order_desc, remote_ip);
        // 因该示例需要用户交互，可以访问或查看example.jsp中Ex.2-1示例1
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.2-2 网关支付交易结果查询】
        // 流程：商户系统 --post--> 收付直通车
        // 实际环境中可能由于网络环境等问题导致商户未收到支付结果通知，商户系统可以使用该接口查询订单的支付结果

        // 两种方式：1、查询当天订单，2、查询指定日期订单
        String ex2_2result = EPay.gpQuery(order_no);                    //order_no为调用EPay.gpPay(...)时的订单号
        ex2_2result = EPay.gpQuery(order_no, order_date);               //order_date为发起交易的日期
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.2-3 网关支付退款交易】
        // 流程：商户系统 --post--> 收付直通车

        // 这里的order_no是待退款订单号，order_date是订单交易日期（注意不是退款的日期），
        // order_amount是退款金额，支持部分退款，但每笔成功的订单只能退款一次
        String ex2_3result = EPay.gpRefund(order_no, order_date, order_amount);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.2-4 网关支付退款结果查询】
        // 流程：商户系统 --post--> 收付直通车
        // 实际环境中可能由于网络环境等问题导致商户未收到退款结果，商户系统可以使用该接口查询退款结果

        // 两种方式：1、查询当天退款，2、查询指定日期退款结果
        String ex2_4result = EPay.gpRefundQuery(order_no);              //order_no为调用EPay.epRefund(...)时的订单号
        ex2_4result = EPay.gpRefundQuery(order_no, order_date);         //order_date为发起退款的日期
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void paymentExample() {

        // 一些通用的变量
        String acct_type = "0";                                         //账户类型-借记卡


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.3-1 智能代付单笔付款】
        // 流程：商户系统 --post--> 收付直通车

        // order_no是商户订单号，由商户系统生成，应当注意订单号在商户系统中应当全局唯一，即不会出现两笔订单有相同的订单号
        String order_no = "SDK" + DateTimeUtil.getDateTime();           //这里示例使用SDK20150806120001格式的订单号
        String trans_amt = "10.00";                                     //支付金额
        String to_bank_no = "309391000011";                             //收款行行号
        String to_acct_no = "622909115001762912";                       //收款人账号
        String to_acct_name = "华英雄";                                 //收款人户名
        String trans_usage = "这笔订单是由SDK发起的示例订单";           //订单详情

        String ex3_1result = EPay.pyPay(order_no, to_bank_no, to_acct_no, to_acct_name, acct_type, trans_amt, trans_usage);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.3-2 智能代付交易结果查询】
        // 流程：商户系统 --post--> 收付直通车
        // 对于交易状态为“支付中”的交易，商户系统可以使用该接口查询订单的支付结果

        String ex3_2result = EPay.pyQuery(order_no);                    //order_no为调用EPay.pyPay(...)时的订单号
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.3-3 智能代付商户信息查询】
        // 流程：商户系统 --post--> 收付直通车
        // 注意：查询的商户信息为本商户信息

        String ex3_3result = EPay.pyGetMrch();
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.3-4 智能代付批量付款】
        // 流程：商户系统 --post--> 收付直通车

        // 批量付款文件
        String batch_file = "E:\\batch.txt";

        // 文件名，用于标识批量付款文件，同一商户号同一日期下唯一，查询时使用
        String file_name = "Batch20160101.txt";

        String ex3_4result = EPay.pyBatchPay(file_name, batch_file);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.3-5 智能代付批量付款结果查询】
        // 流程：商户系统 --post--> 收付直通车

        // 文件名，用于标识批量付款文件，调用EPay.pyBatchPay()时传送的值
        file_name = "Batch20160101.txt";

        // 交易日期，调用EPay.pyBatchPay()时的日期
        String order_date = "20160101";

        String ex3_5_aresult = EPay.pyBatchPayQuery(file_name, order_date, null);
        // 返回结果为JSON格式的字符串，具体含义请参看收付直通车代收接口文档

        // 如果需要将返回结果保存至文件，可以将最后一个参数置为文件路径（带文件名）
        String ex3_5_bresult = EPay.pyBatchPayQuery(file_name, order_date, "E:\\result.txt");
        // 当写入文件失败时，将返回Configure.FILE_ERROR_RESULT值，否则返回原JSON格式字符串
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void donwloadExample() {

        // 一些通用的变量
        String order_date = DateTimeUtil.getDate();                    //交易日期，格式：yyyyMMdd

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.4-1 对账文件下载】
        // 流程：商户系统 --post--> 收付直通车
        // 用于下载快捷支付对账文件，下载的文件为一个zip压缩包，压缩包内内容请参看收付直通车代收接口文档。
        // 有两种方式：写入文件、返回文件内容byte[]

        // 1、返回文件内容byte[]方式：
        String rcpt_type = "0";                                             //rcpt_type 回单类型：0-快捷入账回单；1-快捷出账回单；2-快捷手续费回单；3-网关支付入账回单；4-网关支付出账回单；5-网关支付手续费回单
        Object ex4_1result = EPay.dlSettleFile(rcpt_type, order_date);      //order_date 指定需要下载的对账文件的日期
        // 该函数可能返回两种类型：String和byte[]
        // 当返回String时，表示下载失败，返回值为失败原因的json字符串
        // 当返回byte[]时，表示下载成功，byte[]为zip文件二进制内容
        // 可以使用如下代码进行判断：
        if (ex4_1result instanceof byte[]) {
            // 下载成功时的操作
        } else {
            // 下载失败时的操作
        }

        // 2、写入文件方式：
        String filename = "C:\\filename.zip";                        //保存的文件路径和文件名
        String ex4_1result_ = EPay.dlSettleFile(rcpt_type, order_date, filename);
        // 返回值为下载结果json字符串，可以使用如下代码判断成功与否：
        if (Configure.SUCCESS_RESULT.equals(ex4_1result_)) {
            // 下载成功时的操作
        } else {
            // 下载失败时的操作
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 【Ex.4-2 行号文件下载】
        // 流程：商户系统 --post--> 收付直通车
        // 用于下载人行行号与行名对应关系文件，该文件为一个zip压缩包。行号文件建议商户系统每天同步更新一次。
        // 有两种方式：写入文件、返回文件内容byte[]

        // 1、返回文件内容byte[]方式：
        String download_type = "01";                                    //download_type 下载类型：01-行号下载
        Object ex4_2result = EPay.dlFile(download_type);                //order_date 指定需要下载的对账文件的日期
        // 该函数可能返回两种类型：String和byte[]
        // 当返回String时，表示下载失败，返回值为失败原因的json字符串
        // 当返回byte[]时，表示下载成功，byte[]为zip文件二进制内容
        // 可以使用如下代码进行判断：
        if (ex4_2result instanceof byte[]) {
            // 下载成功时的操作
        } else {
            // 下载失败时的操作
        }

        // 2、写入文件方式：
        String bankfile = "C:\\bankfile.zip";                        //保存的文件路径和文件名
        String ex4_2result_ = EPay.dlFile(download_type, bankfile);
        // 返回值为下载结果json字符串，可以使用如下代码判断成功与否：
        if (Configure.SUCCESS_RESULT.equals(ex4_2result_)) {
            // 下载成功时的操作
        } else {
            // 下载失败时的操作
        }
    }
}
