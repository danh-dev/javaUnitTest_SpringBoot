package hello.hellosping.repository;

import hello.hellosping.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //Registration
    Member save(Member member);
    //retrieval-ID
    Optional<Member> findById(Long id);
    //retrieval-Name
    Optional<Member> findByName(String name);
    //retrieval-ALL
    List<Member> findAll();
}
