package hoony.bulletinboard.member.controller;

import hoony.bulletinboard.member.domain.Member;
import hoony.bulletinboard.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){ // 회원가입 >> 회원가입폼 페이지로 이동
        return "members/createMemberForm";
    }


//    @PostMapping(value = "/members/new")
//    public String create(@RequestParam String name, String password){ // 값을 하나하나 받음
//        // @RequestBody 를 사용하면 name 객체에 name값과 password 값이 동시에 담김
//        System.out.println("name = " + name);
//        System.out.println("password = " + password);
//        Member member = new Member(name, password);
//        memberService.join(member);
//
//        return "redirect:/";
//    }

    @PostMapping(value = "/members/new")
    public String create(@ModelAttribute MemberForm memberForm){ // MemberForm type 으로 받음 - 멀티 파라미터 객체로 받기
        // @RequestBody 는 415 오류! - charset=UTF-8' is not supported
        // form태그를 이용시 HTTP header의 Content-type 이 x-www-form-urlencoded 로 지정됨 >> Json으로 받을 수 없음
        // @ModelAttribute 없어도 작동한다! >> 왜??
        System.out.println("name = " + memberForm.getName());

        Member member = new Member(memberForm.getName(), memberForm.getPassword());
        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping(value = "/members")
    public String memberList(Model model){ // 회원 목록 >> 회원목록 페이지로 이동
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }


    @GetMapping(value = "/members/login")
    public String loginForm(){ // 로그인 >> 로그인폼 페이지로 이동
        return "members/loginForm";
    }


    @PostMapping(value = "/members/login")
    public String login(@ModelAttribute MemberForm form, HttpServletResponse response) { // 따로 DTO 필요할까?

        Member member = new Member(form.getName(), form.getPassword());
        boolean loginFlag = memberService.login(member);


        if (loginFlag){
//            System.out.println("로그인 성공!");
            log.info("로그인 성공");

            // 세션 쿠키
            Cookie nameCookie = new Cookie("loginName", member.getName());
            nameCookie.setPath("/"); // 모든경로에서 사용
            response.addCookie(nameCookie);


            return "redirect:/";
//            return "redirect:/?loginName="+member.getName();
        }

        System.out.println("로그인 실패!");
        return "redirect:/members/login"; // 로그인 페이지 그대로

//        ra.addAttribute("memberLogin", memberLogin); // 데이터를 어떻게 보내야 할까?

    }

    @GetMapping(value = "/members/logout")
    public String logout(HttpServletResponse response) {
        // 세션만료
        Cookie nameCookie = new Cookie("loginName", null);
        nameCookie.setMaxAge(0);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);

        return "redirect:/";
    }

    @GetMapping(value = "/members/edit")
    public String editMemberForm(@CookieValue(name = "loginName") String loginName, Model model){
        // Cookie에 loginName 이 없으면 에러 - 400

        // model 에 name 값을 담아서 비밀번호만 수정하도록 변경
        model.addAttribute("loginName", loginName);
        return "members/editForm";
    }

    @PostMapping(value = "/members/edit")
    public String editMember(@CookieValue(name = "loginName") String loginName,
                             @RequestParam String password,
                             HttpServletResponse response){

//        System.out.println("Controller 에서 받은 password = " + password);



        Member member = new Member(loginName, password); // 여기서 memberId 정보가 소실
        member.setMemberId(memberService.findMember(loginName).getMemberId()); // 꾸역꾸역 추가
        memberService.modifyPassword(member);

        // 세션만료 - 다시 로그인
        Cookie nameCookie = new Cookie("loginName", null);
        nameCookie.setMaxAge(0);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);

        return "redirect:/";
    }

    @GetMapping(value = "/members/delete")
    public String deleteMember(@CookieValue(name = "loginName") String loginName,
                               HttpServletResponse response){

        Member member = memberService.findMember(loginName); // 회원인지 확인 포함
        memberService.withdraw(member);

        // 세션만료 - 다시 로그인
        Cookie nameCookie = new Cookie("loginName", null);
        nameCookie.setMaxAge(0);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);

        return "redirect:/";
    }




}
