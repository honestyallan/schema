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
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 * 切面demo
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:07
 */
@Aspect
public class TestOrderAspect implements Ordered {

    @Pointcut("@within(cn.vincent.demo.app.aop.demo2.TestOrder) || @annotation(cn.vincent.demo.app.aop.demo2.TestOrder)")
    public void testOrderPointCut() {
        System.out.println("testOrderPointCut in TestOrderAspect");
    }

    @Before("testOrderPointCut()")
    public void before() {
        System.out.println("testOrderPointCut before");
    }

    @Around("testOrderPointCut()")
    public Object aroundService(ProceedingJoinPoint joinPoint) {
        System.out.println("testOrderPointCut around start");
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("testOrderPointCut around end");
        }
    }

    @After("testOrderPointCut()")
    public void after() {
        System.out.println("testOrderPointCut after");
    }

    @AfterReturning("testOrderPointCut()")
    public void afterReturning() {
        System.out.println("testOrderPointCut afterReturning");
    }

    @AfterThrowing("testOrderPointCut()")
    public void aterThrowing() {
        System.out.println("testOrderPointCut aterThrowing");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
