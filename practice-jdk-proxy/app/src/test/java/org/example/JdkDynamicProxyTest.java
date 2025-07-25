package org.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkDynamicProxyTest {

    private final Logger log = LoggerFactory.getLogger(JdkDynamicProxyTest.class);

    @Test
    void dynamicA(){
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) java.lang.reflect.Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler
        );
        proxy.call();
        log.info("targetClass ={}",target.getClass());
        log.info("proxyClass{}", proxy.getClass());
    }
}
