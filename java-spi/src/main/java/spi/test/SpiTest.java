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
		// �����1.4�ж�Service Provider�Ľ��ܣ����ط�����ͨ��sun.misc.Service���м��ص�
		// ���Ҳ����Ӧ��ʾ����������OK��
        Iterator it = sun.misc.Service.providers(SPIService.class);
        while (it.hasNext()) {
            SPIService service = (SPIService) it.next();
            service.test();
        }
    }
}
