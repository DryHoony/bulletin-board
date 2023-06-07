package hoony.bulletinboard.board.controller;

import hoony.bulletinboard.board.domain.Post;
import hoony.bulletinboard.board.service.BoardService;
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
    public String boardMain(Model model){
        List<Post> posts = boardService.getAllPosts();
        model.addAttribute("posts", posts);
        return "boards/boardMain";
    }

    @GetMapping(value = "/boards/{postId}") // 게시글(Post) 상세
    public String postDetail(@PathVariable Long postId, Model model){
        Post post = boardService.searchPost(postId);
        model.addAttribute("post", post);

//        System.out.println("post 값 잘 들어왔나 확인 >> " + post.toString());

        return "boards/post";
    }


    @GetMapping(value = "/boards/new")
    public String createPostForm(){

        return "boards/createPostForm";
    }

//    @PostMapping(value = "/boards/new")
//    public String createPost(@ModelAttribute  ){
//        // 글쓴이는 로그인 기능 추가후 생성
////        Post post = new Post("지금당신", form.getTitle(), form.getText());
////        boardService.writeArticle(post);
//
////        return "redirect:/"; // 이건 home 으로 간다
//        return "redirect:/boards";
//    }


}
