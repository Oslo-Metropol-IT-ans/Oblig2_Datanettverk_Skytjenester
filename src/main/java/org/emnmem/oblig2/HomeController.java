package org.emnmem.oblig2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = {"/"})
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = {"/login"})
    public String login() {
        return "/";
    }

    @RequestMapping(value = {"/register"})
    public String register() {
        return "/";
    }

    @RequestMapping(value = {"/rooms"})
    public String rooms() {
        return "/";
    }

    @RequestMapping(value = {"/room/{**}"})
    public String room() {
        return "/";
    }

}
