package com.playground.member.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {


    /**
     * 메인페이지
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Principal principal, Model model) {
        String message = "Welcome";

        if(principal != null)
            message += " " + principal.getName();

        model.addAttribute("message", message);
        return "/index";
    }


    /**
     * 유저권한 페이지 (임시)
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        model.addAttribute("message", "User " + principal.getName());
        return "/user";
    }

    /**
     * 어드민권한 페이지 (임시)
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        model.addAttribute("message", "Admin " + principal.getName());
        return "/admin";
    }



}
