package hoony.bulletinboard.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Post {

    // 글 번호(PK), 작성자, 제목, 내용, 사진, 작성시간

    private Long postId;
    private String author;
    private String title;
    private String content;
    private String photoURL; //
    private LocalDateTime creationTime;

    public Post() {
    }

    public Post(Long postId, String author, String title, String content, String photoURL, LocalDateTime creationTime) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.photoURL = photoURL;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
