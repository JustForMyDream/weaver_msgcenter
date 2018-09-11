package com.weaver.base.msgcenter.utils.messagequeue.timer;

import com.weaver.base.msgcenter.service.MessageCenterServiceChannel;
import com.weaver.base.msgcenter.utils.messagequeue.Util_ActiveMQ;
import com.weaver.base.msgcenter.utils.messagequeue.Util_RabbitMQ;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.TimerTask;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 9:15 2018/9/8
 * @ Description：消息中心启动
 * @ Modified By：
 * @ Version：    1.0
 */
public class MessageCenterTask implements Runnable  {

    private static final Log logger = LogFactory.getLog(MessageCenterTask.class);

    public void run() {

        MessageCenterServiceChannel messageCenterServiceChannel = new MessageCenterServiceChannel();
        try {

            if(messageCenterServiceChannel.isActivemq_enable()){
                logger.info("====================>启动Active消息中心线程");
                Util_ActiveMQ util_activeMQ = Util_ActiveMQ.getInstance();
                util_activeMQ.receiveMessage();
            }
            if(messageCenterServiceChannel.isRabbitmq_enable()){
                logger.info("====================>启动Rabbit消息中心线程");
                Util_RabbitMQ util_rabbitMQ = Util_RabbitMQ.getInstance();
                util_rabbitMQ.receiveMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
