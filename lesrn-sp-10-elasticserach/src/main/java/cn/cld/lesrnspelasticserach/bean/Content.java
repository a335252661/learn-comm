package cn.cld.lesrnspelasticserach.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
    private String shopName;
}
