package com.example.deanery1.controller2;

//import com.example.deanery1.model.user.Role;
import com.example.deanery1.model.user.Status;
import com.example.deanery1.model.user.User;
import com.example.deanery1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage(HttpSession session) throws Exception {

        return "success";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String getRegistration(@ModelAttribute("newPerson") User user, Model model ){
        if (userService.getUsersFromDB(user)) {
            model.addAttribute("userNameError", "Користувач з таким логіном вже існує в БД");
            return "registration";
        }
        //user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userService.save(user);
        return "redirect:login";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "adminPage";
    }


}
