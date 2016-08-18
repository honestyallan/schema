/**
 * 智能代付批量付款接口
 *
 * @author xiezz
 * @version 1.1.3
 */
package com.cib.epay.sdk.service.py;


import com.cib.epay.sdk.common.Configure;
import com.cib.epay.sdk.common.SignAlgorithm;
import com.cib.epay.sdk.service.IPostService;
import com.cib.epay.sdk.util.DateTimeUtil;
import com.cib.epay.sdk.util.Signature;

import java.util.Map;

public class PyBatchPay extends IPostService {

    private static final String SERVICE_NAME = "cib.epay.payment.batchPayApi";
    private static final String SERVICE_VER = "02";
    
    @Override
    public String exec(Map<String, String> params) {
        params.put("order_date", DateTimeUtil.getDate());
        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("sign_type", SignAlgorithm.get(SERVICE_NAME));
        params.put("mac", Signature.generateMAC(params));
        return txn(Configure.isDevEnv() ? Configure.PY_DEV_API : Configure.PY_PROD_API, params);
    }
}
