package hello.hellosping;

import hello.hellosping.repository.JpaMemberRepository;
import hello.hellosping.repository.MemberRepository;
import hello.hellosping.repository.MemoryMemberRepository;
import hello.hellosping.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    //MemberService
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    //MemberRepository
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
