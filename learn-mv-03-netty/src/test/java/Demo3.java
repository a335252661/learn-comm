import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static cn.cld.utils.ByteBufferUtil.debugAll;
import static cn.cld.utils.ByteBufferUtil.debugRead;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/13
 */
public class Demo3 {
    public static void main(String[] args) {
//        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("0123456789");
//        buffer2.flip();
//        debugAll(buffer2); //position: [0], limit: [10]
//        buffer2.get();
//        buffer2.get();
//        debugAll(buffer2); //position: [2], limit: [10]

//        debugAll(buffer2); //position: [0], limit: [10]
//        buffer2.get();
//        buffer2.get();
//        buffer2.compact();
//        debugAll(buffer2); //position: [8], limit: [11]


        // 创建新的buteBuffer

        ByteBuffer buffer2 = ByteBuffer.allocate(11);
        buffer2.put(new byte[]{0,1,2,3,4,5,6,7,8,9});
//        debugAll(buffer2);

        ByteBuffer newByteBuffer = ByteBuffer.allocate(22);
//        newByteBuffer.flip();
        newByteBuffer.put(buffer2);
        debugAll(newByteBuffer);
        newByteBuffer.flip();
        byte b = newByteBuffer.get();
        System.out.println((char)b);

    }
}
