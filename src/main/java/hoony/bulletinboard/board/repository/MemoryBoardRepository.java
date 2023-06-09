package hoony.bulletinboard.board.repository;

import hoony.bulletinboard.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static Map<Long,Post> memory = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setPostId(++sequence);
        memory.put(post.getPostId(), post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public Post find(Long postId) {
        return memory.get(postId);
    }

    @Override
    public Post update(Post post) {
        memory.put(post.getPostId(), post);
        return null;
    }

    @Override
    public void delete(Post post) {
        memory.remove(post.getPostId());
    }




    ///////////////////////////////////////////////////////////////

    @Override
    public List<Post> findByAuthor(String author) {
        return memory.values().stream()
                .filter(post -> post.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByTitle(String title) {
        return memory.values().stream()
                .filter(post -> post.getTitle().contains(title))
                .collect(Collectors.toList());
    }


}
