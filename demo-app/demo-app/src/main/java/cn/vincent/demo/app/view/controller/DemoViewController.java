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

package cn.vincent.demo.app.view.controller;

import cn.vincent.demo.app.view.controller.vo.HelloVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * view测试
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:08
 */
@Controller
@RequestMapping("/demo/view")
public class DemoViewController {

    /************************************************ test *************************************************/

    @RequestMapping(value = "/test/index")
    public String testIndex() {
        return "test-index";
    }

    /************************************************ viewName *************************************************/

    @RequestMapping(value = "/jsp/index")
    public String jspIndex() {
        return "jsp1-index";
    }

    @RequestMapping(value = "/jsp2/index")
    public String jsp2Index() {
        return "jsp2-index";
    }

    @RequestMapping(value = "/freemarker/index")
    public String freemarkerIndex() {
        return "freemarker-index";
    }

    /************************************************ 内容协商 *************************************************/

    @RequestMapping(value = "/cnt/testindex")
    public HelloVO cntIndex() {
        HelloVO vo = new HelloVO();
        vo.setT1("hello");
        vo.setT2("world");
        return vo;
    }
}
