package pl.agatawielgus.airmeet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.agatawielgus.airmeet.app.user.MeetingAppUser;

@Controller
public class HomeController {

    @GetMapping("/")
    public String goHome (){
        return "home";
    }


    @GetMapping(value = "/login")
    public String login () {
        return "login-form";
    }

    @GetMapping(value = "/register")
    public String register (Model model) {

        MeetingAppUser meetingAppUser =  new MeetingAppUser();

        model.addAttribute("MeetingAppUser", meetingAppUser);

        return "register-form";
    }




}


