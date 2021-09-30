package self.selfmadesns.members.repository;

import self.selfmadesns.members.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> selectMemberByNo(Long no);

    Optional<Member> selectMemberById(String id);

    Optional<Member> selectMemberByEmail(String email);

    Optional<Member> logInMember(String id, String pw);

    List<Member> selectMemberList();

    Member insertMember(Member member);

    int updateMember(Member member);

    int deleteMember(String id);
}
