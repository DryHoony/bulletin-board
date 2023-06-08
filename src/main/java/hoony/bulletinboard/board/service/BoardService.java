package hoony.bulletinboard.board.service;

import hoony.bulletinboard.board.domain.Post;
import hoony.bulletinboard.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service @Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

        // 기본 글 테스트용
        Post post0 = new Post(null, "a", "무제", "제발 단 한~번이라도우", "사진경로", LocalDateTime.now());
        boardRepository.save(post0);

    }

    // 1.글 작성
    public Post createPost(Post post){
        post.setCreationTime(LocalDateTime.now());
        Post postSaved =  boardRepository.save(post);

        return postSaved;
    }

    // 2. 글전체 조회
    public List<Post> getAllPosts(){
        return boardRepository.findAll();
    }



    ////////////////////////////////////////////////////////////////////////

    // 2.5 글 검색 by postId
    public Post searchPost(Long postId){
        return boardRepository.find(postId);
    }


    // 3. 글 검색 by 작성자
    public List<Post> searchPostsByAuthor(String author){
        return boardRepository.findByAuthor(author);
    }

    // 4. 글 검색 by 제목 >> '검색어'를 포함하는 제목을 모두 찾기
    public List<Post> searchPostsByTitle(String title){
        return boardRepository.findByTitle(title);
    }


    // 5. 글 수정
    public void updatePost(Post post){
        boardRepository.update(post);
    }

    // 6. 글 삭제
    public void deletePost(Long postId){
        Post post = boardRepository.find(postId);
        if(post != null){
            boardRepository.delete(post);
        }
    }


}
