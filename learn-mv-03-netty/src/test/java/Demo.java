import org.junit.Test;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/9
 */
public class Demo {
    @Test
    public void fun() {
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
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

    @Test
    public void fun2() {
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
            //准备缓冲区 , 表示在内存里面划分10个字节作为buffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            StringBuffer sb = new StringBuffer();
            int i=0;
            while (true){
                i++;
                //从channel里面读取数据
                int read = channel.read(buffer);
                if(read == -1){
                    break;
                }
                //打印buffer的内容
                buffer.flip();//切换到读模式
                System.out.println("第几次读取----" + i);
                while (buffer.hasRemaining()){ //检查是否还有剩余的数据
                    byte b = buffer.get();//无参表示一个字节一个字节的度
                    System.out.println((char)b);
                    sb.append((char)b);
                }
                buffer.clear();//切换到写模式
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun3() throws Exception{
        FileChannel channel = new FileInputStream("data.txt").getChannel();

        //准备缓冲区 , 表示在内存里面划分10个字节作为buffer缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)0x63);

        channel.write(buffer);
    }

}
