package cn.cld;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/9
 */
public class Test {

    public static void main(String[] args) {
        try {
            FileChannel channel = new FileInputStream("learn-mv-03-netty/data.txt").getChannel();
            //准备缓冲区 , 表示在内存里面划分20个字节作为buffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //从channel里面读取数据
            int read = channel.read(buffer);
            //打印buffer的内容
            buffer.flip();//切换到读模式
            while (buffer.hasRemaining()){ //检查是否还有剩余的数据
                byte b = buffer.get();//无参表示一个字节一个字节的度
                System.out.println((char)b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new FileInputStream("data.txt").twr
//        try (FileInputStream fileInputStream = new FileInputStream("data.txt")) {
//
//        } catch (IOException e) {
//        }
    }

    public void fun() {

    }



}
