package com.weaver.base.msgcenter.utils.messagequeue.listener;

import com.weaver.base.msgcenter.entity.MessageBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.IOException;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 11:17 2018/9/7
 * @ Description：activeMQ消息监听器
 * @ Modified By：
 * @Version:
 */
public class ActiveListener implements MessageListener {

    private static final Log logger = LogFactory.getLog(ActiveListener.class);

    public void onMessage(Message message) {
        try {
            MessageBean messageBean = (MessageBean)((ObjectMessage)message).getObject();
            logger.info("=======================>监听器收到消息，将消息存入redis");
//            try {
//             //  Util_Message.sendMessage(messageBean);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }



}
