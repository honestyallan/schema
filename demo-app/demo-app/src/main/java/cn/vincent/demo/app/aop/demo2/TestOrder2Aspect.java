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
public class TestOrder2Aspect implements Ordered {

    @Pointcut("@within(cn.vincent.demo.app.aop.demo2.TestOrder2) || @annotation(cn.vincent.demo.app.aop.demo2.TestOrder2)")
    public void testOrder2PointCut() {
        System.out.println("testOrder2PointCut in TestOrderAspect");
    }

    @Before("testOrder2PointCut()")
    public void before() {
        System.out.println("testOrder2PointCut before");
    }

    @Around("testOrder2PointCut()")
    public Object aroundService(ProceedingJoinPoint joinPoint) {
        System.out.println("testOrder2PointCut around start");
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("testOrder2PointCut around end");
        }
    }

    @After("testOrder2PointCut()")
    public void after() {
        System.out.println("testOrder2PointCut after");
    }

    @AfterReturning("testOrder2PointCut()")
    public void afterReturning() {
        System.out.println("testOrder2PointCut afterReturning");
    }

    @AfterThrowing("testOrder2PointCut()")
    public void aterThrowing() {
        System.out.println("testOrder2PointCut aterThrowing");
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
