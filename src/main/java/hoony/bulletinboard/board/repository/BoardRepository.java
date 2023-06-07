package hoony.bulletinboard.board.repository;

import hoony.bulletinboard.board.domain.Post;

import java.util.List;

public interface BoardRepository {

    // CRUD
    // Post(postId, author, title, content, photoURL, creationTime)

    Post save(Post post);
    List<Post> findAll();
    Post find(Long postId);

    List<Post> findByAuthor(String author);
    List<Post> findByTitle(String title);

    Post update(Post post);
    void delete(Post post);


}
