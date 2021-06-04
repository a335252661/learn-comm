package cn.cld.learnspcode.entity;

import java.io.Serializable;

/**
 * (TUser)实体类
 *
 * @author makejava
 * @since 2021-04-29 14:01:19
 */
public class TUser implements Serializable {
    private static final long serialVersionUID = -23743780407140183L;

    private Integer id;

    private String name;

    private String password;

    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
