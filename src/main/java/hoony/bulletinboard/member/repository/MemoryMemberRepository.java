package hoony.bulletinboard.member.repository;

import hoony.bulletinboard.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<String, Member>memory = new HashMap<>();
    // 추후 동시성 문제 고려! - ConcurrentHashMap, AtomicLong 사용 고려

    @Override
    public Member save(Member member) { // 추가
        memory.put(member.getName(), member);
        return member;
    }

    @Override
    public Optional<Member> findByName(String name) { // 조회
//        return Optional.empty();
        return Optional.ofNullable(memory.get(name)); // 값이 Null 일 수도, 아닐수도 있음
    }

    @Override
    public List<Member> findAll() { // 전체조회
        return new ArrayList<>(memory.values());
    }

    @Override
    public Member update(Member member) { // 수정 - 비밀번호 only
//        Member getMember = memory.get(member.getName());
//        getMember.setPassword(getMember.getPassword());
        // 왜 안되지? 주소값이 다른가?

//        memory.remove(member.getName());
        memory.put(member.getName(), member);
        return member;
    }

    @Override
    public void delete(Member member) { // 삭제
        memory.remove(member.getName());
    }
    
    public void clearMemory() { // 테스트 용
        memory.clear();
    }




}
