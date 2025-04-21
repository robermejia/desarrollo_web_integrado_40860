package com.robermejia.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErrorController {

    @GetMapping("error-403")
    public String error403(Model model){
        model.addAttribute("error","Error de acceso");
        return "error-403";
    }

}
