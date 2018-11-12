package com.gis.config.serverui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ThymeleafTestController
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@Controller
public class ThymeleafTestController {

    /**
     * 视图
     * @param model
     * @return
     */
    @RequestMapping("/thymeleaf/model")
    public String page3(Model model){
        model.addAttribute("name", "world");

        // 跳转至resources/templates/的test.html
        return "test/themeleaftest";
    }
}
