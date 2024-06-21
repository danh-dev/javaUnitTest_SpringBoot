package hello.hellosping.service;

import hello.hellosping.domain.Member;
import hello.hellosping.repository.MemberRepository;
import hello.hellosping.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class JpaMemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memoryMemberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("memberA");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memoryMemberRepository.findById(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void DuplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberA");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}