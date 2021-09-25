package com.banao.task.Contoller;

import com.banao.task.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MainService mainService;
    @RequestMapping("/dashboard")
    public String welcome(Model model, Principal principal)
    {
        model.addAttribute("user",mainService.getSingleUser(principal.getName()).getFirstName());
        return "userdashboard";
    }
}
