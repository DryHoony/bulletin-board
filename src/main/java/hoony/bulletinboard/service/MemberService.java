package hoony.bulletinboard.service;

import hoony.bulletinboard.domain.Member;
import hoony.bulletinboard.repository.MemberRepository;
import hoony.bulletinboard.repository.MemoryMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();


    private final MemberRepository memberRepository; // DI

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        // 자동가입 a/0000
        Member member0 = new Member("a", "0000");
        memberRepository.save(member0);
    }

    // 회원가입
    public Member join(Member member) {
        // 중복검색
//        Member getMember = findMember(member.getName());
//        if(!getMember.equals(null)){
//            return null; // Name 중복 - 추후 메세지 추가
//        } // NPE - "getMember" is null

        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원!!");
                        });

        memberRepository.save(member);
        log.info("{}님 회원가입", member.getName());
        return member;
    }

    // 회원조회
    public Member findMember(String name){
        Optional<Member> getMember = memberRepository.findByName(name);

        if(getMember.isPresent()){
            return getMember.get();
        } else {
            return null; // return 값이 null 이면 조회 결과가 없는 것
        }

        // try-catch 문으로 Exception 관리 ver - 추후 upgrade
//        try{
//            getMember = memberRepository.findByName(name).get();
//        }catch (NoSuchElementException e){
//
//        }

    }

    // 회원목록
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 로그인
    public boolean login(Member member){
        Member getMember = findMember(member.getName());
        if(getMember.equals(null)){
            return false; // 해당 ID 없음 - 추후 메세지 추가
        }
        
        if(getMember.getPassword().equals(member.getPassword())){
            return true; // 비밀번호까지 일치
        }
        
        return false; // 비밀번호 불일치 - 추후 메세지 추가

    }

    // 비밀번호 수정
    public void modifyPassword(Member member){
        memberRepository.update(member);
    }


    // 회원탈퇴
    public void withdraw(Member member){
        memberRepository.delete(member);
    }



}
