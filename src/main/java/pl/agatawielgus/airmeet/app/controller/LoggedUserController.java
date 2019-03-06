package pl.agatawielgus.airmeet.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.agatawielgus.airmeet.app.entity.LoggedInUser;
import pl.agatawielgus.airmeet.app.entity.User;
import pl.agatawielgus.airmeet.app.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/loggedin")
public class LoggedUserController {



    private UserService userService;


    @Autowired
    public LoggedUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers (Model theModel){

        List<User> users = userService.findAll();
        theModel.addAttribute("users", users);

        return "show-users";
    }

    @GetMapping("/showcontact")
    public String showContactDetails(@RequestParam("tempUserUsername") String userName, Model model){

        User user = userService.findByUsername(userName);

        model.addAttribute("user", user);

        return "contact-details";
    }


    @GetMapping("/userprofile")
    public String showUserProfile(Model model, Principal principal) {

        String userName = principal.getName();

        User user = userService.findByUsername(userName);
        model.addAttribute("user",user);

        return "user-profile";
    }

    @GetMapping("/showupdatepage")
    public String showUpdatePage(Model model, Principal principal){

        String userName = principal.getName();

        User user = userService.findByUsername(userName);
        model.addAttribute("user",user);

        return "update-form";
    }

    @PostMapping("/processupdateform")
    public String processUpdateForm(@Valid @ModelAttribute("user") User user,
                                    BindingResult theBindingResult){

       if (theBindingResult.hasErrors()){
            return "update-form";
        }

        String userName = user.getUsername();
        User existing = userService.findByUsername(userName);

        if (existing != null){
            userService.save(user);

            return "update-confirmation";
        }
        return "update-form";
    }

    @GetMapping("/delete")
    public String delete(Principal principal) {

        String userName = principal.getName();

        // delete the user
       LoggedInUser loggedInUser = userService.findLoggedInUserByUsername(userName);

       userService.delete(loggedInUser);

        // redirect to /employees/list
        return "delete-confirmation";

    }

}
