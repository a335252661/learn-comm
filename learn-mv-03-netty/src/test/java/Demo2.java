import cn.cld.utils.ByteBufferUtil;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/10
 */
public class Demo2 {
    @Test
    public void fun() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte)0x61);
        byteBuffer.put( new byte[]{0x62,0x63} );

        byteBuffer.flip(); //表示切换到读的模式，
        byte b = byteBuffer.get();
        System.out.println((char)b);
        ByteBufferUtil.debugAll(byteBuffer);

        byteBuffer.compact();
        ByteBufferUtil.debugAll(byteBuffer);
        byteBuffer.put((byte)'程');
        ByteBufferUtil.debugAll(byteBuffer);

    }
}
