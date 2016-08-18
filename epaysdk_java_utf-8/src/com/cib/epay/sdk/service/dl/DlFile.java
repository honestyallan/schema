/**
 * 文件下载接口
 *
 * @author xiezz
 * @version 1.1.2
 */
package com.cib.epay.sdk.service.dl;

import com.cib.epay.sdk.common.Configure;
import com.cib.epay.sdk.service.IDownloadService;
import com.cib.epay.sdk.util.DateTimeUtil;
import com.cib.epay.sdk.util.Signature;

import java.util.Map;


public class DlFile extends IDownloadService {

    private static final String SERVICE_NAME = "cib.epay.acquire.download";
    private static final String SERVICE_VER = "01";

    @Override
    public Object download(Map<String, String> params) {

        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("mac", Signature.generateMAC(params));

        return download(Configure.isDevEnv() ? Configure.GP_DEV_API : Configure.GP_PROD_API, params);
    }

    @Override
    public String downloadToFile(Map<String, String> params, String filename) {

        params.put("appid", Configure.getAppid());
        params.put("service", SERVICE_NAME);
        params.put("ver", SERVICE_VER);
        params.put("timestamp", DateTimeUtil.getDateTime());
        params.put("mac", Signature.generateMAC(params));

        return download(Configure.isDevEnv() ? Configure.GP_DEV_API : Configure.GP_PROD_API, params, filename);
    }

}
