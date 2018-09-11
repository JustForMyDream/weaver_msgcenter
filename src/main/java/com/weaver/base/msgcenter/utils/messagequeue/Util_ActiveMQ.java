package com.weaver.base.msgcenter.utils.messagequeue;

import com.cloudstore.dev.api.bean.MessageBean;
import com.weaver.base.msgcenter.constant.MessageCenterConstant;
import com.weaver.base.msgcenter.utils.messagequeue.listener.ActiveListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.general.BaseBean;


import javax.jms.*;
/**
 * @ Author     ：wyl
 * @ Date       ：Created in 9:13 2018/9/3
 * @ Description：ActiveMQ工具类
 * @ Modified By：
 * @ Version：    1.0
 */
public class Util_ActiveMQ {
    private static final Log logger = LogFactory.getLog(Util_ActiveMQ.class);
    //这四个参数可以从配置文件读取
    private static String userName=ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static String passWord=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static String brokeURL=""; // 默认的连接地址
    private static boolean enable = false; // Activemq是否启用

    private static ConnectionFactory connectionFactory = null; // 连接工厂
    private static Connection connection = null; // 连接
    private static Session session = null; // 会话 接受或者发送消息的线程
    private static Destination destination = null; // 消息的目的地
    private static MessageProducer messageProducer = null; // 消息生产者
    private static MessageConsumer messageConsumer = null; // 消息的消费者

    private static Util_ActiveMQ activemq = null;

    private Util_ActiveMQ(){
        init();
    }

    private void init(){

        BaseBean baseBean = new BaseBean();

        userName = baseBean.getPropValue("weaver_message_center","activeMQUserName");
        passWord = baseBean.getPropValue("weaver_message_center","activeMQPassWord");
        brokeURL = baseBean.getPropValue("weaver_message_center","activeMQBrokeURL");
        enable ="1".equals(baseBean.getPropValue("weaver_message_center","activeMQStatus"));//status=1 启用

        connectionFactory = new ActiveMQConnectionFactory(userName,passWord,brokeURL);

        try {
            this.connection = connectionFactory.createConnection();
            this.connection.start();

            /**
             * 创建一个session
             * 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
             * 第二个参数为false时，sessionState的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
             */
            session = connection.createSession(true,Session.SESSION_TRANSACTED);

            //创建一个到达的目的地
            destination = session.createQueue(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE);
            //创建生产者
            messageProducer = session.createProducer(destination);
            //创建消费者
            messageConsumer = session.createConsumer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式
     * @return
     */
    public static Util_ActiveMQ getInstance(){
        if(null == activemq){
            synchronized (Util_ActiveMQ.class){
                if(null == activemq){
                    activemq = new Util_ActiveMQ();
                }
            }
        }
        return enable?activemq:null;
    }

    /**
     * 发送消息
     * @param object
     * @throws Exception
     */
    public void sendMessage(Object object) throws Exception {
        if(null == object){
            return;
        }
        if(object instanceof MessageBean){
            logger.info("============================>发送消息");

            MessageBean messageBean = (MessageBean)object;

            //设置消息是否持久化
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建消息
            ObjectMessage message=session.createObjectMessage(messageBean);
            //发送消息
            messageProducer.send(message);

            session.commit();

            //关闭messageProducer、session、connection

         //   messageProducer.close();

        }
    }

//    /**
//     * 发送异步消息
//     * @param object
//     * @throws JMSException
//     * @throws InterruptedException
//     */
//    public void sendAsyncMessage(Object object) throws JMSException, InterruptedException{
//        if(null == object){
//            return;
//        }
//        if(object instanceof MessageBean){
//            MessageBean messageBean = (MessageBean)object;
//            /**
//             * 创建一个session
//             * 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
//             * 第二个参数为false时，sessionState的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
//             */
//            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//            //创建一个到达的目的地
//            destination = session.createQueue(MsgCenterUtil.null2String(messageBean.getUserId()));
//            //创建生产者
////            messageProducer = session.createProducer(destination);
//            ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(destination);
//
//            final ObjectMessage message=session.createObjectMessage(messageBean);
//
//            producer.send(message,new AsyncCallback(){
//                public void onException(JMSException e) {
//                    e.printStackTrace();
//                    System.out.println("发送成功");
//                }
//
//                public void onSuccess() {
//                    System.out.println("发送成功");
//                }
//            });
//
//
//        }
//    }




    /**
     * activemq发送消息
     * @param object
     * @param flag
     * @param sessionState
     * @param deliveryState
     */
    public void sendMessage(Object object,boolean flag,int sessionState,int deliveryState){
        if(null == object){
            return;
        }
        if(object instanceof MessageBean){
            MessageBean messageBean = (MessageBean)object;
            try {
                /**
                 * 创建一个session
                 * 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
                 * 第二个参数为false时，sessionState的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
                 */
                session = connection.createSession(flag,sessionState);
                //创建一个到达的目的地
                destination = session.createQueue(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE);
                //创建生产者
                messageProducer = session.createProducer(destination);
                //设置消息是否持久化
                messageProducer.setDeliveryMode(deliveryState);
                //创建消息
                ObjectMessage message=session.createObjectMessage(messageBean);
                //发送消息
                messageProducer.send(message);

                session.commit();
            } catch (JMSException e) {
                e.printStackTrace();
            }finally {
                if(session != null){
                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if(connection != null){
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 接收消息
     * @param
     */
    public void   receiveMessage() {

//        if(null == session)
//        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //创建一个到达的目的地
//        destination = session.createQueue(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE);

        //创建监听器

        try {
            messageConsumer.setMessageListener(new ActiveListener());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


    /**
     * active接收消息
     * @param flag
     * @param sessionState
     */
    public void receiveMessage(boolean flag,int sessionState) {

        try {
            //创建一个session
            session = connection.createSession(flag, sessionState);
            //创建一个到达的目的地
            destination = session.createQueue(MessageCenterConstant.MESSAGE_CENTER_QUEUE_ONE);
            //创建消费者
            messageConsumer = session.createConsumer(destination);
            //创建监听器

            messageConsumer.setMessageListener(new ActiveListener());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭
     */
//    public void close(){
//        if(messageProducer != null){
//            try {
//                messageProducer.close();
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//        if(session != null){
//            try {
//                session.close();
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//        if(connection != null){
//            try {
//                connection.close();
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

}


