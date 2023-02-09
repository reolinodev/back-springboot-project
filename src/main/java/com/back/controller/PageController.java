package com.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @RequestMapping(value = "/swagger")
    public String swagger() {
        String url = "/swagger-ui/";
        return "redirect:" + url;
    }
}
