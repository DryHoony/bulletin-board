package hoony.bulletinboard.service;

import hoony.bulletinboard.member.domain.Member;
import hoony.bulletinboard.member.repository.MemoryMemberRepository;
import hoony.bulletinboard.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // BeforeEach 에서 처리해주면 AfterEach 할 필요 있을까?
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearMemory();
//    }

    // 테스트 메소드명은 과감하게 한글로 해도 괜찮

    @Test
    void 회원가입() {
        //given
        Member member = new Member("hoony", "0000");

        //when
        memberService.join(member);

        //then
        Member findMember = memberRepository.findByName("hoony").get();
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void 회원가입_중복예외() {
        //given
        Member member = new Member("hoony", "0000");

        //when
        memberService.join(member);
        Exception e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member)); // 예외발생

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원!!");


    }


    @Test
    void 회원조회() {
    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void 로그인() {
    }

    @Test
    void 비밀번호수정() {
    }

    @Test
    void 회원탈퇴() {
    }
}