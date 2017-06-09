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

import cn.vincent.demo.app.exception.controller.vo.ExceptionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author Vincent
 * @version 1.0 2017/05/20 17:14
 */
public class BaseDemoExpController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String EXCEPTION_VIEW = "demo/exp/all/exception";

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public ModelAndView exp(HttpServletRequest request, Exception ex) {
        logger.error("出错啦", ex);
        ModelAndView mv = new ModelAndView();

        ExceptionResult vo = new ExceptionResult();
        vo.setCode("10000");
        vo.setMessage(ex == null ? "exception" : ex.getMessage());

        mv.setViewName(EXCEPTION_VIEW);
        mv.addObject(vo);
        return mv;
    }
}
