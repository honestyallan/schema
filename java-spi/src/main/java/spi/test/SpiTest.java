package spi.test;

import java.util.Iterator;

public class SpiTest {

    /**
     * @param args
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
                                          ClassNotFoundException {
        // TODO Auto-generated method stub
		// 这个是1.4中对Service Provider的介绍，加载服务是通过sun.misc.Service进行加载的
		// 这个也有相应的示例，照做就OK；
        Iterator it = sun.misc.Service.providers(SPIService.class);
        while (it.hasNext()) {
            SPIService service = (SPIService) it.next();
            service.test();
        }
    }
}
