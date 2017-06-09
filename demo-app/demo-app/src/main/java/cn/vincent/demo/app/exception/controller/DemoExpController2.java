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

package cn.vincent.demo.app.exception.controller;

import cn.vincent.demo.app.exception.controller.vo.ExpVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * exp测试
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:08
 */
@Controller
@RequestMapping("/demo/exp2")
public class DemoExpController2 extends BaseDemoExpController {

    /************************************************ exception2 *************************************************/

    @RequestMapping(value = "/cnt/testindex")
    public ExpVO testIndex(String t) {
        if (StringUtils.isBlank(t)) {
            throw new RuntimeException("没有参数啊");
        }

        ExpVO vo = new ExpVO();
        vo.setT1("run");
        vo.setT2("normal");
        return vo;
    }
}
