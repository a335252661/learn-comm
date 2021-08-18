package c4.thread;

import cn.cld.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/17
 */
@Slf4j
public class MultiThreadServer3 {
    public static void main(String[] args) throws Exception{
        Thread.currentThread().setName("boss");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8081));

        Selector bossSelector = Selector.open();
        SelectionKey bossKey = ssc.register(bossSelector, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);

        // 一个worker 可以由多个sockerchannel调用
//        Worker worker = new Worker("work-0");

        // 1. 创建固定数量的 worker 并初始化
        // 这台机器的可用核心数 Runtime.getRuntime().availableProcessors()
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger();

        while (true){
            bossSelector.select(); //不断循环，无事件则阻塞，有事件则执行
            Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                //1* 、客户端连接，发送消息，获取到连接事件
                if(key.isAcceptable()){ //获取到一个客户端的连接事件
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    log.debug("connected.....{}",socketChannel);

                    log.debug("before register.....{}",socketChannel);
                    // SocketChannel 注册到selector   ，注册到worker 的selector
                    //出现的问题：
                    //注册到  worker.selector 时候 ， 前面开启了worker 的线程，worker线程里面 selector.select(); selector监听事件，没有事件selector是阻塞的。
                    // 所以  register时候会阻塞住 ，
                    // 优化版本 ，先注册 ，再 selector.select()
                    // 把这行代码传到worker 类里面去 ， 然后在worker线程真正运行的时候再执行
                    //socketChannel.register(worker.selector , SelectionKey.OP_READ);

                    //优化地方，注册方法传递到worker 类
                    //2*、注册方法传递到worker 类
//                    worker.register(socketChannel);
                    workers[index.incrementAndGet() % workers.length].register(socketChannel);

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
        private volatile boolean start = false ; //表示还未初始化
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();
        public Worker(String name){
            this.name = name;
        }

        // 是在main线程里面执行的 当前线程 boss 调用的 worker.register(); 所以这些都是boss线程执行的
        // boss线程创建了 worker 线程 ，boss线程初始化了selector
        //3*、第一个客户端会初始化一遍，每个客户端对应一个channel，都需要注册监听事件，此时只是加入到队列
        public void register(SocketChannel socketChannel) throws Exception{
            if(!start){ //表示第一次初始化
                thread = new Thread(this , name);
                selector = Selector.open();
                thread.start();//4、线程启动
                start=true;
            }
            //6、手动唤醒阻塞事件
            // 类似信号量， 先  selector.select() 阻塞住--》  selector.wakeup() 有个票，开了，不阻塞了
            // 先  selector.wakeup() 有个票  --》  selector.select()  看到有个票，就不阻塞了，票就没了，是一次性的
            selector.wakeup(); // 唤醒 select 方法 boss
            socketChannel.register(selector , SelectionKey.OP_READ);  //这个注册事件会在selector.select()阻塞的时候注册失败
        }

        // 才是真正的worker 线程执行的
        @Override
        public void run() {
            log.debug("run........................");
            while (true){
                try {
                    selector.select();//5、没有注册事件，阻塞住
                    //7、手动唤醒的，没有监听到读事件，此时 selector 是空，继续循环，在 selector.select()  监听事件，前面注册了读事件，就能接受到发过来的数据，向下执行
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
