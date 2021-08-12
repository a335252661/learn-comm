package c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/12
 */
public class Client {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 8081));
//            SocketAddress address = sc.getLocalAddress();
            System.out.println("waiting...");
//        sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
