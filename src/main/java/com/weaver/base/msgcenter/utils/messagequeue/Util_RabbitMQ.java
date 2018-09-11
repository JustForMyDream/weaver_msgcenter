package com.weaver.base.msgcenter.utils.messagequeue;

import com.cloudstore.dev.api.bean.MessageBean;
import com.rabbitmq.client.*;
import com.weaver.base.msgcenter.constant.MessageCenterConstant;
import com.weaver.base.msgcenter.utils.MsgCenterUtil;
import com.weaver.base.msgcenter.utils.messagequeue.listener.RabbitListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.general.BaseBean;


import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * @ Author     ：wyl
 * @ Date       ：Created in 9:13 2018/9/5
 * @ Description：RabbitMQ工具类
 * @ Modified By：
 * @ Version：    1.0
 */
public class Util_RabbitMQ {
    private static final Log logger = LogFactory.getLog(Util_ActiveMQ.class);
    //前5个参数从配置文件读取
    private static String host = "";
    private static String username = "";
    private static String password = "";
    private static int port;
    private static boolean enable = false; // Rabbitmq是否启用

    private static ConnectionFactory connectionFactory = null; //连接工厂

    private  Connection connection = null; //连接

    private  Channel channel = null;



    private static Util_RabbitMQ rabbitmq = null;

    private Util_RabbitMQ(){
        init();
    }

    public void init(){

        BaseBean baseBean = new BaseBean();

        host = baseBean.getPropValue("weaver_message_center","rabbitMQHost");
        username = baseBean.getPropValue("weaver_message_center","rabbitMQUsername");
        password = baseBean.getPropValue("weaver_message_center","rabbitMQPassword");
        port = MsgCenterUtil.getIntValue(baseBean.getPropValue("weaver_message_center","rabbitMQPort"));
        enable ="1".equals(baseBean.getPropValue("weaver_message_center","rabbitMQStatus"));//status=1 启用

        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE,false,false,false,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    /**
     * 单例模式
     * @return
     */
    public static Util_RabbitMQ getInstance(){
        if(null == rabbitmq){
            synchronized (Util_RabbitMQ.class){
                if(null == rabbitmq){
                    rabbitmq = new Util_RabbitMQ();
                }
            }
        }
        return enable?rabbitmq:null;
    }



    /**
     *  rabbitmq消息发送
     * @param object
     * @throws Exception
     */
    public void sendMessage(Object object) throws Exception{

        logger.info("=========================================>进入rabbit发送消息通道");

        if(null == object){
            return;
        }
        if(object instanceof MessageBean){
            MessageBean messageBean = (MessageBean)object;
            connection = connectionFactory.newConnection();

            channel = connection.createChannel();

            channel.queueDeclare(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE,false,false,false,null);

            channel.basicPublish("",MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE,false,null,MsgCenterUtil.serialize(messageBean));

            channel.close();

            connection.close();

        }
    }

    /**
     * rabbitmq消息发送
     * @param object
     * @param exchangeName
     */
    public void sendMesage(Object object,String exchangeName){
        if(null == object){
            return;
        }
        if(object instanceof MessageBean){
            MessageBean messageBean = (MessageBean)object;
            try {

                String queue = String.valueOf(messageBean.getUserId());

                channel = connection.createChannel();

                channel.queueDeclare(queue,false,false,false,null);

                channel.basicPublish(exchangeName,queue,false,null,MsgCenterUtil.serialize(messageBean));


            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                this.close();
            }
        }

    }

    /**
     * rabbitmq 消息接收
     * @param userId
     * @param exchangeName
     */
    public void receiveMessage(String userId,String exchangeName){
        String queue = String.valueOf(userId);

        try {

            channel = connection.createChannel();

            channel.queueDeclare(queue,false,false,false,null);

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    MessageBean messageBean = (MessageBean) MsgCenterUtil.deserialize(body);
                    System.out.println(messageBean.getContext());
                }
            };
            //自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume(queue, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receiveMessage() throws Exception{
        connection = connectionFactory.newConnection();

        channel.queueDeclare(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE,false,false,false,null);

        RabbitListener rabbitListener = new RabbitListener(channel);

        channel.basicConsume(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE, true, rabbitListener);

//        channel.close();
//
//        connection.close();
    }

    /**
     * 关闭通道和连接
     */
    private void close(){
        if(channel != null){
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
