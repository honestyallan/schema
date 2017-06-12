/*
 * Test.java 2015-6-1
 * 上海电银信息技术有限公司
 * 使用者必须经过许可 
 */
package demo;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

/**
 * 此类功能描述
 * @auther 李益勇
 * @version 2015-6-1 下午4:13:03
 * @since JDK1.6
 */
public class LibHsMacAPITest {
	public static void main(String[] args) throws Exception {
		LibHsMacAPITest.generateMAC();
	}
	
    /**
     * 生成MAC
     * @param method
     * @param databuf
     * @param datalen
     * @param key
     * @return
     * @throws Exception
     */
    public static void generateMAC() throws Exception{  
    	//获取key
    	Pointer sMacKey = new Memory(100);
    	LibHsMacAPIJNA.INATANCE.GenZAK(sMacKey);
    	String key = new String(sMacKey.getByteArray(0, 16));
    	System.out.println("key:" + key);
    	//加密
    	Pointer sMac = new Memory(16);
    	String sMacBuf = new String("1111");
    	LibHsMacAPIJNA.INATANCE.GenMAC(sMacBuf, sMac, key);
    	String mac = new String(sMac.getByteArray(0, 16),"utf-8");
    	System.out.println(mac);
    	//检验加密是否正确
    	int flag = LibHsMacAPIJNA.INATANCE.MACVerify(sMacBuf, mac, key);
    	System.out.println("检验结果：" + (flag == 0 ? "成功":"失败"));
    }
}
