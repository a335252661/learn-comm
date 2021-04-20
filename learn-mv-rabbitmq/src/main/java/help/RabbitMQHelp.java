package help;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/16
 */
public class RabbitMQHelp {
    private static ConnectionFactory connectionFactory;
    private static Connection connection = null;
    private static Channel channel = null;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.106.128");
        connectionFactory.setPort(5672);//通讯端口
        connectionFactory.setVirtualHost("/ems");//设置虚拟主机
        //配置虚拟主机能够访问的用户密码
        //emsusr
        connectionFactory.setUsername("emsusr");
        connectionFactory.setPassword("emsusr");
    }

    public static Connection getConnection() {
        try {
            //获取连接对象
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static Channel getChannel() {
        Connection connection = RabbitMQHelp.getConnection();
        try {
            //获取连接中的通道
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public static void closeMQ() {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
