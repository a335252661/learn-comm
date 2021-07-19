package beans2;

import beans.Role;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/12
 */
public class Person {
    private Role myRole111;
    public Role getMyRole111() {
        return myRole111;
    }
    public void setMyRole222(Role myRole111) {
        this.myRole111 = myRole111;
    }
    @Override
    public String toString() {
        return "Person{" +
                "myRole111=" + myRole111 +
                '}';
    }
}
