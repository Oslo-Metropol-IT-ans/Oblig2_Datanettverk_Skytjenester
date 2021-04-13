package org.emnmem.oblig2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/rooms")
    public String rooms() {
        return "index";
    }
}
