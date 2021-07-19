package cn.cld.learnspannotation.invocationHandler.HelloDemo;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
public class Helloimplements implements IHello{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
    @Override
    public void sayGoogBye(String name) {
        System.out.println(name+" GoodBye!");
    }
}
