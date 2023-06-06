package hoony.bulletinboard;

import hoony.bulletinboard.repository.MemberRepository;
import hoony.bulletinboard.repository.MemoryMemberRepository;
import hoony.bulletinboard.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 스프링 빈 직접등록 - @Service, @Repository @Autowired 어노테이션 제거한 후에 사용
    // 리포지토리 변경 시 Config 에서 수정하면 되는 장점이 있음

//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
