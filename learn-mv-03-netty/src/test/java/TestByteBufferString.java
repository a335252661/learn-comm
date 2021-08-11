import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static cn.cld.utils.ByteBufferUtil.debugAll;


public class TestByteBufferString {
    public static void main(String[] args) {
//        byte[] bytes = "我爱你".getBytes();
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }

        // 字符串转为 ByteBuffer
        // 1. 方式1
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
//        buffer1.put("hello".getBytes());
        buffer1.put("我爱你".getBytes());
        debugAll(buffer1);

        // 2. Charset , 自动切换到了读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("爱你");
        debugAll(buffer2);

        // 3. wrap 自动切换到了读模式
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);

        // ByteBuffer 转为 字符串
        // 方式1
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);

        // 方式1
        buffer1.flip(); //  buffer1还处于写模式，需要切换一下到读模式，才能读出来
        String str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);

    }
}
