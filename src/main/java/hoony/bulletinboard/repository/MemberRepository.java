package hoony.bulletinboard.repository;

import hoony.bulletinboard.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 추후 리포시토리 변경을 위해 인터페이스로 설정
    // CRUD 기능

    Member save(Member member);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    Member update(Member member);
    void delete(Member member);



}
