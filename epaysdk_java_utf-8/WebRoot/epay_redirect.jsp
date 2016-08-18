<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cib.epay.sdk.*"%>
<%
	// [重要]该页面仅为示例页面，在正式使用时，应当先对传入参数进行安全性检查和过滤，防止XSS等攻击。SDK中的方法没有对传入的参数进行任何检查和过滤。
	
	String type = request.getParameter("redirect_type");
	
	if("ep_auth".equals(type)) {				// Ex.1-1 快捷支付认证跳转
	
		String trac_no = new String(request.getParameter("trac_no").getBytes("ISO-8859-1"), "UTF-8");
		String acct_type = new String(request.getParameter("acct_type").getBytes("ISO-8859-1"), "UTF-8");
		String bank_no = new String(request.getParameter("bank_no").getBytes("ISO-8859-1"), "UTF-8");
		String card_no = new String(request.getParameter("card_no").getBytes("ISO-8859-1"), "UTF-8");
		// 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
		out.println(EPay.epAuth(trac_no, acct_type, bank_no, card_no));
	} else if("ep_authpay".equals(type)) { 		// Ex.1-9 无绑定账户的快捷支付接口
	
		String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
		String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");
		String order_title = new String(request.getParameter("order_title").getBytes("ISO-8859-1"), "UTF-8");
		String order_desc = new String(request.getParameter("order_desc").getBytes("ISO-8859-1"), "UTF-8");
		String remote_ip = request.getRemoteAddr();
		// 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
		out.println(EPay.epAuthPay(order_no, order_amount, order_title, order_desc, remote_ip));
	} else if("gp_pay1".equals(type)) {			// Ex.2-1 网关支付跳转1
	
		String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
		String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");
		String order_title = new String(request.getParameter("order_title").getBytes("ISO-8859-1"), "UTF-8");
		String order_desc = new String(request.getParameter("order_desc").getBytes("ISO-8859-1"), "UTF-8");
		String remote_ip = request.getRemoteAddr();
		// 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
		out.println(EPay.gpPay(order_no, order_amount, order_title, order_desc, remote_ip));
	}
%>