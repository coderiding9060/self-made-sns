package self.selfmadesns.members.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import self.selfmadesns.members.domain.Member;
import self.selfmadesns.members.service.MemberService;
import self.selfmadesns.members.util.SessionManageUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Transactional
public class RestMemberController {

    private MemberService memberService;

    private SessionManageUtil sessionManageUtil;

    public RestMemberController(MemberService memberService, SessionManageUtil sessionManageUtil) {
        this.memberService = memberService;
        this.sessionManageUtil = sessionManageUtil;
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

    @PutMapping(value = "/members", headers = {"Content-type=application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> edit(@RequestBody(required = false) Member member) {
        Map result = new HashMap();
        result.put("result", Boolean.FALSE);
        if(member!=null){
            int updateResult = memberService.editMember(member);
            if(updateResult>0){
                System.out.println("updateResult = " + updateResult);
                result.put("result", Boolean.TRUE);
            }
        }
        return result;
    }

    @GetMapping(value = "/members/{memberId}")
    @ResponseBody
    public Map<String, Object> logIn(@PathVariable(name = "memberId") String memberId) {
        Map result = new HashMap();
        result.put("result", Boolean.FALSE);
        Optional<Member> member = memberService.inquiryMemberById(memberId.toString());
        if(!member.isEmpty()){
                result.put("member", member);
                result.put("result", Boolean.TRUE);
        }
        return result;
    }

    @GetMapping(value = "/members/{id}/{pw}")
    @ResponseBody
    public Map<String, Object> logIn(@PathVariable(name = "id") String id, @PathVariable(name = "pw") String pw) {
        Map result = new HashMap();
        result.put("result", Boolean.FALSE);
        Optional<Member> member = memberService.logInMember(id, pw);
        if(!member.isEmpty()){
            String sessionData = sessionManageUtil.createSession("memberId", member.get().getId());
            if(sessionData.length()>0){
                result.put("member", member);
                result.put("result", Boolean.TRUE);
            }
        }
        return result;
    }

    @GetMapping(value = "/memberLogOut")
    @ResponseBody
    public Map<String, Object> logOut() {
        Map result = new HashMap();
        result.put("result", Boolean.FALSE);
        String sessionData = sessionManageUtil.removeSession("memberId");
        System.out.println("sessionData = " + sessionData);
        if(sessionData.length()==0){
            result.put("result", Boolean.TRUE);
        }
        return result;
    }



}
