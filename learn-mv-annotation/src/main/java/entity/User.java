package entity;

import annotation.Gender;
import annotation.Gender.GenderEnum;
import annotation.Info;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/6
 */
@Info(name = "程刘德" ,age = 25 , description = "描述" ,hobby = {"iphone" , "home"})
public class User {
    @Gender(gender = GenderEnum.BOY)
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
