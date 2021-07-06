package com.playground.member.form;

import com.playground.member.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Slf4j
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

        if(principal != null)
            model.addAttribute("loginUserName", principal.getName());

        return "/index";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/join")
    public String join() {
        return "/member/join";
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
