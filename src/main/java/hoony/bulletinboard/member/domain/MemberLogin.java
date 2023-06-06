package hoony.bulletinboard.member.domain;

public class MemberLogin {

    private String name;
    private boolean loginFlag;

    public MemberLogin() {
    }

    public MemberLogin(String name, boolean loginFlag) {
        this.name = name;
        this.loginFlag = loginFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

}
