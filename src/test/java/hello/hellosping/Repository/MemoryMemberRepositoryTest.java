package hello.hellosping.Repository;

import hello.hellosping.domain.Member;
import hello.hellosping.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        // "spring" name user
        Member member = new Member();
        member.setName("spring");

        // when
        // save(user)
        memoryMemberRepository.save(member);

        //then
        // findById()
        // true => user == user
        Member result = memoryMemberRepository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("memberA");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("memberB");
        memoryMemberRepository.save(member2);

        // when
        // find by name
        Member result = memoryMemberRepository.findByName("memberA").get();

        // then
        // true member == find member
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("memberA");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("memberB");
        memoryMemberRepository.save(member2);

        // when
        // find all
        List<Member> result = memoryMemberRepository.findAll();

        // then
        // count of member == 2
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
