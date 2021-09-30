package self.selfmadesns.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.selfmadesns.members.repository.JdbcMemberRepository;
import self.selfmadesns.members.repository.MemberRepository;
import self.selfmadesns.members.service.MemberService;
import self.selfmadesns.members.service.MemberServiceImpl;

import javax.sql.DataSource;

@Configuration
public class MemberConfig {

    private DataSource dataSource;

    @Autowired
    public MemberConfig(DataSource dataSource) {
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


}
