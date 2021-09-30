package self.selfmadesns.members.service;

import self.selfmadesns.members.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<Member> inquiryMemberByNo(Long no);

    Optional<Member> inquiryMemberById(String id);

    Optional<Member> inquiryMemberByEmail(String email);

    Optional<Member> logInMember(String id, String pw);

    List<Member> inquiryMemberList();

    Member createMember(Member member);

    int editMember(Member member);

    int withdrawMember(String id);
}
