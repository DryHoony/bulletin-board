package hoony.bulletinboard.member.domain;

import lombok.Getter;
import lombok.Setter;


public class Member {

    private Long memberId;
    private String name;
    private String password;

    public Member() {
    }

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }



    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
