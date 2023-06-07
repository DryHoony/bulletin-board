package hoony.bulletinboard.member.controller;

import hoony.bulletinboard.member.domain.Member;
import hoony.bulletinboard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }


    @GetMapping("/")
    public String home(@CookieValue(name = "loginName", required = false) String loginName, Model model) { // 로그인 정보 추가

        if(loginName == null){
            System.out.println("쿠키로 name 정보 안들어옴");
            return "home";
        }
        System.out.println("쿠키로 name 정보 들어옴 >> " + loginName);

        // 로그인
//        Member loginMember = memberService.findMember(loginName);
//        model.addAttribute("member", loginMember);
        model.addAttribute("loginName", loginName);
        return "loginHome";
    }




//    @GetMapping("/")
//    public String home(@RequestParam(required = false) String loginName) {
//
//        if(loginName != null){
//            log.info("'{}'님 로그인 환영", loginName);
//        }
//
//        return "home";
//    }

//    @GetMapping("/")
//    public String home(Model model) {
//        MemberLogin memberLogin = (MemberLogin) model.getAttribute("memberLogin");
//        if(memberLogin==null){
//            memberLogin = new MemberLogin();
//            memberLogin.setLoginFlag(false);
//
//            System.out.println("homeController 에서 로그인 안됨");
//
//            return "home";
//        }
//
//        System.out.println("MemberLogin 객체 잘 받아와 졌나? = " + memberLogin.isLoginFlag());
//        if(memberLogin.isLoginFlag()){
//            System.out.println("그렇다면 이름은? = " + memberLogin.getName());
//        }
//
//        return "home";
//    }

}
