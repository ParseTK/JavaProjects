package com.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ScalarController {

    @GetMapping("/scalar")
    public ModelAndView scalar() {
        return new ModelAndView("forward:/scalar.html");
    }

    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/scalar");
    }
}
