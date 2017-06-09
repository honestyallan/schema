/*
 * Copyright (c) 2015 by vincent.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.vincent.demo.app.aop.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

/**
 * 切面demo
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:07
 */
@Aspect
public class TestOrderAspect2 implements Ordered {

    @Pointcut("@within(cn.vincent.demo.app.aop.demo2.TestOrder) || @annotation(cn.vincent.demo.app.aop.demo2.TestOrder)")
    public void testOrderPointCut2() {
        System.out.println("testOrderPointCut2 in TestOrderAspect");
    }

    @Before("testOrderPointCut2()")
    public void before() {
        System.out.println("testOrderPointCut2 before");
    }

    @Around("testOrderPointCut2()")
    public Object aroundService(ProceedingJoinPoint joinPoint) {
        System.out.println("testOrderPointCut2 around start");
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("testOrderPointCut2 around end");
        }
    }

    @After("testOrderPointCut2()")
    public void after() {
        System.out.println("testOrderPointCut2 after");
    }

    @AfterReturning("testOrderPointCut2()")
    public void afterReturning() {
        System.out.println("testOrderPointCut2 afterReturning");
    }

    @AfterThrowing("testOrderPointCut2()")
    public void aterThrowing() {
        System.out.println("testOrderPointCut2 aterThrowing");
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
