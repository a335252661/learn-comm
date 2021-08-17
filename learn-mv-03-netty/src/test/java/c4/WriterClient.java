package c4;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/17
 */
public class WriterClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8081));
        // 客户端建立了连接，socketChannel ，通道里面就有数据了，我们要读出来，放到buteBuffer里面
        //需要创建byteBuffer ，
        int count = 0;
        while (true){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1014 * 1004);
            int read = socketChannel.read(byteBuffer); //channel里面最大放131071个字节
            count +=read;
            System.out.println(count);
        }

    }
}
