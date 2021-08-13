package c4;

import cn.cld.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static cn.cld.utils.ByteBufferUtil.debugAll;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/12
 */
@Slf4j
public class Server {

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                System.out.println("source.position()==="+source.position());
                // 把这条完整消息存入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    target.put(source.get()); // position 向前进一位
                }
//                debugAll(target);
            }
        }
        //0123456789abcdef33333\n ， 如果容量是16 ， 第一次传过来的是 0123456789abcdef ，没有找到\n,
        // compact 就相当于没有读到的，保留，然后继续写
        source.compact();
    }
    public static void main(String[] args) {
//        feizuse();
//        selectorfeizuse();
//        selectorfeizuseRead();
        selectorfeizuseRead2();
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
                // selector 在事件不处理的时候，他不会阻塞，cancel之后，事件取消
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
                    currentKey.cancel(); //取消这个事件 ， 没有事件 selector 就会阻塞
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
    private static void selectorfeizuseRead() {
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
            while (true){
                // selector方法 ， 相当于 SocketChannel sc = ssc.accept(); 没有事件发生，就阻塞，有事件发生，线程就恢复运行
                // selector 在事件不处理的时候，他不会阻塞，cancel之后，事件取消
                // 客户端强制关闭时候，或者客户端正常关闭时候， 都会发送一个read事件到服务端
                selector.select();
                //能运行下来说明，有事件发生，获取所有的事件 ，有连接事件，还有多个读取事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    // 拿到已经发生的事件
                    SelectionKey currentKey = iterator.next();
                    log.debug("key {}" , currentKey);
//                    currentKey.isValid()
                    //移除掉已经发生的事件，在已经发生的事件的集合里，源码里面不会主动移除，如果不移除，发生空指针异常
                    iterator.remove();
                    //判断事件的类型
                    if (currentKey.isAcceptable()) { //如果是接收 连接事件
                        //通过key获取服务器channel ,表示处理事件，如果事件不处理，则会事件一直存在，线程一直运行。不会阻塞
                        ServerSocketChannel channel = (ServerSocketChannel)currentKey.channel();
                        SocketChannel sc = channel.accept();  // SocketChannel sc = ssc.accept();
                        log.debug("accept {}" , sc);
                        //设置读取数据是非阻塞事件
                        sc.configureBlocking(false);
                        //socketChannel 注册到selector
                        SelectionKey sk = sc.register(selector, 0, null);
                        //绑定SelectionKey的监听事件
                        sk.interestOps(SelectionKey.OP_READ); //SocketChannel 注册到selector ， 肯定是绑定read事件，获取数据

                    }else if (currentKey.isReadable()){
                        try {
                            // 获取SocketChannel
                            SocketChannel socketChannel = (SocketChannel)currentKey.channel();
                            //此处存在消息边界问题 ， 当我ByteBuffer.allocate(4) ， 客户端发送中文消息 “中国” ， UTF-8编码下，一个中文占三个字节
                            // 那么服务端第一个获取四个字节  中� ，第二次获取剩余的三个字节  ��，显示乱码
                            // 每个socketChannel 应该有自己的 ByteBuffer ，如果客户端发送了5个字节，服务端只有allocate(4)四个字节接受，此时触发两次read，因为使用split分割\n，第一次
                            // 没读取到\n ,则前四个字节就丢失了， 所以 每个socketChannel 应该有自己的 ByteBuffer
                            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                            // 内容 hello\nxixi\n
                            int read = socketChannel.read(byteBuffer);//感觉就是把sockchannel 里面的数据 读取放入到 缓冲区
                            if(read == -1){ //表示客户端调用close 方法，正常断开连接
                                currentKey.cancel();//我们在selector 里面删除 注册的channel
                            }else { // 不是-1 说明服务器接收到了客户端的正常发送的数据，我们读取写入到缓冲区
                                //缓冲区切换到读模式
//                                byteBuffer.flip();
//                                ByteBufferUtil.debugRead(byteBuffer);
//                                System.out.println(Charset.defaultCharset().decode(byteBuffer));

                                //解决半包、黏包问题
                                //1、 发送消息 \n 分割
                                split(byteBuffer);

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            // 客户端强制关闭会 发送一个read事件到服务端，服务端检测到，需要把这个channel真正从集合里移除掉。这样服务器不会断开
                            currentKey.cancel();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void selectorfeizuseRead2() {
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
            while (true){
                // selector方法 ， 相当于 SocketChannel sc = ssc.accept(); 没有事件发生，就阻塞，有事件发生，线程就恢复运行
                // selector 在事件不处理的时候，他不会阻塞，cancel之后，事件取消
                // 客户端强制关闭时候，或者客户端正常关闭时候， 都会发送一个read事件到服务端
                selector.select();
                //能运行下来说明，有事件发生，获取所有的事件 ，有连接事件，还有多个读取事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    // 拿到已经发生的事件
                    SelectionKey currentKey = iterator.next();
                    log.debug("key {}" , currentKey);
//                    currentKey.isValid()
                    //移除掉已经发生的事件，在已经发生的事件的集合里，源码里面不会主动移除，如果不移除，发生空指针异常
                    iterator.remove();
                    //判断事件的类型
                    if (currentKey.isAcceptable()) { //如果是接收 连接事件
                        //通过key获取服务器channel ,表示处理事件，如果事件不处理，则会事件一直存在，线程一直运行。不会阻塞
                        ServerSocketChannel channel = (ServerSocketChannel)currentKey.channel();
                        SocketChannel sc = channel.accept();  // SocketChannel sc = ssc.accept();
                        log.debug("accept {}" , sc);
                        //设置读取数据是非阻塞事件
                        sc.configureBlocking(false);
                        //socketChannel 注册到selector ， ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                        // buffer 作为附件 绑定到对象的channel ， 每个channel 都有自己的buffer
                        SelectionKey sk = sc.register(selector, 0, byteBuffer);
                        //绑定SelectionKey的监听事件
                        sk.interestOps(SelectionKey.OP_READ); //SocketChannel 注册到selector ， 肯定是绑定read事件，获取数据

                    }else if (currentKey.isReadable()){
                        try {
                            // 获取SocketChannel
                            SocketChannel socketChannel = (SocketChannel)currentKey.channel();
                            // 获取对应的channel 关联的附件的buffer
                            ByteBuffer byteBuffer = (ByteBuffer)currentKey.attachment();
                            // 内容 hello\nxixi\n
                            int read = socketChannel.read(byteBuffer);//感觉就是把sockchannel 里面的数据 读取放入到 缓冲区
                            if(read == -1){ //表示客户端调用close 方法，正常断开连接
                                currentKey.cancel();//我们在selector 里面删除 注册的channel
                            }else { // 不是-1 说明服务器接收到了客户端的正常发送的数据，我们读取写入到缓冲区
                                //缓冲区切换到读模式
//                                byteBuffer.flip();
//                                ByteBufferUtil.debugRead(byteBuffer);
//                                System.out.println(Charset.defaultCharset().decode(byteBuffer));

                                //解决半包、黏包问题
                                //1、 发送消息 \n 分割
                                split(byteBuffer);
                                //此时我们知道 buffer 未读取，经过了compact方法之后，position==limit
                                if(byteBuffer.position() == byteBuffer.limit()){
                                    // 创建新的buteBuffer
                                    ByteBuffer newByteBuffer = ByteBuffer.allocate(byteBuffer.capacity() * 2);
                                    newByteBuffer.flip();
                                    newByteBuffer.put(byteBuffer);
                                    debugAll(byteBuffer);
                                    debugAll(newByteBuffer);

                                    //把新的butebuffer 作为附件挂载回去
                                    currentKey.attach(newByteBuffer);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            // 客户端强制关闭会 发送一个read事件到服务端，服务端检测到，需要把这个channel真正从集合里移除掉。这样服务器不会断开
                            currentKey.cancel();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
