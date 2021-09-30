package self.selfmadesns.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import self.selfmadesns.members.domain.Member;
import self.selfmadesns.members.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@Controller
@Transactional
public class RestMemberController {

    private MemberService memberService;

    @Autowired
    public RestMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/members", headers = {"Content-type=application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> join(@RequestBody(required = false) Member member) {
        Map result = new HashMap();
        result.put("result", Boolean.FALSE);
        if(member!=null){
            Member insertResult = memberService.createMember(member);
            if(insertResult!=null){
                System.out.println("insertResult = " + insertResult);
                result.put("result", Boolean.TRUE);
            }
        }
        return result;
    }

}
