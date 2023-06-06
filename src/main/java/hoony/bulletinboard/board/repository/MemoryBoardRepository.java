package hoony.bulletinboard.board.repository;

import hoony.bulletinboard.board.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static List<Article> memory = new ArrayList<>();


    @Override
    public Article save(Article article) {
        memory.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return memory;
    }




    @Override
    public List<Article> findByWriter(String writer) {
        return null;
    }

    @Override
    public List<Article> findByTitle(String title) {
        return null;
    }

    @Override
    public Article upgrade(Article article) {
        return null;
    }

    @Override
    public void delete(Article article) {

    }
}
