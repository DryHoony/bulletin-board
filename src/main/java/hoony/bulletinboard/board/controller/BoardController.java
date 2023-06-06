package hoony.bulletinboard.board.controller;

import hoony.bulletinboard.board.domain.Article;
import hoony.bulletinboard.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/boards")
    public String boardMain(Model model){
        List<Article> articles = boardService.findArticles();
        model.addAttribute("articles", articles);
        return "boards/boardMain";
    }


    @GetMapping(value = "/boards/new")
    public String createForm(){

        return "boards/createArticleForm";
    }

    @PostMapping(value = "/boards/new")
    public String create(@ModelAttribute CreateArticleForm form){
        // 글쓴이는 로그인 기능 추가후 생성
        Article article = new Article("지금당신", form.getTitle(), form.getText());
        boardService.writeArticle(article);

//        return "redirect:/"; // 이건 home 으로 간다
        return "redirect:/boards";
    }


}
