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


}
