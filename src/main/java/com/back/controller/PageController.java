package com.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "/swagger")
    public String swagger() {
        String url = "/swagger-ui/";
        return "redirect:" + url;
    }
}
