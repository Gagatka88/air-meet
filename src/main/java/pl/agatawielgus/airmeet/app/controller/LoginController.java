package pl.agatawielgus.airmeet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    @GetMapping("/loggedin")
    public String logIn(){
        return "loggedin";
    }



    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
