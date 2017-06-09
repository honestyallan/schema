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

package cn.vincent.demo.app.aop.demo1.annotation;

import java.security.SignatureException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日志切面demo
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:07
 */
@Aspect
public class LogAccessAspect {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 没有setter getter不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // key值可以不带引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许特殊字符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    @Pointcut("@within(cn.vincent.demo.app.aop.demo1.annotation.LogAccess) || @annotation(cn.vincent.demo.app.aop.demo1.annotation.LogAccess)")
    public void logAccessAspectPointCut() {
        System.out.println("logAccessAspectPointCut in LogAccessAspect");
    }

    private LogAccess getAnnotation(ProceedingJoinPoint joinPoint) throws SignatureException {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature))
            throw new SignatureException("The Signature's real type is not MethodSignature but " + signature.getClass().getName());
        MethodSignature methodSignature = (MethodSignature) signature;
        LogAccess anno = methodSignature.getMethod().getAnnotation(LogAccess.class);
        if (null == anno) {
            anno = methodSignature.getMethod().getDeclaringClass().getAnnotation(LogAccess.class);
        }
        return anno;
    }

    @Before("logAccessAspectPointCut()")
    public void before() {
//    public void before(JoinPoint joinPoint) {
//        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
//        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
//        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
//        //获取传入目标方法的参数
//        Object[] args = joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
//        }
//        System.out.println("被代理的对象:" + joinPoint.getTarget());
//        System.out.println("代理对象自己:" + joinPoint.getThis());
        System.out.println("LogAccessAspect before");
    }

    @Around("logAccessAspectPointCut()")
    public Object aroundService(ProceedingJoinPoint joinPoint) {
        System.out.println("LogAccessAspect around start");
        try {
            LogAccess anno = this.getAnnotation(joinPoint);
            if (anno != null && anno.value()) {
                Object[] args = joinPoint.getArgs();
                if (args != null && args.length > 0) {
                    System.out.println("参数:" + mapper.writeValueAsString(args));
                } else {
                    System.out.println("参数:" + "没有数据");
                }
            }
            Object obj = joinPoint.proceed();
            System.out.println("res:" + obj.toString());
//            return joinPoint.proceed(new Object[]{"hello"});
//            return "nininiii";
            return obj;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("LogAccessAspect around end");
        }
    }

    @After("logAccessAspectPointCut()")
    public void after() {
        System.out.println("LogAccessAspect after");
    }

    @AfterReturning("logAccessAspectPointCut()")
    public void afterReturning() {
        System.out.println("LogAccessAspect afterReturning");
    }

    @AfterThrowing("logAccessAspectPointCut()")
    public void aterThrowing() {
        System.out.println("LogAccessAspect aterThrowing");
    }
}
