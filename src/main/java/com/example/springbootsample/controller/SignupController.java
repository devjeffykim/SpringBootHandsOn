package com.example.springbootsample.controller;

import com.example.springbootsample.application.service.UserApplicationService;
import com.example.springbootsample.form.SignupForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
    @Autowired
    private UserApplicationService userApplicationService;

    @GetMapping("/signup")
    public String getSignup(Model model , Locale locale ,
                            @ModelAttribute SignupForm form){
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);

        return "user/signup";
    }

    /** User signup process */
    @PostMapping ("/signup" )
    public String postSignup(Model model , Locale locale , @ModelAttribute SignupForm form ,
                             BindingResult bindingResult ) {
        // Input check result
        if (bindingResult.hasErrors()) {
            // NG: Return to the user signup screen
            return getSignup(model , locale , form );
        }
        log.info(form .toString());
        // Redirect to login screen
        return "redirect:/login" ;
    }
}
