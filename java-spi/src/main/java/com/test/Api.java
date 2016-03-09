package com.test;

import java.util.HashMap;

public class Api {
    private static HashMap<String, Class<? extends Spi>> spis = new HashMap<String, Class<? extends Spi>>();
    private String protocol;

    public Api(String protocol) {
        this.protocol = protocol;
    }

    public void Send(String msg) throws InstantiationException,
            IllegalAccessException {
        Spi spi = spis.get(protocol).newInstance();

        spi.send("��Ϣ���Ϳ�ʼ");
        spi.send(msg);
        spi.send("��Ϣ���ͽ���");
    }

    public static void Register(String protocol, Class<? extends Spi> cls) {
        spis.put(protocol, cls);
    }
}
