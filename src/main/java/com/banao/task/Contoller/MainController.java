package com.banao.task.Contoller;

import com.banao.task.Exceptions.UserNotFound;
import com.banao.task.Exceptions.VerificationFailed;
import com.banao.task.Model.MyUser;
import com.banao.task.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    @Autowired
    MainService mainService;


    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("allUsers", mainService.fetchAllUserDetails());
        return "admindashboard";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("userModel", new MyUser());
        model.addAttribute("signuperror", "");
        return "signup";
    }

    @PostMapping("/process-user")
    public String signupUser(@ModelAttribute("userModel") MyUser myUser, Model model) {
        if (mainService.checkUserExists(myUser.getEmail())) {
            model.addAttribute("signuperror", "User Already Exists");
            return "signup";
        }
        mainService.signupUser(myUser);
        return "redirect:/login";
    }

    @GetMapping("/verify-user")
    public String verifyUser(@RequestParam("email") String email, @RequestParam("token") String token) throws VerificationFailed, UserNotFound {
        mainService.verifyUser(email, token);
        return "verificationsuccess";
    }
}
