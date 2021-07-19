package beans;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/12
 */
public class User {
    private Role myRole111;
    public Role getMyRole() {
        return myRole111;
    }
    public void setMyRole222(Role myRole) {
        this.myRole111 = myRole;
    }
    @Override
    public String toString() {
        return "User{" +
                "myRole=" + myRole111 +
                '}';
    }
}
