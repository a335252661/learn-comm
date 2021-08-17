package c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

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

//            sc.write(Charset.defaultCharset().encode("hello"));
            sc.write(Charset.defaultCharset().encode("hello\nxixi\n"));
//            sc.write(Charset.defaultCharset().encode("中国"));//展示乱码问题
//            sc.write(Charset.defaultCharset().encode("0123456789abcdef33333\n"));
//            sc.write(Charset.defaultCharset().encode("0123\n"));
//            System.out.println("waiting...");
            System.in.read();
        sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
