import dao.UserDaoImpl;
import servicenew.UserNewServiceImpl;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/11
 */
public class App {

    public static void main(String[] args) {
        //用户实际调用的是业务层，dao层他们不需要接触！
//        UserService userService = new UserServiceImpl();
//        userService.getUser();


        UserNewServiceImpl UserNewService = new UserNewServiceImpl();
        UserNewService.setUserDao(new UserDaoImpl());
        UserNewService.getUser();
    }

}
