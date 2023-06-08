package hoony.bulletinboard.member.controller;

public class MemberForm {
    // DTO
    // memberId 필드 없음

    private String name;
    private String password;

    public MemberForm() {
    }

    public MemberForm(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
