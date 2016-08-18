/**
 * 网关支付退款交易接口
 *
 * @author xiezz
 * @version 1.1.2
 */
package com.cib.epay.sdk.service.gp;

import com.cib.epay.sdk.common.Configure;
import com.cib.epay.sdk.common.SignAlgorithm;
import com.cib.epay.sdk.service.IPostService;
import com.cib.epay.sdk.util.DateTimeUtil;
import com.cib.epay.sdk.util.Signature;

import java.util.Map;


public class GpRefund extends IPostService {

    private static final String SERVICE_NAME = "cib.epay.acquire.cashier.refund";
    private static final String SERVICE_VER = "02";

    public String exec(Map<String, String> params) {

        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("sign_type", SignAlgorithm.get(SERVICE_NAME));
        params.put("mac", Signature.generateMAC(params));

        return txn(Configure.isDevEnv() ? Configure.GP_DEV_API : Configure.GP_PROD_API, params);
    }
}
