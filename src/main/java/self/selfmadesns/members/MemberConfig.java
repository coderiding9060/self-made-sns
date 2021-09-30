package self.selfmadesns.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.selfmadesns.members.repository.JdbcMemberRepository;
import self.selfmadesns.members.repository.MemberRepository;
import self.selfmadesns.members.service.MemberService;
import self.selfmadesns.members.service.MemberServiceImpl;
import self.selfmadesns.members.util.SessionManageUtil;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
public class MemberConfig {

    private HttpSession httpSession;

    private DataSource dataSource;

    @Autowired
    public MemberConfig(HttpSession httpSession, DataSource dataSource) {
        this.httpSession = httpSession;
        this.dataSource = dataSource;
    }

    @Bean
    MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    SessionManageUtil sessionManageUtil(){
        return new SessionManageUtil(httpSession);
    }



}
