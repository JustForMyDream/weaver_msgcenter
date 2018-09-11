package com.weaver.base.msgcenter.utils.messagequeue.listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.utils.MsgCenterUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
/**
 * @ Author     ：wyl
 * @ Date       ：Created in 9:15 2018/9/6
 * @ Description：RabbitMQ监听器
 * @ Modified By：
 * @ Version：    1.0
 */
public class RabbitListener extends DefaultConsumer {

    private static final Log logger = LogFactory.getLog(ActiveListener.class);

    public RabbitListener(Channel channel) {
        super(channel);
    }

    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body)
            throws IOException {
        MessageBean messageBean = (MessageBean) MsgCenterUtil.deserialize(body);
        logger.info("================================rabbit取消息放入redis");
//        Util_Message.sendMessage(messageBean);
    }
}
