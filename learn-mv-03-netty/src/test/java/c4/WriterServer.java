package c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/17
 */
public class WriterServer {
    public static void main(String[] args) {

//        writer();
        writer2();

    }

    private static void writer() {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8081));
            ssc.configureBlocking(false);

            ssc.register(selector , SelectionKey.OP_ACCEPT);

            while (true){

                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        // 通过selectionKey 获得 ServerSocketChannel ,再获取sockerChannel
                        //ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
//                        SocketChannel socketChannel = ssc.accept();

                        // 直接使用ssc ,再获取sockerChannel
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);

                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < 3000000; i++) {
                            stringBuffer.append("1");
                        }
                        // string 转化为 byteBuffer 。 byteBuffer里面有数据
                        ByteBuffer byteBuffer = Charset.defaultCharset().encode(stringBuffer.toString());

                        // byteBuffer 里面的数据写入到channel
                        //  socketChannel.write(byteBuffer) 就是从byteBuffer 写入到channel
                        //  socketChannel.read(byteBuffer) 就是从channel 读取到byteBuffer

                        //一次性写入到了channel多少字节==：5373911
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：3932130
                        //一次性写入到了channel多少字节==：0
                        //一次性写入到了channel多少字节==：0
                        //因为channel写满了，写入了0，然后channel把数据发送出去了，就可以继续从bytebuffer里面把数据读入到channel了
                        //效率不高，我们可以，channel满了之后，写不进入，我们可以继续做别的事，比如读操作，writer2 是改进

                        while (byteBuffer.hasRemaining()) { //判断 byteBuffer 里面是否还有数据
                            int write = socketChannel.write(byteBuffer);// 写入进入了channel，byteBuffer 里面的数据就删除掉了
                            System.out.println("一次性写入到了channel多少字节==："+ write);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writer2() {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8081));
            ssc.configureBlocking(false);

            ssc.register(selector , SelectionKey.OP_ACCEPT);

            while (true){

                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        // 通过selectionKey 获得 ServerSocketChannel ,再获取sockerChannel
                        //ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
//                        SocketChannel socketChannel = ssc.accept();

                        // 直接使用ssc ,再获取sockerChannel
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        SelectionKey selectionKey = socketChannel.register(selector, 0, null);
                        selectionKey.interestOps(SelectionKey.OP_READ);


                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < 30000000; i++) {
                            stringBuffer.append("1");
                        }
                        // string 转化为 byteBuffer 。 byteBuffer里面有数据
                        ByteBuffer byteBuffer = Charset.defaultCharset().encode(stringBuffer.toString());

                        // byteBuffer 里面的数据写入到channel
                        //  socketChannel.write(byteBuffer) 就是从byteBuffer 写入到channel
                        //  socketChannel.read(byteBuffer) 就是从channel 读取到byteBuffer

                        int write = socketChannel.write(byteBuffer);// 写入进入了channel，byteBuffer 里面的写出去的数据就删除掉了
                        System.out.println("一次性写入到了channel多少字节==："+ write);

                        if (byteBuffer.hasRemaining()) { //判断 byteBuffer 里面是否还有数据
                            //关注可写事件 ， 这样不会覆盖之前的可读事件 selectionKey.interestOps()：表示之前的事件   +   |   都可以
//                            selectionKey.interestOps(selectionKey.interestOps() + SelectionKey.OP_WRITE);
                            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);

                            //把未写完的数据挂载到selectionkey
                            selectionKey.attach(byteBuffer);
                        }
                    }else if(key.isWritable()){ // 表示又可写了
                        ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
                        SocketChannel socketChannel = (SocketChannel)key.channel();

                        int write = socketChannel.write(byteBuffer);// 写入进入了channel，byteBuffer 里面的写出去的数据就删除掉了
                        System.out.println("一次性写入到了channel多少字节==："+ write);

                        if(!byteBuffer.hasRemaining()){//表示byteBuffer 全部都行写入了channel ， byteBuffer里面没有内容了
                            // 清除关注的附件
                            key.attach(null);
                            //清除掉可写事件
                            key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                        }

                    }



                }

            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
