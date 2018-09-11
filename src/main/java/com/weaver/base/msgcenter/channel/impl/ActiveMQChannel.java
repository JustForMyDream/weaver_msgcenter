package com.weaver.base.msgcenter.channel.impl;


import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.utils.messagequeue.Util_ActiveMQ;


import javax.jms.JMSException;
import java.util.List;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 11:35 2018/9/7
 * @ Description：ActiveMQ消息发送通道
 * @ Modified By：
 * @ Version：    1.0
 */
public class ActiveMQChannel implements IMessageChannel {

    private static ActiveMQChannel activeMQChannel = null;

    private ActiveMQChannel(){}

    public static ActiveMQChannel getInstance(){
        if(null == activeMQChannel){
            synchronized (ActiveMQChannel.class){
                if(null == activeMQChannel){
                    activeMQChannel = new ActiveMQChannel();
                }
            }
        }
        return activeMQChannel;
    }

    private Util_ActiveMQ util_activeMQ = Util_ActiveMQ.getInstance();


    public boolean send(MessageBean message) throws Exception {
        return false;
    }

    public boolean delete(MessageBean message) throws Exception {
        return false;
    }

    public boolean deleteList(List<MessageBean> list) throws Exception {
        return false;
    }

    public boolean deleteListByUserId(String userId) throws Exception {
        return false;
    }

    public boolean update(MessageBean message) throws Exception {
        return false;
    }

    public boolean updateList(List<MessageBean> list) throws Exception {
        return false;
    }

    public List<MessageBean> getList(String userId) throws Exception {
        return null;
    }

    public List<MessageBean> getList(String moduleId, String userId) throws Exception {
        return null;
    }

    public Integer sendList(List<MessageBean> list) throws Exception {
        return null;
    }

    public Integer getCount(String userId) throws Exception {
        return null;
    }

    public Integer getCount(String moduleId, String userId) throws Exception {
        return null;
    }
}
