<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cib.epay.sdk.util.*"%>
<%
	// 商户回调接口的页面
	// 收付直通车在支付成功、认证成功等事件发生时，会异步调用商户提供的URL回调地址，并把参数送过去
	// 商户根据接收到的回调通知，需要按以下步骤进行处理:
	// 1、取出所有参数
	// 2、对MAC进行验签操作，确保消息来自收付直通车而不是伪造的【非常重要】
	// 3、根据通知类型(event参数)，确定收到的通知类型
	// 4、根据其它参数，如order_no等，进行相应的商户业务逻辑处理
	// 本页面中的代码仅供参考，商户可以编码实现自己的逻辑
	
	String method = request.getMethod();
	Map<String,String> params = new HashMap<String,String>();
	Map<?, ?> reqParams = request.getParameterMap();
	Iterator<?> iter = reqParams.keySet().iterator();
	while (iter.hasNext()) {
		String name = (String) iter.next();
		String[] values = (String[]) reqParams.get(name);
		if("get".equalsIgnoreCase(method)) 
			params.put(name, new String(values[0].getBytes("ISO-8859-1"), "UTF-8"));
		else
			params.put(name, values[0]);
	}

	if(Signature.verifyMAC(params)) {				//验签成功
		
		if("get".equalsIgnoreCase(method)) {			//前台通知
			
			//商户可以在这边进行 [前台] 回调通知的业务逻辑处理
			//注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
			//前台跳转回来的通知，需要显示内容，如支付成功等
			if("NOTIFY_ACQUIRE_SUCCESS".equalsIgnoreCase(params.get("event"))) {		//支付成功通知
				
				String order_no = params.get("order_no");
				//String ... = ...
				//商户可以从params中获取通知中的数据
				//然后进行支付成功后的业务逻辑处理
				out.println("订单" + order_no + "支付成功");
				
			} else if("NOTIFY_ACQUIRE_FAIL".equalsIgnoreCase(params.get("event")))	{	//支付失败通知
				
				//支付失败业务逻辑处理
				
			} else if("NOTIFY_REFUND_SUCCESS".equalsIgnoreCase(params.get("event"))) {	//退款成功通知
				
				//退款成功业务逻辑处理
				
			} else if("NOTIFY_AUTH_SUCCESS".equalsIgnoreCase(params.get("event"))) {	//快捷支付认证成功通知
				
				//认证成功业务逻辑处理
				
			}
			
		} else if("post".equalsIgnoreCase(method)) {	//后台通知
			
			//商户可以在这边进行 [后台] 回调通知的业务逻辑处理
			//注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
			if("NOTIFY_ACQUIRE_SUCCESS".equalsIgnoreCase(params.get("event"))) {		//支付成功通知
				
				//支付成功业务逻辑处理
				
			} else if("NOTIFY_ACQUIRE_FAIL".equalsIgnoreCase(params.get("event")))	{	//支付失败通知
				
				//支付失败业务逻辑处理
				
			} else if("NOTIFY_REFUND_SUCCESS".equalsIgnoreCase(params.get("event"))) {	//退款成功通知
				
				//退款成功业务逻辑处理
				
			} else if("NOTIFY_AUTH_SUCCESS".equalsIgnoreCase(params.get("event"))) {	//快捷支付认证成功通知
				
				//认证成功业务逻辑处理
				
			}
		}
	
	}else{									//验签失败
		
		//不应当进行业务逻辑处理，即把该通知当无效的处理
		//商户可以在此记录日志等
		
	}
%>
