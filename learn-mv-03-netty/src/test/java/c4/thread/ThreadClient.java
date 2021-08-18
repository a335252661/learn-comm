package c4.thread;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/18
 */
public class ThreadClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8081));

        socketChannel.write(Charset.defaultCharset().encode("12345678"));
        System.in.read();

    }
}
