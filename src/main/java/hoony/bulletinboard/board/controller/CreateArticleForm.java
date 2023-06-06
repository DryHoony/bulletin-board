package hoony.bulletinboard.board.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateArticleForm {

    private String title;
    private String text;


    public CreateArticleForm(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
