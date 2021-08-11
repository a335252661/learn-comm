package helps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/8/11
 */
public class FileHelps {


    //************************************文件夹操作********************************************//

    // dirName "learn-mv-03-netty/d1/d2"
    public static void CreateDirs(String dirName) {
        //创建目录
        Path path = Paths.get(dirName);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 如果目录中有内容则删除不掉
    public static void deleteDir(String dirName) {
        Path path = Paths.get(dirName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //强制文件夹，内部有文件也会删除
    public static void deleteDirForce(String dirName) {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                //获取文件时候
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileCount.incrementAndGet();
                    Files.delete(file);
                    return super.visitFile(file, attrs);
                }
                // 进入文件夹之后
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    dirCount.incrementAndGet();
                    Files.delete(dir);
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("删除文件夹目录："+dirName);
        System.out.println("删除文件夹个数："+dirCount);
        System.out.println("删除文件个数："+fileCount);
    }

    //************************************文件操作********************************************//
    public static void deleteFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 拷贝文件夹，同时文件一起拷贝
    public static void CopyDir(String dirFrom , String dirTo) {
        long start = System.currentTimeMillis();
        String source = dirFrom;
        String target = dirTo;

        long end = 0;
        try {
            Files.walk(Paths.get(source)).forEach(path -> {
                try {
                    String targetName = path.toString().replace(source, target);
                    // 是目录
                    if (Files.isDirectory(path)) {
                        Files.createDirectory(Paths.get(targetName));
                    }
                    // 是普通文件
                    else if (Files.isRegularFile(path)) {
                        Files.copy(path, Paths.get(targetName));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            end = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(end - start);
    }


    public static void copyFile(String from , String to) {
        Path fromSource = Paths.get(from);
        Path toSource = Paths.get(to);
        try {
            Files.copy(fromSource,toSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copyFile(String from , String to ,Boolean isOption) {
        Path fromSource = Paths.get(from);
        Path toSource = Paths.get(to);
        try {
            // StandardCopyOption.REPLACE_EXISTING 表示文件以及存在则会覆盖掉
            if(isOption){
                Files.copy(fromSource,toSource , StandardCopyOption.REPLACE_EXISTING);
            }else {
                copyFile(from,to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //可以操作大于两个g的文件
    public static void copyFile2(String fromStr , String toStr) {
        try (
                FileChannel from = new FileInputStream(fromStr).getChannel();
                FileChannel to = new FileOutputStream(toStr).getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化,transferTo 最大传输 2g 数据
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void moveFile(String from , String to) {
        Path fromSource = Paths.get(from);
        Path toSource = Paths.get(to);
        try {
            Files.move(fromSource,toSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void moveFile(String from , String to ,Boolean isOption) {
        Path fromSource = Paths.get(from);
        Path toSource = Paths.get(to);
        try {
            // StandardCopyOption.REPLACE_EXISTING 表示文件以及存在则会覆盖掉
            if(isOption){
                Files.move(fromSource,toSource , StandardCopyOption.REPLACE_EXISTING);
            }else {
                moveFile(from,to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void walkFileTree(String dirName) {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {

                // 进入文件夹之前
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dirCount.incrementAndGet();
//                    System.out.println(dir);
                    return super.preVisitDirectory(dir, attrs);
                }

                //获取文件时候
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileCount.incrementAndGet();
//                    System.out.println(file); //文件全路径
//                    System.out.println(file.getFileName()); //获取文件名
                    return super.visitFile(file, attrs);
                }
                //获取文件失败时候
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return super.visitFileFailed(file, exc);
                }
                // 进入文件夹之后
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件夹个数："+dirCount);
        System.out.println("文件个数："+fileCount);
    }

    public static void main(String[] args) {
//        FileHelps.walkFileTree("C:\\clddata\\project\\other\\netty-demo");
        // C:\clddata\project\other\netty-demo - 副本
        FileHelps.deleteDirForce("C:\\clddata\\project\\other\\netty-demo - 副本");
    }

}
