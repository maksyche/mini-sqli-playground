package com.maksyche.controller;

import com.maksyche.dto.LoginRequest;
import com.maksyche.dto.UpdateRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        model.addAttribute("updateRequest", new UpdateRequest());
        return "main";
    }
}
