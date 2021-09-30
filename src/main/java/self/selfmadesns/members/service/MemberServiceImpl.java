package self.selfmadesns.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import self.selfmadesns.members.domain.Member;
import self.selfmadesns.members.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> inquiryMemberByNo(Long no) {
        return memberRepository.selectMemberByNo(no);
    }

    @Override
    public Optional<Member> inquiryMemberById(String id) {
        return memberRepository.selectMemberById(id);
    }

    @Override
    public Optional<Member> inquiryMemberByEmail(String email) {
        return memberRepository.selectMemberByEmail(email);
    }

    @Override
    public Optional<Member> logInMember(String id, String pw) {
        return memberRepository.logInMember(id,pw);
    }

    @Override
    public List<Member> inquiryMemberList() {
        return new ArrayList<>(memberRepository.selectMemberList());
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.insertMember(member);
    }

    @Override
    public int editMember(Member member) {
        return memberRepository.updateMember(member);
    }

    @Override
    public int withdrawMember(String id) {
        return memberRepository.deleteMember(id);
    }
}
