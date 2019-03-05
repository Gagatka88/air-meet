package pl.agatawielgus.airmeet.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.agatawielgus.airmeet.app.entity.User;
import pl.agatawielgus.airmeet.app.service.UserService;
import pl.agatawielgus.airmeet.app.user.MeetingAppUser;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("MeetingAppUser") MeetingAppUser meetingAppUser,
            BindingResult theBindingResult, Model theModel) {

        String userName = meetingAppUser.getUserName();
        String airport = meetingAppUser.getAirportCode();

        if (theBindingResult.hasErrors()){
            return "register-form";
        }

        User existing = userService.findByUsername(userName);
        if (existing != null){
            theModel.addAttribute("MeetingAppUser", new MeetingAppUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            return "register-form";
        }

        userService.save(meetingAppUser);

        return "registration-confirmation";
    }
}
