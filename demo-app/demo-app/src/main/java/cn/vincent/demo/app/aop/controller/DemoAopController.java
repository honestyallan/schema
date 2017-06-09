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

package cn.vincent.demo.app.aop.controller;

import cn.vincent.demo.app.aop.commons.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * aop测试
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:08
 */
@RestController
@RequestMapping("/demo/aop")
public class DemoAopController {

    /************************************************ advice *************************************************/
    @Resource(name = "demo1AdviceProxyFactoryBean")
    private TestService demo1AdviceProxyFactoryBean;
    @Resource(name = "demo1AdviceTestService")
    private TestService demo1AdviceTestService;

    @RequestMapping(value = "/advice/add")
    public String adviceAdd(String t) {
        this.demo1AdviceProxyFactoryBean.add(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    @RequestMapping(value = "/advice/update")
    public String adviceUpdate(String t) {
        this.demo1AdviceProxyFactoryBean.update(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    @RequestMapping(value = "/advice/add2")
    public String adviceAdd2(String t) {
        this.demo1AdviceTestService.add(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    @RequestMapping(value = "/advice/update2")
    public String adviceUpdate2(String t) {
        this.demo1AdviceTestService.update(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    /************************************************ xml *************************************************/
    @Resource(name = "demo1XmlTestService")
    private TestService demo1XmlTestService;

    @RequestMapping(value = "/xml/add")
    public String xmlAdd(String t) {
        this.demo1XmlTestService.add(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    @RequestMapping(value = "/xml/update")
    public String xmlUpdate(String t) {
        this.demo1XmlTestService.update(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    /************************************************ annotation *************************************************/
    @Resource(name = "demo1AnnoTestService")
    private TestService demo1AnnoTestService;

    @RequestMapping(value = "/anno/add")
    public String annoAdd(String t) {
        System.out.println(this.demo1AnnoTestService.add(StringUtils.isBlank(t) ? "test" : t));;
        return "success";
    }

    @RequestMapping(value = "/anno/update")
    public String annoUpdate(String t) {
        this.demo1AnnoTestService.update(StringUtils.isBlank(t) ? "test" : t);
        return "success";
    }

    /************************************************ test order *************************************************/
    @Resource(name = "demo2AnnoTestService")
    private TestService demo2AnnoTestService;

    @RequestMapping(value = "/order/add")
    public String orderAdd(String t) {
        this.demo2AnnoTestService.add(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }

    @RequestMapping(value = "/order/update")
    public String orderUpdate(String t) {
        this.demo2AnnoTestService.update(StringUtils.isBlank(t) ? "test" : "t");
        return "success";
    }
}
