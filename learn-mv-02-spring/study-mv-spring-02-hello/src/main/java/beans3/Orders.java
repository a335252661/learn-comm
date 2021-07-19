package beans3;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
public class Orders {

    private String name;

    public  Orders (){
        System.out.println("第一步：执行无参构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("第二步：执行set方法设置属性值");
        this.name = name;
    }

    public void initMethod() {
        System.out.println("第三步：执行初始化方法 initMethod");
    }

    public void distoryMethod() {
        System.out.println("第五步：distoryMethod");
    }

    @Override
    public String toString() {
        return "Orders{" +
                "name='" + name + '\'' +
                '}';
    }
}
