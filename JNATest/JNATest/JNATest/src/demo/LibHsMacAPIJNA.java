package demo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;


/**
 * 接口里面的方法名要跟C的接口方法名一致
 * 调用linux so(动态库) 获取mac 
 * @version 2015-5-29 下午2:38:34
 * @since JDK1.6
 */
public interface LibHsMacAPIJNA extends Library{
	/*	
	然后开始我们的java接口
	loadLibrary第一个参数就是你的dll名字
	第二个就是当前接口的.class类型
	*/
	//线程安全  (加载lib使用了绝对路径   切割去掉了路径前面的"/")
	public  LibHsMacAPIJNA INATANCE = (LibHsMacAPIJNA) Native.synchronizedLibrary((LibHsMacAPIJNA)Native.loadLibrary(LibHsMacAPIJNA.class.getResource("/libHsMacAPI.dll").getPath().substring(1), LibHsMacAPIJNA.class));

	//绝对路径
//	public LibHsMacAPIJNA INATANCE =  (LibHsMacAPIJNA)Native.loadLibrary("E:\\document\\schema\\JNATest\\JNATest\\JNATest\\bin\\libHsMacAPI.dll", LibHsMacAPIJNA.class);
	/*********************************************************************
	函数名称：  GenMAC
	功能描述：  用于生成MAC
	参数说明：  
	       sMacBuf   需要进行MAC的数据流
		   sMac      生成的MAC,最小为16字节
		   sMacKey   对MAC加密密钥(加密过的KEY)
	  返回值：  无
	**********************************************************************/
	public void GenMAC(String sMacBuf, Pointer sMac, String sMacKey);
	/*
	函数名称：  GenZAK
	功能描述：  用于生成MACKey
	参数说明：  sMacKey	存放生成的MACKEY,长度为16位
	  返回值：  无
	**********************************************************************/
	public void GenZAK(Pointer sMacKey);
	/*********************************************************************
	函数名称：  MACVerify
	功能描述：  用于检查MAC
	参数说明：  
	       sMacBuf   需要进行MAC的数据流
		   sMac      生成的MAC,最小为16字节
		   sMacKey   对MAC加密密钥(加密过的KEY)
	  返回值：  
	      =0：校验成功
		 !=0: 校验不成功
	**********************************************************************/
	public int MACVerify(String sMacBuf, String sMac, String sMacKey);
}
