package c4;

import cn.cld.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/12
 */
@Slf4j
public class Server {

    public static void main(String[] args) {
//        feizuse();
        selectorfeizuse();
    }

    private static void zuse() {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        //创建服务器
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //绑定监听端口
            ssc.bind(new InetSocketAddress(8081));
            //连接集合
            ArrayList<SocketChannel> channels = new ArrayList<>();

            while (true){
                log.debug("connecting............");
                SocketChannel sc = ssc.accept();// 服务器启动，监听客户端连接服务器，没有连接，是阻塞的，线程停止运行
                log.debug("connected...{}",sc);
                channels.add(sc);

                for (SocketChannel channel : channels) {
                    log.debug("before...{}",channel);
                    channel.read(buffer); //也是一个阻塞方法，线程停止运行，等待客户端发送数据，这是客户端只是建立了连接
                    buffer.flip();

                    ByteBufferUtil.debugRead(buffer);

                    buffer.clear();
                    log.debug("after...{}",channel);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void feizuse() {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        //创建服务器
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();

            ssc.configureBlocking(false); //设置为非阻塞模式，此时 ssc.accept(); 不会阻塞，获取不到连接时候返回null
            //绑定监听端口
            ssc.bind(new InetSocketAddress(8081));
            //连接集合
            ArrayList<SocketChannel> channels = new ArrayList<>();

            while (true){
//                log.debug("connecting............");
                SocketChannel sc = ssc.accept();// 不会阻塞，获取不到连接时候返回null

                if(sc != null){
                    log.debug("connected...{}",sc);
                    sc.configureBlocking(false); //设置非阻塞模式，channel 从buffer里面读数据，获取不到不会阻塞了。
                    channels.add(sc);
                }



                for (SocketChannel channel : channels) {
//                    log.debug("before...{}",channel);
                    int read = channel.read(buffer);//channel 从buffer里面读数据，获取不到不会阻塞了。获取不到说明客户端没有发送数据，这个时候返回0
                    if(read > 0){
                        buffer.flip();

                        ByteBufferUtil.debugRead(buffer);

                        buffer.clear();
                        log.debug("after...{}",channel);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void selectorfeizuse() {
        try {
            // 创建selector
            Selector selector = Selector.open();

            //创建服务器
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false); //设置为非阻塞模式，此时 ssc.accept(); 不会阻塞，获取不到连接时候返回null
            //绑定监听端口
            ssc.bind(new InetSocketAddress(8081));


            //服务器channel 注册到selector
            SelectionKey sscKey = ssc.register(selector, 0, null);
            // sscKey 主管只关注连接事件
            sscKey.interestOps(SelectionKey.OP_ACCEPT);
            log.debug("regedit key {}" , sscKey);

            //连接集合
            ArrayList<SocketChannel> channels = new ArrayList<>();

            while (true){
                // selector方法 ， 相当于 SocketChannel sc = ssc.accept(); 没有事件发生，就阻塞，有事件发生，线程就恢复运行
                selector.select();


                //能运行下来说明，有事件发生，获取所有的事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey currentKey = iterator.next();
                    log.debug("key {}" , currentKey);

                    //通过key获取服务器channel ,表示处理事件，如果事件不处理，则会事件一直存在，线程一直运行。不会阻塞
                    ServerSocketChannel channel = (ServerSocketChannel)currentKey.channel();
                    SocketChannel accept = channel.accept();  // SocketChannel sc = ssc.accept();

                    log.debug("accept {}" , accept);
                }
//                log.debug("connecting............");
//                SocketChannel sc = ssc.accept();// 不会阻塞，获取不到连接时候返回null
//                if(sc != null){
//                    log.debug("connected...{}",sc);
//                    sc.configureBlocking(false); //设置非阻塞模式，channel 从buffer里面读数据，获取不到不会阻塞了。
//                    channels.add(sc);
//                }
//                for (SocketChannel channel : channels) {
////                    log.debug("before...{}",channel);
//                    int read = channel.read(buffer);//channel 从buffer里面读数据，获取不到不会阻塞了。获取不到说明客户端没有发送数据，这个时候返回0
//                    if(read > 0){
//                        buffer.flip();
//                        ByteBufferUtil.debugRead(buffer);
//                        buffer.clear();
//                        log.debug("after...{}",channel);
//                    }
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
