package self.selfmadesns.members.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping(value = "/")
    public String home(){
        return "index";
    }

    @GetMapping(value = "/join.me")
    public String join(){
        return "members/memberJoinForm";
    }

    @GetMapping(value = "/logIn.me")
    public String logIn(){
        return "members/memberLogInForm";
    }

    @GetMapping(value = "/edit.me")
    public String edit(){
        return "members/memberEditForm";
    }


}
