package hello.hellosping.service;

import hello.hellosping.domain.Member;
import hello.hellosping.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        this.memoryMemberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach() {memoryMemberRepository.clearStore();}

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