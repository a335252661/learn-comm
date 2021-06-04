package cn.cld.learnspshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private  String id;
    private  String username;
    private  String password;
    private String salt;
}
