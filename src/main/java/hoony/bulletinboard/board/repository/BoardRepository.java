package hoony.bulletinboard.board.repository;

import hoony.bulletinboard.board.domain.Article;

import java.util.List;

public interface BoardRepository {

    // CRUD
    // Article(writer, title, text) //ver1 기준

    Article save(Article article);
    List<Article> findAll();

    List<Article> findByWriter(String writer);
    List<Article> findByTitle(String title);

    Article upgrade(Article article);
    void delete(Article article);


}
