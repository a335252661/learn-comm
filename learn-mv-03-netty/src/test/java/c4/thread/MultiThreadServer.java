package c4.thread;

import cn.cld.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/17
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws Exception{
        Thread.currentThread().setName("boss");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8081));

        Selector bossSelector = Selector.open();
        SelectionKey bossKey = ssc.register(bossSelector, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);

        // 一个worker 可以由多个sockerchannel调用
        Worker worker = new Worker("work-0");
        // 初始化线程，初始化selector，开启线程
        worker.register();

        while (true){
            bossSelector.select(); //不断循环，无事件则阻塞，有事件则执行
            Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if(key.isAcceptable()){ //获取到一个客户端的连接事件
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    log.debug("connected.....{}",socketChannel);

                    log.debug("before register.....{}",socketChannel);
                    // SocketChannel 注册到selector   ，注册到worker 的selector
                    //出现的问题：
                    //注册到  worker.selector 时候 ， 前面开启了worker 的线程，worker线程里面 selector.select(); selector监听事件，没有事件selector是阻塞的。
                    // 所以  register时候会阻塞住 ，
                    socketChannel.register(worker.selector , SelectionKey.OP_READ);
                    log.debug("after register.....{}",socketChannel);
                }

            }

        }
    }

    // 一个worker 可以由多个sockerchannel调用
    static class Worker implements  Runnable{
        private Thread thread;
        private Selector selector;
        private String name;
        public Worker(String name){
            this.name = name;
        }

        // 是在main线程里面执行的 当前线程 boss 调用的 worker.register(); 所以这些都是boss线程执行的
        // boss线程创建了 worker 线程 ，boss线程初始化了selector
        public void register() {
            thread = new Thread(this , name);
            try {
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            thread.start();
        }

        // 才是真正的worker 线程执行的
        @Override
        public void run() {
            log.debug("run........................");
            while (true){
                try {
                    selector.select();

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    if (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if(selectionKey.isReadable()){ //worker 执行的都是可读可写事件
                            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                            log.debug("read.....{}",socketChannel);
                            ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                            socketChannel.read(byteBuffer);

                            byteBuffer.flip();
                            ByteBufferUtil.debugAll(byteBuffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
