package hoony.bulletinboard.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class Article {

    // 글쓴이, 글제목, 내용
    private String writer;
    private String title;
    private String text;


    public Article() {
    }

    public Article(String writer, String title, String text) {
        this.writer = writer;
        this.title = title;
        this.text = text;
    }

}
