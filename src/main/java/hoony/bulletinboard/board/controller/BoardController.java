package hoony.bulletinboard.board.controller;

import hoony.bulletinboard.board.domain.Post;
import hoony.bulletinboard.board.service.BoardService;
import hoony.bulletinboard.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/boards") // 게시판 메인
    public String boardMain(@CookieValue(name = "loginName", required = false) String loginName, Model model){
        List<Post> posts = boardService.getAllPosts();
        model.addAttribute("posts", posts);

        // 로그인 여부 - '글쓰기' 버튼이 존재의 유무
        if(loginName != null){
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("isLogin", false);
        }

        return "boards/boardMain";
    }

    @GetMapping(value = "/boards/{postId}") // 게시글(Post) 상세
    public String postDetail(@CookieValue(name = "loginName", required = false) String loginName, @PathVariable Long postId, Model model){
        Post post = boardService.searchPost(postId);
        model.addAttribute("post", post);

//        System.out.println("post 값 잘 들어왔나 확인 >> " + post.toString());

        // 상세 페이지에서 - 로그인한 사용자가 글쓴이 일 경우에만 '수정', '삭제' 버튼 보여짐
        model.addAttribute("loginName", loginName); // 로그인 안한경우 null 값 들어가 있음


        return "boards/post";
    }


    @GetMapping(value = "/boards/new")
    public String createPostForm(@CookieValue(name = "loginName", required = false) String loginName, Model model){

        // 로그인 안되어 있을 땐, 로그인페이지로 빠꾸
        if(loginName == null){
            return "redirect:/members/login";
        }

        model.addAttribute("author", loginName);

        return "boards/createPostForm";
    }

    @PostMapping(value = "/boards/new")
    public String createPost(@ModelAttribute("post") Post post){
        Post postSaved = boardService.createPost(post);

        return "redirect:/boards/" + postSaved.getPostId(); // 생성된 Post 의 상세페이지
    }

    // 수정 - Post 정보를 통째로 전달받아야 한다.
    @PostMapping(value = "/boards/editForm")
    public String editPostForm(@CookieValue(name = "loginName", required = false) String loginName,
                               @ModelAttribute("post") Post post,
                               Model model){

        // 로그인 안되어 있을 땐, 로그인페이지로 빠꾸
        if(loginName == null){
            return "redirect:/members/login";
        }

        // 로그인명과 글쓴이가 불일치 하면 빠꾸
        if(!loginName.equals(post.getAuthor())){
            System.out.println("남에꺼 수정하면 안돼!");
            return "redirect:/boards/"+post.getPostId(); // 해당 글로 redirect
        }

        // 수정 폼으로 - 정상 흐름
        model.addAttribute("post", post);

        return "boards/editPostForm";
    }

    @PostMapping(value = "/boards/edit")
    public String editPost(@CookieValue(name = "loginName", required = false) String loginName,
                           @ModelAttribute("post") Post post){

//        System.out.println("post = " + post);

        // 로그인 안되어 있을 땐, 로그인페이지로 빠꾸
        if(loginName == null){
            return "redirect:/members/login";
        }

        // 로그인명과 글쓴이가 불일치 하면 빠꾸
        if(!loginName.equals(post.getAuthor())){
            System.out.println("남에꺼 수정하면 안돼!");
            return "redirect:/boards/"+post.getPostId(); // 해당 글로 redirect
        }

        // 수정로직
        boardService.updatePost(post);

        return "redirect:/boards/"+ post.getPostId();
    }


    @PostMapping(value = "/boards/delete") // 삭제
    public String deletePost(@CookieValue(name = "loginName", required = false) String loginName,
                             @RequestParam("postId") Long postId, @RequestParam("author") String author){

//        System.out.println("postId = " + postId);
//        System.out.println("author = " + author);

        // 로그인 안되어 있을 땐, 로그인페이지로 빠꾸
        if(loginName == null){
            return "redirect:/members/login";
        }

        // 로그인명과 글쓴이가 불일치 하면 빠꾸
        if(!loginName.equals(author)){
            System.out.println("남에꺼 지우면 안돼!");
            return "redirect:/boards/"+postId; // 해당 글로 redirect
        }

        // 삭제 - 정상 흐름
        boardService.deletePost(postId);

        return "redirect:/boards";
    }





}
