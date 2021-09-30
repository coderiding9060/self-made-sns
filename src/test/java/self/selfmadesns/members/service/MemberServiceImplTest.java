package self.selfmadesns.members.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import self.selfmadesns.members.domain.Member;
import self.selfmadesns.members.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void inquiryMemberByNo() {
        Optional<Member> member = memberService.inquiryMemberByNo(1L);
        if(member.isEmpty()){
            System.out.println("null 반환");
        } else {
            System.out.println(member.get());
        }
    }

    @Test
    void inquiryMemberById() {
        Optional<Member> member = memberService.inquiryMemberById("홍길동");
        if(member.isEmpty()){
            System.out.println("null 반환");
        } else {
            System.out.println(member.get());
        }
    }

    @Test
    void inquiryMemberByEmail() {
        Optional<Member> member = memberService.inquiryMemberByEmail("abc@abc.com");
        if(member.isEmpty()){
            System.out.println("null 반환");
        } else {
            System.out.println(member.get());
        }
    }

    @Test
    void logInMember() {
        Optional<Member> member = memberService.logInMember("member1", "1234");
        if(member.isEmpty()){
            System.out.println("null 반환");
        } else {
            System.out.println(member.get());
        }
    }

    @Test
    void inquiryMemberList() {
        List<Member> memberList = memberService.inquiryMemberList();
        if(memberList.size()>0){
            System.out.println("리스트 얻음");
        } else {
            System.out.println("리스트가 null");
        }
    }

    @Test
    void createMember() {
        Member member = new Member();
        member.setId("member1");
        member.setPw("1234");
        member.setName("회원1");
        member.setEmail("member1@abc.com");
        member.setGender("남");
        Member member1 = memberService.createMember(member);
        System.out.println("member1 = " + member1);
    }

    @Test
    void editMember() {
        Member member = new Member();
        member.setId("member1");
        member.setPw("1234");
        member.setName("회원1");
        member.setEmail("member1@abc.com");
        member.setGender("남");
        int updateCount = memberService.editMember(member);
        System.out.println("updateCount = " + updateCount);
    }

    @Test
    void withdrawMember() {
        int deleteCount = memberService.withdrawMember("member1");
        System.out.println("deleteCount = " + deleteCount);
    }
}