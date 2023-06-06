package hoony.bulletinboard.board.service;

import hoony.bulletinboard.board.domain.Article;
import hoony.bulletinboard.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

        // 기본 글 테스트용
        Article article0 = new Article("GD", "무제", "제발 단 한~ 번이라도우");
        boardRepository.save(article0);

    }

    // 글 작성
    public void writeArticle(Article article){
        boardRepository.save(article);
        log.info("{}님 {} 글 작성 완료", article.getWriter(), article.getTitle());
    }



    // 글전체 조회
    public List<Article> findArticles(){
        log.info("글 전체 조회");
        return boardRepository.findAll();
    }

}
