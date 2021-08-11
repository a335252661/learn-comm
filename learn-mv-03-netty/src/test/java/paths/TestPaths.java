package paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/11
 */
public class TestPaths {
    public static void main(String[] args) {

        //创建目录
        Path path = Paths.get("learn-mv-03-netty/d1/d2");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
